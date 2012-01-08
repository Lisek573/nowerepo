package Lisek573.git;

import Lisek573.git.CharacterJobs.Jobs;

public class Character {

	String Name;
	Jobs Job;
	Integer Level;
	float Serial;
	boolean vip;

	public Character(String Name, Jobs Job, Integer Level, Float Serial) {
		this.Name = Name;
		this.Job = Job;
		this.Level = Level;
		this.Serial = Serial;
	}

	public void printCharacter() {
		System.out.println("Name: " + this.Name + "\t** Level: " + this.Level
				+ "\t** Job: " + this.Job);
	}

	public String getName() {
		return Name;
	}

	public Jobs getJob() {
		return Job;
	}

	public Integer getLevel() {
		return Level;
	}
	
	public Float getSerial() {
		return Serial;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public void setJob(Jobs Job) {
		this.Job = Job;
	}

	public void setLevel(Integer Level) {
		this.Level = Level;
	}
	
	public void setSerial(Float Serial) {
		this.Serial = Serial;
	}

	public void setVip(boolean vip) {
		this.vip = vip;

	}

	public boolean getVip() {
		return vip;

	}
}
