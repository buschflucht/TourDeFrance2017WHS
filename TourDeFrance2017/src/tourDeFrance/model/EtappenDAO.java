package tourDeFrance.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tourDeFrance.DBFunctions;

public class EtappenDAO {
	/**
	 * Waehlt die erforderlichen Spalten aus der Datenbank aus und erzeugt eine
	 * Liste
	 * 
	 * @return Etappen Liste
	 * @throws SQLException
	 */
	public static List<Etappe> selectEtappen() throws SQLException {
		String selectStmt = "SELECT e.etappenID, e.startort, e.zielort, e.laenge, e.datum, a.bezeichnung"
				+ " FROM etappen e left join etappenart a on e.art=a.artID";
		Statement stmt = DBFunctions.getInstance().getConnection().createStatement();
		ResultSet rsEtappe = stmt.executeQuery(selectStmt);
		List<Etappe> etappen = getEtappenFromResultSet(rsEtappe);
		return etappen;

	}

	/**
	 * Waehlt die erforderlichen Spalten aus der Datenbank aus und erzeugt eine
	 * Liste
	 * 
	 * @return etappenListe
	 * @throws SQLException
	 */
	public static List<Etappe> selectErgebnisse() throws SQLException {
		String selectStmt = "SELECT e.etappennummer, e.datum" + " FROM etappen e  WHERE e.datum < NOW()";
		Statement stmt = DBFunctions.getInstance().getConnection().createStatement();
		ResultSet rsEtappe = stmt.executeQuery(selectStmt);
		List<Etappe> etappen = getErgebnisseFromResultSet(rsEtappe);
		return etappen;

	}

	/**
	 * Holt die Daten aus der Datenbank fuer die TableView
	 * 
	 * @param rs
	 *            ResultSet
	 * @return ArrayListe von Etappe
	 * @throws SQLException
	 */
	public static List<Etappe> getErgebnisseFromResultSet(ResultSet rs) throws SQLException {
		List<Etappe> rt = new ArrayList<Etappe>();
		while (rs.next()) {
			Etappe ep = new Etappe();
			ep.setEtappenNummer(rs.getInt("etappennummer"));
			Timestamp t = rs.getTimestamp("datum");
			LocalDateTime d = t.toLocalDateTime();
			ep.setDatum(d.toLocalDate());
			rt.add(ep);
		}
		return rt;

	}

	/**
	 * Holt die Daten aus der Datenbank fuer die TableView
	 * 
	 * @param rs
	 *            ResultSet
	 * @return ArrayListe von Etappe
	 * @throws SQLException
	 */
	public static List<Etappe> getEtappenFromResultSet(ResultSet rs) throws SQLException {
		List<Etappe> rt = new ArrayList<Etappe>();
		while (rs.next()) {
			Etappe ep = new Etappe();
			ep.setEtappenID(rs.getInt("etappenID"));
			ep.setStartOrt(rs.getString("startort"));
			ep.setZielOrt(rs.getString("zielort"));
			ep.setLaenge(rs.getDouble("laenge"));
			Timestamp t = rs.getTimestamp("datum");
			LocalDateTime d = t.toLocalDateTime();
			ep.setDatum(d.toLocalDate());
			ep.setZeit(d.toLocalTime());
			ep.setBezeichnung(rs.getString("bezeichnung"));
			rt.add(ep);
		}
		return rt;

	}

}
