package tourDeFrance.model;

import java.time.LocalTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class Fahrer {

	private IntegerProperty fahrerID;
	private StringProperty fahrerVorname;
	private StringProperty fahrerNachname;
	private IntegerProperty team;
	private IntegerProperty aktiv;
	private ObjectProperty<LocalTime> gesamtzeit;
	private IntegerProperty etappensiege;
	private IntegerProperty punkteGruen;
	private IntegerProperty punkteBerg;

	public IntegerProperty getFahrerID() {
		return fahrerID;
	}

	public StringProperty getFahrerVorname() {
		return fahrerVorname;
	}

	public StringProperty getFahrerNachname() {
		return fahrerNachname;
	}

	public IntegerProperty getTeam() {
		return team;
	}

	public IntegerProperty getAktiv() {
		return aktiv;
	}

	public ObjectProperty<LocalTime> getGesamtzeit() {
		return gesamtzeit;
	}

	public IntegerProperty getEtappensiege() {
		return etappensiege;
	}

	public IntegerProperty getPunkteGruen() {
		return punkteGruen;
	}

	public IntegerProperty getPunkteBerg() {
		return punkteBerg;
	}

}
