package com.capg.mypaymentapp.repo;

import java.sql.SQLException;
import java.util.List;

import com.capg.mypaymentapp.beans.Customer;

public class WalletRepoImpl2 implements WalletRepo {

	@Override
	public boolean save(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer findOne(String mobileNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTransaction(String mobileNo, String s) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List getTransaction(String mobileNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
