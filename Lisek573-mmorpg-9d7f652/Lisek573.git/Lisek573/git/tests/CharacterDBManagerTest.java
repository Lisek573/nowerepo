package Lisek573.git.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Lisek573.git.mainthings.Character;
import Lisek573.git.mainthings.CharacterJobs.Jobs;
import Lisek573.git.services.CharacterDBManager;

public class CharacterDBManagerTest {

	CharacterDBManager cdb = new CharacterDBManager();

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
	cdb.deleteAllCharacters();	
	}

	@Test
	public void testAddCharacter() throws SQLException  {
		cdb.addCharacter(new Character("Lisek", Jobs.Sniper, 94, (float)1));
		cdb.addCharacter(new Character("Lisek", Jobs.Sniper, 94, (float)1));
		assertEquals(2, cdb.getAllCharacters().size());
	
	}

	@Test
	public void testGetAllCharacters() throws SQLException {
		cdb.addCharacter(new Character("Lisek", Jobs.Sniper, 94, (float)1));
		assertEquals(1, cdb.getAllCharacters().size());
	}

	
	@Test
	public void testFindCharacterBySerial() throws SQLException {
		cdb.addCharacter(new Character("Lisek", Jobs.Sniper, 94, (float)1));
		assertEquals(1, cdb.findCharacterBySerial(1).size());
	}

	

}