package com.capg.mypaymentapp.repo;

import java.sql.SQLException;
import java.util.List;

import com.capg.mypaymentapp.beans.Customer;

public interface WalletRepo {

public boolean save(Customer customer);
	
	public Customer findOne(String mobileNo) throws SQLException;
	public void saveTransaction(String mobileNo,String s) throws SQLException;
	public List getTransaction(String mobileNo) throws SQLException;

}
