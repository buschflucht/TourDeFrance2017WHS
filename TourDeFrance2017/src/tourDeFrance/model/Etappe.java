package tourDeFrance.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Etappe {

	private IntegerProperty etappenID;
	private IntegerProperty etappennummer;
	private ObjectProperty<LocalDate> datum;
	private ObjectProperty<LocalTime> zeit;
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

	public Etappe() {
		this.etappenID = new SimpleIntegerProperty();
		this.startort = new SimpleStringProperty();
		this.zielort = new SimpleStringProperty();
		this.laenge = new SimpleDoubleProperty();
		this.datum = new SimpleObjectProperty<LocalDate>();
		this.zeit = new SimpleObjectProperty<LocalTime>();

	}

	// EtappenID
	public int getEtappenID() {
		return etappenID.get();
	}

	public void setEtappenID(int etappenID) {
		this.etappenID.set(etappenID);
	}

	public IntegerProperty etappenIDProperty() {
		return etappenID;
	}

	// Startort
	public String getStartOrt() {
		return startort.get();
	}

	public void setStartOrt(String startOrt) {
		this.startort.set(startOrt);
	}

	public StringProperty startOrtProperty() {
		return startort;
	}

	// Zielort
	public String getZielOrt() {
		return zielort.get();
	}

	public void setZielOrt(String zielOrt) {
		this.zielort.set(zielOrt);
	}

	public StringProperty zielOrtProperty() {
		return zielort;
	}

	// Laenge
	public double getLaenge() {
		return laenge.get();
	}

	public void setLaenge(double laenge) {
		this.laenge.set(laenge);
	}

	public DoubleProperty laengeProperty() {
		return laenge;
	}

	public ObjectProperty<LocalDate> datumProperty() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum.set(datum);
	}
	public LocalDate getDatum() {
		return datum.get();
	}

	public ObjectProperty<LocalTime> zeitProperty() {
		return zeit;
	}
	public LocalTime getZeit() {
		return zeit.get();
	}

	public void setZeit(LocalTime zeit) {
		this.zeit.set(zeit);
	}

}
