package com.capg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.print.attribute.standard.PrinterMessageFromOperator;

import com.capg.mypaymentapp.beans.Customer;
import com.capg.mypaymentapp.beans.Wallet;

public class WalletRepoImpl1 implements WalletRepo {
	
	Connection con=null;
	public WalletRepoImpl1() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		con.setAutoCommit(true);
	}	
	@Override
	public boolean save(Customer customer){
     PreparedStatement p,p1,p2,p3;
	try {
		p = con.prepareStatement("select count(*) from customer where mobilenumber=?");
		p.setString(1,customer.getMobileNo());
		//System.out.println(p.execute());
		ResultSet i=p.executeQuery();
		i.next();
		int j=i.getInt(1);
		//System.out.println();
		if(j==0)
		{
			//System.out.println("1");
			p=con.prepareStatement("insert into customer values(?,?)");
			p.setString(1, customer.getMobileNo());
			p.setString(2, customer.getName());
			p.execute();
			p1=con.prepareStatement("insert into wallet values(?,?)");
			p1.setString(1, customer.getMobileNo());
			p1.setBigDecimal(2,new BigDecimal(0));
			p3=con.prepareStatement("insert into transaction values(?,'Account created')");
			p3.setString(1,customer.getMobileNo());
			p1.execute();
			p.close();
			p1.close();
			
			p3.execute();
			p3.close();
			return true;
		}
		else if(!(Thread.currentThread().getStackTrace()[2].getMethodName().equals("createAccount")))
		{
			p2=con.prepareStatement("update wallet set balance=? where mobilenumber=?");
			p2.setBigDecimal(1, customer.getWallet().getBalance());
			p2.setString(2, customer.getMobileNo());
			p2.executeUpdate();
			p2.close();
			return false;
		}
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	}
	@Override
	public Customer findOne(String mobileNo) throws SQLException {
		PreparedStatement p,p1,p2;
		p = con.prepareStatement("select * from customer where mobilenumber=?");
		p.setString(1,mobileNo);
		p1 = con.prepareStatement("select * from wallet where mobilenumber=?");
		p1.setString(1,mobileNo);
		ResultSet r=p.executeQuery();
		ResultSet r1=p1.executeQuery();
		Customer c=new Customer();
		while(r.next())
		{
		c.setMobileNo(r.getString(1));
		c.setName(r.getString(2));
		while(r1.next())
		{
		c.setWallet(new Wallet(r1.getBigDecimal("balance")));
		}
		}
		if(c.getMobileNo()==null)
		{
			return null;
		}
		else
		{
			return c;
		}
	}
	@Override
	public void saveTransaction(String mobileNo, String s) throws SQLException 
	{
		PreparedStatement p;
		p=con.prepareStatement("insert into transaction values(?,?)");
		p.setString(1,mobileNo);
		p.setString(2,s);
		p.execute();
		p.close();
	}
	@Override
	public List getTransaction(String mobileNo) throws SQLException {
		PreparedStatement p;
		p=con.prepareStatement("select transactionlog from transaction where mobileno=?");
		p.setString(1,mobileNo);
		
		ResultSet rs=p.executeQuery();
		List l=new LinkedList();
		while(rs.next())
		{
			l.add(rs.getString(1));
		}
		p.close();
		return l;
	    }

}
