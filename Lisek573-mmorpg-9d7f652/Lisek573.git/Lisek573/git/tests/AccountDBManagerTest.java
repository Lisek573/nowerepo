package Lisek573.git.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Lisek573.git.mainthings.Account;
import Lisek573.git.services.AccountDBManager;

public class AccountDBManagerTest {

	AccountDBManager adb = new AccountDBManager();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		adb.deleteAllAccounts();
	}


	@Test
	public void testAddAccount() throws SQLException {
		adb.addAccount(new Account("ABC", null, null));
		assertEquals(1, adb.getAllAccounts().size());
	}

	@Test
	public void testGetAllAccounts() throws SQLException {
		adb.addAccount(new Account("DEF", null, null));
		adb.addAccount(new Account("GHI", null, null));
		adb.addAccount(new Account("JKM", null, null));
		assertEquals(3, adb.getAllAccounts().size());
	}
	
	@Test
	public void testclear() throws SQLException {
		adb.clear();
		assertEquals(0, adb.getAllAccounts().size());
	}

	@Test
	public void testFindAccountByName() throws SQLException {
		adb.addAccount(new Account("ABC", "ABC", 1));
		assertEquals(1, adb.FindAccountByName("ABC").size());
		assertTrue(adb.FindAccountByName("ABC").size() == 1);
	}
	@Test
	public void testdeleteAllAccounts() throws SQLException 
	{
		adb.addAccount(new Account("ABC", "ABC", 1));
		adb.addAccount(new Account("ABD", "ABD", 2));
		adb.deleteAllAccounts();
		assertEquals(0, adb.getAllAccounts().size());
	}




}
