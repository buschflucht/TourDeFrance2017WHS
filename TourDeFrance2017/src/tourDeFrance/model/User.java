package tourDeFrance.model;

import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class User {

	private IntegerProperty userID;
	private StringProperty userName;
	private IntegerProperty sessionID;
	private StringProperty vorname;
	private StringProperty nachname;
	private StringProperty passwort;
	private ObjectProperty<LocalDate> angelegt;
	private IntegerProperty punkte;
	private final ReadOnlyStringWrapper fullname = new ReadOnlyStringWrapper();

	public User() {
		
		this.vorname = new SimpleStringProperty();
		this.nachname = new SimpleStringProperty();
		fullname.bind(Bindings.concat(vorname, " ", nachname));
	}
	
	 public ReadOnlyStringProperty nameProperty() {
	        return fullname.getReadOnlyProperty();
	    }

	    public final String getName() {
	        return fullname.get();
	    }
	
	// UserID
	public int getEtappenID() {
		return userID.get();
	}

	public void setUserID(int userID) {
		this.userID.set(userID);
	}

	public IntegerProperty etappenIDProperty() {
		return userID;
	}

	// userName
	public String getUserName() {
		return userName.get();
	}

	public void setUserName(String userName) {
		this.userName.set(userName);
	}

	public StringProperty userNameProperty() {
		return userName;
	}

	// VorName
	public String getVorName() {
		return vorname.get();
	}

	public void setVorName(String vorName) {
		this.vorname.set(vorName);
	}

	public StringProperty vorNameProperty() {
		return vorname;
	}

	// NachName
	public String getNachName() {
		return nachname.get();
	}

	public void setNachName(String nachName) {
		this.nachname.set(nachName);
	}

	public StringProperty nachNameProperty() {
		return nachname;
	}
	
	// Punkte
	public int getPunkte() {
		return punkte.get();
	}

	public void setPunkte(int punkte) {
		this.punkte.set(punkte);
	}

	public IntegerProperty punkteIDProperty() {
		return punkte;
	}
	
	
	

}
