package Lisek573.git.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Lisek573.git.mainthings.Account;
import Lisek573.git.mainthings.Character;
import Lisek573.git.mainthings.CharacterJobs.Jobs;
import Lisek573.git.services.AccountDBManager;
import Lisek573.git.services.CharacterAccountDBManager;
import Lisek573.git.services.CharacterDBManager;

public class CharacterAccountDBManagerTest {

	CharacterDBManager cdb = new CharacterDBManager();
	AccountDBManager adb = new AccountDBManager();
	CharacterAccountDBManager cadb = new CharacterAccountDBManager();
	
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
			cadb.deleteAllCharacterFromAccount();
			adb.deleteAllAccounts();
			cdb.deleteAllCharacters();
	}

	@Test
	public void testAddCharacterToAccount() throws SQLException {
		adb.addAccount(new Account("ABC", "1", 1));
		cdb.addCharacter(new Character("Lisek", Jobs.Sniper, 55, (float)1));
		cadb.addCharacterToAccount(adb.FindAccountByName("MM"), cdb.findCharacterBySerial((float)1));
		assertEquals(1, cadb.getAccountCharacter(adb.FindAccountByName("MM")).size());
	}

	@Test
	public void testDeleteAllAccountCharacter() throws SQLException{
		adb.addAccount(new Account("ABC", "1", 1));
		adb.addAccount(new Account("ABC", "1", 1));
		cdb.addCharacter(new Character("Lisek", Jobs.Sniper, 55, (float)1));
		cadb.addCharacterToAccount(adb.FindAccountByName("ABC"), cdb.findCharacterBySerial((float)1));
		cadb.addCharacterToAccount(adb.FindAccountByName("MML"), cdb.findCharacterBySerial((float)1));
		cadb.addCharacterToAccount(adb.FindAccountByName("MM"), cdb.findCharacterBySerial((float)1));
		cadb.deleteAllAccountCharacter(adb.FindAccountByName("MM"));
		cadb.deleteAllAccountCharacter(adb.FindAccountByName("MML"));
		assertTrue(cadb.getAccountCharacter(adb.FindAccountByName("MM")).size() == 0);
		assertTrue(cadb.getAccountCharacter(adb.FindAccountByName("MML")).size() == 0);
	}

	@Test
	public void testDeleteAllCharacterFromAccount() throws SQLException{
		adb.addAccount(new Account("ABC", "1", 1));
		cdb.addCharacter(new Character("Lisek", Jobs.Sniper, 55, (float)1));
		cadb.addCharacterToAccount(adb.FindAccountByName("MM"), cdb.findCharacterBySerial((float)1));
		cadb.deleteAllCharacterFromAccount();
		assertTrue(cadb.getAccountCharacter(adb.FindAccountByName("MM")).size() == 0);
	}

	@Test
	public void testGetAccountCharacter() throws SQLException{
		adb.addAccount(new Account("ABC", "1", 1));
		cdb.addCharacter(new Character("Lisek", Jobs.Sniper, 55, (float)1));
		cadb.addCharacterToAccount(adb.FindAccountByName("MM"), cdb.findCharacterBySerial((float)1));
		assertEquals(1, cadb.getAccountCharacter(adb.FindAccountByName("MM")).size());
	}

}
