package tourDeFrance.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Teams {

	private IntegerProperty teamID;
	private StringProperty teamName;
	private StringProperty teamBildUrl;
	
	public IntegerProperty getTeamID() {
		return teamID;
	}
	public StringProperty getTeamName() {
		return teamName;
	}
	public StringProperty getTeamBildUrl() {
		return teamBildUrl;
	}
	
	

	
	
}
