package com.capg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capg.mypaymentapp.beans.Customer;
import com.capg.mypaymentapp.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo{

	private static Map<String, Customer> data=new HashMap<String,Customer>(); 
	private static Map<String, List> transaction=new HashMap<String,List>(); 
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}

	public WalletRepoImpl() {
		super();
	}
	public boolean save(Customer customer) 
	{
		//System.out.println(data);
		if(data.get(customer.getMobileNo())==null)
		{
		data.put(customer.getMobileNo(),customer);
		List l=new ArrayList();
		l.add("account created");
		transaction.put(customer.getMobileNo(),l);
		if(data.get(customer.getMobileNo())!=null)
		{
			return true;
		}
		}
		return false;
	}

	public Customer findOne(String mobileNo) {
		return data.get(mobileNo);
	}
	public void saveTransaction(String mobileNo,String s) 
	{
		List l=transaction.get(mobileNo);
        l.add(s);
        transaction.put(mobileNo,l);
	}

	public List getTransaction(String mobileNo) {
		return transaction.get(mobileNo);
	}
	
}
