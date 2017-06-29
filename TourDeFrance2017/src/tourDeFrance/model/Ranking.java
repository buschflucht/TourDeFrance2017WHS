package tourDeFrance.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

public class Ranking {

	private IntegerProperty rankingID;
	private ObjectProperty<LocalDate> datum;
	private IntegerProperty userID;
	private IntegerProperty punkte;
	private IntegerProperty platz;

	public IntegerProperty getRankingID() {
		return rankingID;
	}

	public ObjectProperty<LocalDate> getDatum() {
		return datum;
	}

	public IntegerProperty getUserID() {
		return userID;
	}

	public IntegerProperty getPunkte() {
		return punkte;
	}

	public IntegerProperty getPlatz() {
		return platz;
	}

}
