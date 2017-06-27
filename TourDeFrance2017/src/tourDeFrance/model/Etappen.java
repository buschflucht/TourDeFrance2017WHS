package tourDeFrance.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Etappen {

	private IntegerProperty etappenID;
	private IntegerProperty etappennummer;
	private ObjectProperty<LocalDateTime> datum;
	private StringProperty startort;
	private StringProperty zielort;
	private DoubleProperty laenge;
	private IntegerProperty art;
	private StringProperty fahrerPlatz1;
	private Time siegerzeit;
	private StringProperty fahrerPlatz2;
	private StringProperty fahrerPlatz3;
	private StringProperty teamPlatz1;
	private StringProperty teamPlatz2;
	private StringProperty teamPlatz3;
	private StringProperty fahrerGelb;
	private StringProperty fahrerGruen;
	private StringProperty fahrerBerg;
	private StringProperty dopingFahrer;
	private StringProperty dopingTeam;

	public Etappen(Integer etappenID, String startort, String zielort, Double laenge) {
		this.etappenID = new SimpleIntegerProperty(etappenID);
		this.startort = new SimpleStringProperty(startort);
		this.zielort = new SimpleStringProperty(zielort);
		this.laenge = new SimpleDoubleProperty(laenge);
		
	}

	public IntegerProperty getEtappenID() {
		return etappenID;
	}

	public IntegerProperty getEtappennummer() {
		return etappennummer;
	}

	public ObjectProperty<LocalDateTime> getDatum() {
		return datum;
	}

	public StringProperty getStartort() {
		return startort;
	}

	public StringProperty getZielort() {
		return zielort;
	}

	public DoubleProperty getLaenge() {
		return laenge;
	}

	public IntegerProperty getArt() {
		return art;
	}

	public StringProperty getFahrerPlatz1() {
		return fahrerPlatz1;
	}

	public Time getSiegerzeit() {
		return siegerzeit;
	}

	public StringProperty getFahrerPlatz2() {
		return fahrerPlatz2;
	}

	public StringProperty getFahrerPlatz3() {
		return fahrerPlatz3;
	}

	public StringProperty getTeamPlatz1() {
		return teamPlatz1;
	}

	public StringProperty getTeamPlatz2() {
		return teamPlatz2;
	}

	public StringProperty getTeamPlatz3() {
		return teamPlatz3;
	}

	public StringProperty getFahrerGelb() {
		return fahrerGelb;
	}

	public StringProperty getFahrerGruen() {
		return fahrerGruen;
	}

	public StringProperty getFahrerBerg() {
		return fahrerBerg;
	}

	public StringProperty getDopingFahrer() {
		return dopingFahrer;
	}

	public StringProperty getDopingTeam() {
		return dopingTeam;
	}

}
