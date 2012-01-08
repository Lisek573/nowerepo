package Lisek573.git.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Lisek573.git.Account;
import Lisek573.git.services.AccountDBManager;

public class AccountDBManagerTest {

	AccountDBManager cdb = new AccountDBManager();
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
		cdb.deleteAllAccounts();
	}


	@Test
	public void testAddAccount() throws SQLException {
		cdb.addAccount(new Account("ABC", null, null));
		assertEquals(1, cdb.getAllAccounts().size());
	}

	@Test
	public void testGetAllAccounts() throws SQLException {
		cdb.addAccount(new Account("DEF", null, null));
		cdb.addAccount(new Account("GHI", null, null));
		cdb.addAccount(new Account("JKM", null, null));
		assertEquals(3, cdb.getAllAccounts().size());
	}
	
	@Test
	public void testclear() throws SQLException {
		cdb.clear();
		assertEquals(0, cdb.getAllAccounts().size());
	}

	@Test
	public void testFindAccountByName() throws SQLException {
		cdb.addAccount(new Account("ABC", null, null));
		assertEquals(1, cdb.FindAccountByName("ABC").size());
		assertTrue(cdb.FindAccountByName("ABC").size() == 1);
	}
	@Test
	public void testdeleteAllAccounts() throws SQLException 
	{
		cdb.addAccount(new Account("MML", null, null));
		cdb.addAccount(new Account("MML", null, null));
		cdb.deleteAllAccounts();
		assertEquals(0, cdb.getAllAccounts().size());
	}




}
