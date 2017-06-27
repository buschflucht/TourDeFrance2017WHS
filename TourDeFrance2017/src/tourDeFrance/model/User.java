package tourDeFrance.model;

import java.util.Date;

public class User {

	private int userID;
	private int userName;
	private int sessionID;
	private String vorname;
	private String nachname;
	private String passwort;
	private Date angelegt;
	public int getUserID() {
		return userID;
	}
	public int getUserName() {
		return userName;
	}
	public int getSessionID() {
		return sessionID;
	}
	public String getVorname() {
		return vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public String getPasswort() {
		return passwort;
	}
	public Date getAngelegt() {
		return angelegt;
	}
	
	
	
}
