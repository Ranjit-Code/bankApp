package bankingApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bankingApp.Currency;
import bankingApp.Money;

public class MoneyTest {
	protected Currency CAD, HKD, NOK, EUR;
	protected Money CAD100, EUR10, CAD200, EUR20, CAD0, EUR0, CADnegative100, CAD30;
	
	@Before
	public void setUp() throws Exception {
		// setup sample currencies
		CAD = new Currency("CAD", 0.75);
		HKD = new Currency("HKD", 0.13);
		EUR = new Currency("EUR", 1.14);
		
		// setup sample money amounts
		CAD100 = new Money(100, CAD);
		
		EUR10 = new Money(10, EUR);
		CAD200 = new Money(200, CAD);
		CAD30 = new Money(30.40, CAD);
		EUR20 = new Money(20, EUR);
		CAD0 = new Money(0, CAD);
		EUR0 = new Money(0, EUR);
		CADnegative100 = new Money(-100, CAD);
	}

	@Test
	public void testGetAmount() throws Exception {
		assertEquals(100, CAD100.getAmount(), 0.001);
	}

	@Test
	public void testGetCurrency() {
		assertEquals("EUR", EUR20.getCurrency().getName());
		//fail("Write test case here");
	}

	@Test
	public void testToString() {
		assertEquals("200.0 CAD", CAD200.toString());
		//fail("Write test case here");
	}

	@Test
	public void testGetUniversalValue() {
		double value = 200 * .75;
		assertEquals(value, CAD200.getUniversalValue(), 0.001);
		//fail("Write test case here");
	}

	@Test
	public void testEqualsMoney() {
		assertFalse(CAD100.equals(EUR20));
		assertTrue(CAD30.equals(EUR20));
	}

	@Test
	public void testAdd() {
		//Testing money object with different currency
		double valueInUSD = CAD100.getCurrency().valueInUSD(CAD100.getAmount());
		double convertedValue = CAD.valueInThisCurrency(CAD100.getAmount(), EUR10.getCurrency());
		double addedValue = valueInUSD + convertedValue;
		String value = Double.toString(addedValue).concat(" ").concat(CAD100.getCurrency().getName());
		
		assertEquals(value, CAD100.add(EUR10).toString());
		
		
		//Testing money object with same currency
		double valueInUSD_same = CAD100.getCurrency().valueInUSD(CAD100.getAmount());
		double convertedValue_same = CAD200.getCurrency().valueInUSD(CAD200.getAmount());
		double addedValue_same = valueInUSD_same + convertedValue_same;
		String value_same = Double.toString(addedValue_same).concat(" ").concat(CAD100.getCurrency().getName());
		
		assertEquals(value_same, CAD100.add(CAD200).toString());
	}

	@Test
	public void testSubtract() {
		//Testing money object with different currency
		double valueInUSD = CAD100.getCurrency().valueInUSD(CAD100.getAmount());
		double convertedValue = CAD.valueInThisCurrency(CAD100.getAmount(), EUR10.getCurrency());
		double addedValue = valueInUSD - convertedValue;
		String value = Double.toString(addedValue).concat(" ").concat(CAD100.getCurrency().getName());
		
		assertEquals(value, CAD100.subtract(EUR10).toString());
		
		//Testing money object with same currency
		double valueInUSD_same = CAD100.getCurrency().valueInUSD(CAD100.getAmount());
		double convertedValue_same = CAD200.getCurrency().valueInUSD(CAD200.getAmount());
		double addedValue_same = valueInUSD_same - convertedValue_same;
		String value_same = Double.toString(addedValue_same).concat(" ").concat(CAD100.getCurrency().getName());
		
		assertEquals(value_same, CAD100.subtract(CAD200).toString());
	}

	@Test
	public void testIsZero() {
		assertTrue(CAD0.isZero());
		//fail("Write test case here");
	}

	@Test
	public void testNegate() {
		double amt = - CAD100.getAmount();
		String value = Double.toString(amt) + " " + CAD100.getCurrency().getName();
		
		assertEquals(value, CAD100.negate().toString());
		//fail("Write test case here");
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, CAD30.compareTo(EUR20));
		assertEquals(1, CAD100.compareTo(EUR10));
		assertEquals(-1, EUR10.compareTo(CAD100));
		//fail("Write test case here");
	}
}
