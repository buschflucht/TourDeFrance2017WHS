package tourDeFrance.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Etappenart {

	private IntegerProperty artID;
	private StringProperty bezeichnung;

	// ArtID
	public int getArtID() {
		return artID.get();
	}

	public void setArtID(int artID) {
		this.artID.set(artID);
	}

	public IntegerProperty etappenIDProperty() {
		return artID;
	}

	// bezeichnung
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
