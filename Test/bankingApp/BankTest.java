package bankingApp;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BankTest {
	protected Currency CAD;
	protected Currency HKD;
	protected Bank RBC;
	protected Bank TD;
	protected Bank HSBC;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		
		// setup some test currencies
		this.HKD = new Currency("HKD", 0.13);
		this.CAD = new Currency("CAD", 0.75);
		
		// setup test banks
		this.RBC = new Bank("Royal Bank of Canada", CAD);
		this.TD = new Bank("TD Bank", CAD);
		this.HSBC = new Bank("Hong Kong Shanghai Banking Corporation", HKD);
		
		// add sample customers to the banks
		
		
		// HINT:  uncomment these lines AFTER you test the openAccount() function
		// You can quickly uncomment / comment by highlighting the lines of code and pressing 
		// CTRL + / on your keyboard  (or CMD + / for Macs)
		
		  this.RBC.openAccount("Marcos"); this.RBC.openAccount("Albert");
		  this.TD.openAccount("Jigesha"); this.HSBC.openAccount("Pritesh");
		 
	}

	@Test
	public void testGetName() {
		assertEquals("Royal Bank of Canada", this.RBC.getName());
		//fail("Write test case here");
	}

	@Test
	public void testGetCurrency() {
		assertEquals("CAD", this.TD.getCurrency().getName());
		//fail("Write test case here");
	}

	@Test(expected = AccountExistsException.class)
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		
		this.RBC.openAccount("Marcos");
		this.RBC.openAccount("Chin");
		
		this.TD.openAccount("Sachin");
		
		//to check the exception
		this.TD.openAccount("Sachin");
		
		//fail("Write test case here");
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testDeposit() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		
		this.RBC.deposit("Chin", new Money(2400, CAD));
		
		assertEquals(2400, this.RBC.getBalance("Chin"),0.001);
		
		//to check the expception
		this.RBC.deposit("Bael", new Money(1400, CAD));
		
		//fail("Write test case here");
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testWithdraw() throws AccountDoesNotExistException, AccountExistsException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions
		this.RBC.withdraw("Chin", new Money(1000, CAD));
		
		assertEquals(1400, this.RBC.getBalance("Chin"),0.001);
		
		// to test the exception 
		//Bail is not added, lets check if it bypass the exception or not
		this.RBC.deposit("Bael", new Money(1400, CAD));
		
		//fail("Write test case here");
	}
	
	@Test(expected = AccountDoesNotExistException.class)
	public void testGetBalance() throws AccountDoesNotExistException, AccountExistsException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		
		this.RBC.getBalance("Chin");
		
		assertEquals(1400, this.RBC.getBalance("Chin"),0.001);
		
		// to test the exception 
		//Bail is not added, lets check if it bypass the exception or not
		this.RBC.deposit("Bael", new Money(1400, CAD));
		
		//fail("Write test case here");
	}
	
	@Test(expected = AccountDoesNotExistException.class)
	public void testTransfer() throws AccountDoesNotExistException {
		// Note: You should test both types of transfers:
		// 1. Transfer from account to account
		// 2. Transfer between banks
		// See the Bank.java file for more details on Transfers
		
		//1. from account to account
		this.RBC.transfer("Chin", "Marcos", new Money(500, CAD));
		
		assertEquals(900, this.RBC.getBalance("Chin"), 0.001);
		assertEquals(500, this.RBC.getBalance("Marcos"), 0.001);
		
		
		//2. transfer b/w banks
		this.RBC.transfer("Marcos", this.TD, "Sachin", new Money(200, CAD));
		
		assertEquals(300, this.RBC.getBalance("Marcos"), 0.001);
		assertEquals(2000, this.TD.getBalance("Chin"), 0.001);
		
		
		// to test the exception 
		//Bail is not added, lets check if it bypass the exception or not
		this.RBC.deposit("Bael", new Money(1400, CAD));
		
		//fail("Write test case here");
	}
	
}
