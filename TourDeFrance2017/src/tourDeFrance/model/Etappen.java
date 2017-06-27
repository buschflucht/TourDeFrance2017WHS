package tourDeFrance.model;

import java.sql.Time;
import java.util.Date;

public class Etappen {

	private int etappenID;
	private int etappennummer;

	public int getEtappenID() {
		return etappenID;
	}

	public int getEtappennummer() {
		return etappennummer;
	}

	public Date getDatum() {
		return datum;
	}

	public String getStartort() {
		return startort;
	}

	public String getZielort() {
		return zielort;
	}

	public double getLaenge() {
		return laenge;
	}

	public int getArt() {
		return art;
	}

	public String getFahrerPlatz1() {
		return fahrerPlatz1;
	}

	public Time getSiegerzeit() {
		return siegerzeit;
	}

	public String getFahrerPlatz2() {
		return fahrerPlatz2;
	}

	public String getFahrerPlatz3() {
		return fahrerPlatz3;
	}

	public String getTeamPlatz1() {
		return teamPlatz1;
	}

	public String getTeamPlatz2() {
		return teamPlatz2;
	}

	public String getTeamPlatz3() {
		return teamPlatz3;
	}

	public String getFahrerGelb() {
		return fahrerGelb;
	}

	public String getFahrerGruen() {
		return fahrerGruen;
	}

	public String getFahrerBerg() {
		return fahrerBerg;
	}

	public String getDopingFahrer() {
		return dopingFahrer;
	}

	public String getDopingTeam() {
		return dopingTeam;
	}

	private Date datum;
	private String startort;
	private String zielort;
	private double laenge;
	private int art;
	private String fahrerPlatz1;
	private Time siegerzeit;
	private String fahrerPlatz2;
	private String fahrerPlatz3;
	private String teamPlatz1;
	private String teamPlatz2;
	private String teamPlatz3;
	private String fahrerGelb;
	private String fahrerGruen;
	private String fahrerBerg;
	private String dopingFahrer;
	private String dopingTeam;

}
