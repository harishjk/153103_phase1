package com.capg.mypaymentapp.service;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.capg.mypaymentapp.beans.Customer;


public interface WalletService {
public Customer createAccount(String name ,String mobileno, BigDecimal amount) throws SQLException;
public Customer showBalance (String mobileno) throws SQLException, Exception;
public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws SQLException, Exception;
public Customer depositAmount (String mobileNo,BigDecimal amount ) throws SQLException;
public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws SQLException;
public List printTransaction(String string) throws SQLException;

}
