package com.capg.mypaymentapp.pl;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import com.capg.mypaymentapp.beans.Customer;
import com.capg.mypaymentapp.beans.Wallet;
import com.capg.mypaymentapp.exception.InvalidInputException;
import com.capg.mypaymentapp.pl.Client;
import com.capg.mypaymentapp.service.WalletServiceImpl;

public class Client {
	 public void menu() {
		   try
		   {
		   WalletServiceImpl w=new WalletServiceImpl();
		 Scanner sc=new Scanner(System.in);
		     System.out.println("1.add user");
			 System.out.println("2.show balance");
			 System.out.println("3.fund transfer");
			 System.out.println("4.deposit money");
			 System.out.println("5.withdraw money");
			 System.out.println("6.print transaction");
			 System.out.println("0.Exit");
			 int i1=sc.nextInt();
			 switch(i1)
			 {
			 case 1: System.out.println("enter your phone Number");
					 String mobileNo= sc.next();
					 System.out.println("enter yor Name");
					 String name= sc.next();
				     if(w.createAccount(name, mobileNo,new BigDecimal(0))!=null)
				     {
				    	 System.out.println("account created");
				     }
				     break;
			 case 2:System.out.println("enter your phone Number");
			        String mobileNo2= sc.next(); 
			       
			  	    System.out.println("Balance is" + w.showBalance(mobileNo2).getWallet().getBalance());
			 break;
			 case 3: System.out.println("enter your phone number");
			 String sMobileNo= sc.next();
			 System.out.println("enter the reciever phone number");
			 String tMobileNo= sc.next();			 
			 System.out.println("enter the amount");
			 BigDecimal amo=sc.nextBigDecimal();
			 if(w.fundTransfer(sMobileNo, tMobileNo,amo)!=null)
			 {
				 System.out.println("fund transferred succesfully");
			 }
			 break;
			 case 4:System.out.println("enter your phone Number");
	          String mobileNo3= sc.next();
				 System.out.println("enter the money to deposit");
			 BigDecimal depositAmount= sc.nextBigDecimal();
			 if(w.depositAmount(mobileNo3,depositAmount)!=null)
			 {
				 System.out.println("money deposited");
			 }
			 break;
			 case 5:System.out.println("enter your phone Number");
	          String mobileNo4= sc.next();
				 System.out.println("enter the money to withdraw");
			  BigDecimal withDrawAmount = sc.nextBigDecimal();
			 if(w.withdrawAmount(mobileNo4, withDrawAmount)!=null)
			 {
				 System.out.println("debited");
			 }
			 break;
			 case 6:System.out.println("enter your phone Number");
	          String mobileNo5= sc.next();
	          List l= w.printTransaction(mobileNo5);
	          Iterator i=l.iterator();
	          while(i.hasNext())
	          {
			System.out.println(i.next());
	          }
			 break;
			 case 0:
					System.exit(0);
			default:System.out.println("Invalid Selection");
			 }
			 }
		   catch(InputMismatchException e)
		   {
			   System.out.println("invalid input");
		   }
		   catch(Exception e)
		   {
			   System.out.println(e.getMessage());
		   }
	   }
	   public static void main( String[] args ){
		   Client c = new Client();
	       while(true)
	       {
	    	   c.menu();
	       }
	    }}
