package tourDeFrance.model;

import java.sql.Time;

public class Fahrer {

	private int fahrerID;
	private String fahrerVorname;
	private String fahrerNachname;
	private int team;
	private int aktiv;
	private Time gesamtzeit;
	private int etappensiege;
	public int getFahrerID() {
		return fahrerID;
	}
	public String getFahrerVorname() {
		return fahrerVorname;
	}
	public String getFahrerNachname() {
		return fahrerNachname;
	}
	public int getTeam() {
		return team;
	}
	public int getAktiv() {
		return aktiv;
	}
	public Time getGesamtzeit() {
		return gesamtzeit;
	}
	public int getEtappensiege() {
		return etappensiege;
	}
	public int getPunkteGruen() {
		return punkteGruen;
	}
	public int getPunkteBerg() {
		return punkteBerg;
	}
	private int punkteGruen;
	private int punkteBerg;
	
	
	
}
