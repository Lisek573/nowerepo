package Lisek573.git.mainthings;

import java.sql.SQLException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import Lisek573.git.exception.LevelTooLowException;
import Lisek573.git.exception.NameCannotBeSameException;
import Lisek573.git.mainthings.CharacterJobs.Jobs;
import Lisek573.git.services.AccountDBManager;
import Lisek573.git.services.CharacterAccountDBManager;
import Lisek573.git.services.CharacterDBManager;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws LevelTooLowException,
			NameCannotBeSameException, SQLException {

		PropertyConfigurator.configure("Log4J.properties");

		Account id0001 = new Account("lisek573", "Tomaszewski", 0001);
		id0001.addCharacter(new Character("Lisek", Jobs.Sniper, 94, (float) 1));
		Character cha0001 = (new Character("Lisek", Jobs.Sniper, 94, (float) 1));
		id0001.addCharacter(new Character("Skybird", Jobs.Swordsman, 35,
				(float) 2));
		Character cha0002 = (new Character("Skybird", Jobs.Swordsman, 35,
				(float) 2));
		id0001.addCharacter(new Character("Rorona Frixell", Jobs.Alchemist, 64,
				(float) 3));
		Character cha0003 = (new Character("Rorona Frixell", Jobs.Alchemist,
				64, (float) 3));

		id0001.printAccount();
		id0001.printCharacter();

		AccountDBManager db = new AccountDBManager();
		db.addAccount(id0001);

		CharacterDBManager cdb = new CharacterDBManager();
		cdb.addCharacter(cha0001);
		cdb.addCharacter(cha0002);
		cdb.addCharacter(cha0003);
		
		CharacterAccountDBManager cadb = new CharacterAccountDBManager();

		cadb.addCharacterToAccount(db.FindAccountByName(id0001.getSurname()),
		cdb.findCharacterByName("Skybird"));
		cadb.addCharacterToAccount(db.FindAccountByName(id0001.getSurname()),
		cdb.findCharacterByName("Lisek"));
}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Main.logger = logger;
	}
}