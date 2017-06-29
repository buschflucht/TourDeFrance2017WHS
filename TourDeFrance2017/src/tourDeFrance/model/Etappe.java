package tourDeFrance.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Etappe {

	private IntegerProperty etappenID;
	private IntegerProperty etappennummer;
	private LocalDate ldatum;
	private LocalTime lzeit;
	private StringProperty datum;
	private StringProperty zeit;
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

	private StringProperty bezeichnung;

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
			.withLocale(Locale.GERMAN);
	private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
			.withLocale(Locale.GERMAN);

	public Etappe() {
		this.etappenID = new SimpleIntegerProperty();
		this.startort = new SimpleStringProperty();
		this.zielort = new SimpleStringProperty();
		this.laenge = new SimpleDoubleProperty();
		this.datum = new SimpleStringProperty();
		this.zeit = new SimpleStringProperty();
		this.bezeichnung = new SimpleStringProperty();

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

	// Datum
	public StringProperty datumProperty() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.ldatum = datum;
		this.datum.set(dateFormatter.format(datum));
	}

	public LocalDate getDatum() {
		return ldatum;
	}

	// Zeit
	public StringProperty zeitProperty() {
		return zeit;
	}

	public LocalTime getZeit() {
		return lzeit;
	}

	public void setZeit(LocalTime zeit) {
		this.lzeit = zeit;
		this.zeit.set(timeFormatter.format(zeit));
	}

	// Bezeichnung
	public String getBezeichnung() {
		return bezeichnung.get();
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung.set(bezeichnung);
	}

	public StringProperty bezeichnungProperty() {
		return bezeichnung;
	}
}
