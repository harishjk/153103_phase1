
package com.capg.mypaymentapp.service;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capg.mypaymentapp.beans.Customer;
import com.capg.mypaymentapp.beans.Wallet;
import com.capg.mypaymentapp.exception.InsufficientBalanceException;
import com.capg.mypaymentapp.exception.InvalidInputException;
import com.capg.mypaymentapp.repo.WalletRepo;
import com.capg.mypaymentapp.repo.WalletRepoImpl;
import com.capg.mypaymentapp.repo.WalletRepoImpl1;
import com.sun.org.glassfish.external.arc.Taxonomy;


public class WalletServiceImpl implements WalletService{

private WalletRepo repo;
Customer c;
Wallet w;
	public WalletServiceImpl(Map<String, Customer> data){
		repo= new WalletRepoImpl(data);
	}
	public WalletServiceImpl(WalletRepo repo) {
		super();
		this.repo = repo;
	}

	public WalletServiceImpl(){
		repo= new WalletRepoImpl();
	}

	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws SQLException {
		 validateName(name);
		 validateNumber(mobileNo);
		if(repo.findOne(mobileNo)==null) 
		{
		c = new Customer(name,mobileNo,new Wallet(amount));
		if(repo.save(c))
		{
		return c;
		}
		else
		{
			return null;
		}
		}
		else
		{
		throw new InvalidInputException("user already Exixt");	
		}
	}

	public Customer showBalance(String mobileNo) throws SQLException,Exception {
		validateNumber(mobileNo);
		Customer customer=repo.findOne(mobileNo);
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException("mobile number not found");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws SQLException, Exception {
		validateNumber(sourceMobileNo);
		validateNumber(targetMobileNo);
		validateAmount(amount);
		Customer sourceCustomer = repo.findOne(sourceMobileNo);
		 Customer targetCustomer = repo.findOne(targetMobileNo);
         BigDecimal sourceBalance=sourceCustomer.getWallet().getBalance();
         BigDecimal targetBalance=targetCustomer.getWallet().getBalance();
         if(sourceCustomer!=null && targetCustomer != null && !(sourceMobileNo.equals(targetMobileNo)) )
         {
         if(sourceBalance.compareTo(amount) >=0)
         {
       	  sourceCustomer.getWallet().setBalance(sourceBalance.subtract(amount));;
       	  targetCustomer.getWallet().setBalance(targetBalance.add(amount));
          repo.save(sourceCustomer);
          repo.save(targetCustomer);
          repo.saveTransaction(sourceMobileNo,"amount of rs "+ amount + " has been  transferred to" + targetMobileNo);
          repo.saveTransaction(targetMobileNo,"amount of rs "+ amount + " has been  recieved from " + sourceMobileNo);
          return sourceCustomer;
         }
         else
         {
       	  throw new InsufficientBalanceException("insufficient balance");
         }
         }
         else
         {
        	 throw new InvalidInputException("wrong phone number");
         }
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) throws SQLException {
		validateNumber(mobileNo);
		validateAmount(amount);
		c=repo.findOne(mobileNo);
		if(c!=null)
		{
			if(amount.compareTo(new BigDecimal(0))>0)
			{
				repo.saveTransaction(mobileNo,"amount of rs "+ amount + " has been  deposited");
			amount=amount.add(c.getWallet().getBalance());
		    c.getWallet().setBalance(amount);
		    repo.save(c);
		    //repo.saveTransaction(mobileNo,"amount of rs "+ amount + " has been  deposited");
		    return c;
			}
			else
			{
				throw new InvalidInputException("amount should be greater than 0");
			}
		}
		throw new InvalidInputException("wrong phone number");
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws SQLException {
		validateNumber(mobileNo);
		validateAmount(amount);
		c=repo.findOne(mobileNo);
		if(c!=null )
		{
			if(c.getWallet().getBalance().compareTo(amount)>=0)
		{
				 repo.saveTransaction(mobileNo,"amount of "+ amount + " has been  debited");
			amount=c.getWallet().getBalance().subtract(amount);
		    c.getWallet().setBalance(amount);
		    repo.save(c);
		   
		    return c;
		}
			throw new InsufficientBalanceException("insufffient balance");
		}
		throw new InvalidInputException("wrong phone number");
	}
	public List printTransaction(String mobileNo) throws SQLException
	{
		List l=repo.getTransaction(mobileNo);
		if(l!=null)
		{
			return l;
		}
		else
		{
			throw new InvalidInputException("wrong phone number");		}
	}
	public boolean validateNumber(String number)
	 {
		 String phonePattern="[1-9][0-9]{9}";
		 if(number.matches(phonePattern))
		 {
			
			 return true;
		 }
		 throw new InvalidInputException("invalid mobile number");
	 }
	 public boolean validateName(String name)
	 {
		 String namePattern = "[A-Za-z]+";
		 if(name.matches(namePattern))
		 {
			 return true;
		 }
		 throw new InvalidInputException("invalid name");
	 }
	 public boolean validateAmount(BigDecimal amount)
	 {
		 if(amount.compareTo(new BigDecimal(0))>0)
		 {
			 return true;
		 }
		 throw new InvalidInputException("number cannot be Zero/in negative");
	 }
}
