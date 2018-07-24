package com.capg.mypaymentapp.test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.capg.mypaymentapp.beans.Customer;
import com.capg.mypaymentapp.beans.Wallet;
import com.capg.mypaymentapp.service.WalletService;
import com.capg.mypaymentapp.service.WalletServiceImpl;
import com.capg.mypaymentapp.exception.*;;

@SuppressWarnings("unused")
public class TestClass {
static WalletService service;
@Before
public void initData()
{
Map<String,Customer> data= new HashMap<String, Customer>();
Customer cust1=new Customer("ram", "9790825196",new Wallet(new BigDecimal(1000)));
Customer cust2=new Customer("harish", "8015552903",new Wallet(new BigDecimal(2000)));
Customer cust3=new Customer("siva", "8056156551",new Wallet(new BigDecimal(3000)));
data.put(cust1.getMobileNo(), cust1);
data.put(cust2.getMobileNo(), cust2);
data.put(cust3.getMobileNo(), cust3);
service= new WalletServiceImpl(data);
}
@BeforeClass
public static void setUpBeforeClass() throws Exception
{
service=new WalletServiceImpl();
}
@AfterClass
public static void tearDownAfterClass() throws Exception
{
service=null;
}
@Before
public void setUp() throws Exception
{
}
@After
public void tearDown() throws Exception
{
}
@Test(expected=NullPointerException.class)
public void testCreateAccount_1() throws SQLException  
{
service.createAccount(null,null,null);
}
@Test(expected=NullPointerException.class)
public void testCreateAccount_2() throws SQLException
{
service.createAccount(null,"",null);
}
@Test(expected=NullPointerException.class)
public void testCreateAccount_3() throws SQLException
{
service.createAccount(null,null,new BigDecimal(0));
}

@Test(expected=InvalidInputException.class)
public void testCreateAccount_4() throws SQLException
{
service.createAccount("9790825196","ragu",new BigDecimal(0));
}
@Test(expected=InvalidInputException.class)
public void testCreateAccount_5() throws SQLException
{
service.createAccount("praveen","",new BigDecimal(0));
}
@Test(expected=InvalidInputException.class)
public void testShowBalance_1() throws SQLException, Exception
{
service.showBalance("");
}
@Test(expected=InvalidInputException.class)
public void testShowBalance_2() throws SQLException, Exception
{
service.showBalance("102301928301298");
}
@Test(expected=InvalidInputException.class)
public void testFundTransfer_1() throws SQLException, Exception
{
service.fundTransfer("","",new BigDecimal(7000));
}
@Test(expected=InvalidInputException.class)
public void testFundTransfer_2() throws SQLException, Exception
{
service.fundTransfer("", "9887654321", new BigDecimal(6000));
}
@Test(expected=InvalidInputException.class)
public void testFundTransfer_3() throws SQLException, Exception
{
service.fundTransfer("98887654321", "", new BigDecimal(0));
}
@Test(expected=InvalidInputException.class)
public void testFundTransfer_4() throws SQLException, Exception
{
service.fundTransfer("7299779722","119843738248",new BigDecimal(7000));
}
@Test(expected=InvalidInputException.class)
public void testFundTransfer_5() throws SQLException, Exception
{
service.fundTransfer("119843738248", "98887654321", new BigDecimal(0));
}
@Test(expected=InvalidInputException.class)
public void testFundTransfer_6() throws SQLException, Exception
{
service.fundTransfer("3412k3jb490412","1938749817-13",new BigDecimal(7000));
}
@Test(expected=InvalidInputException.class)
public void testDeposit_1() throws SQLException
{
service.depositAmount("", new BigDecimal(2000));
}
@Test(expected=InvalidInputException.class)
public void testDeposit_2() throws SQLException
{
service.depositAmount("149873194329749", new BigDecimal(2000));
}
@Test(expected=InvalidInputException.class)
public void testDeposit_3() throws SQLException
{
service.depositAmount("149873194329749", new BigDecimal(-9876));
}
@Test(expected=InvalidInputException.class)
public void testDeposit_4() throws SQLException
{
service.depositAmount("", null);
}
@Test(expected=InvalidInputException.class)
public void testDeposit_5() throws SQLException
{
service.depositAmount("988765432", null);
}
@Test(expected=InvalidInputException.class)
public void testDeposit_6() throws SQLException
{
service.depositAmount("9887654321", new BigDecimal(-9876));
}
@Test(expected=InvalidInputException.class)
public void testWithdraw_1() throws SQLException
{
service.withdrawAmount("", new BigDecimal(2000));
}
@Test(expected=InvalidInputException.class)
public void testWithdraw_2() throws SQLException
{
service.withdrawAmount("01293812938932", new BigDecimal(2000));
}
@Test(expected=InvalidInputException.class)
public void testList_1() throws SQLException
{
service.printTransaction("");
}
@Test(expected=InvalidInputException.class)
public void testList_2() throws SQLException
{
service.printTransaction("1");
}
@After
public void testAfter()
{
service=null;
}
}
