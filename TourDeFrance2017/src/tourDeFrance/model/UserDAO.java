package tourDeFrance.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tourDeFrance.DBFunctions;

public class UserDAO {

	/**
	 * Waehlt die erforderlichen Spalten aus der Datenbank aus und erzeugt eine
	 * Liste
	 * 
	 * @return User Liste
	 * @throws SQLException
	 * 
	 */
	public static List<User> selectUser() throws SQLException {
		String selectStmt = "SELECT u.vorname, u.nachname" + " FROM user u";
		Statement stmt = DBFunctions.getInstance().getConnection().createStatement();
		ResultSet rsEtappe = stmt.executeQuery(selectStmt);
		List<User> user = getUserFromResultSet(rsEtappe);
		return user;

	}

	/**
	 * Holt die Daten aus der Datenbank fuer die TableView
	 * 
	 * @param rs
	 *            ResultSet
	 * @return ArrayListe von User
	 * @throws SQLException
	 */
	public static List<User> getUserFromResultSet(ResultSet rs) throws SQLException {
		List<User> rt = new ArrayList<User>();
		while (rs.next()) {
			User us = new User();
			us.setVorName(rs.getString("vorname"));
			us.setNachName(rs.getString("nachname"));
			// us.setPunkte(rs.getInt("punkte"));
			rt.add(us);
		}
		return rt;

	}

}
