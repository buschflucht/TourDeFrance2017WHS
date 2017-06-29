package tourDeFrance.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class User {

	private IntegerProperty userID;
	private IntegerProperty userName;
	private IntegerProperty sessionID;
	private StringProperty vorname;
	private StringProperty nachname;
	private StringProperty passwort;
	private ObjectProperty<LocalDate> angelegt;

	public IntegerProperty getUserID() {
		return userID;
	}

	public IntegerProperty getUserName() {
		return userName;
	}

	public IntegerProperty getSessionID() {
		return sessionID;
	}

	public StringProperty getVorname() {
		return vorname;
	}

	public StringProperty getNachname() {
		return nachname;
	}

	public StringProperty getPasswort() {
		return passwort;
	}

	public ObjectProperty<LocalDate> getAngelegt() {
		return angelegt;
	}

}
