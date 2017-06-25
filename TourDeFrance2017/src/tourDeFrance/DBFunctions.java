package tourDeFrance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBFunctions {

	private static final String LOCAL_IP = "localhost";
	private static final String LOCAL_PORT = "3311";
	private static final String LOCAL_USER = "root";
	private static final String LOCAL_PW = "chestworkout";
	private static final String LOCAL_DB = "tourdefrance2017";

	private static final String LIVE_IP = "localhost";
	private static final String LIVE_PORT = "3306";
	private static final String LIVE_USER = "root";
	private static final String LIVE_PW = "beerenfalle03";
	private static final String LIVE_DB = "tourdefrance2017";

	private static final String TABLENAMEUSER = "user";
	private static final String TABLENAMEETAPPEN = "etappen";
	private static final String TABLENAMEFAHRER = "fahrer";
	private static final String TABLENAMETEAMS = "teams";
	private static final String TABLENAMERANKING = "ranking";
	private static final String TABLENAMETIPPS = "tipps";
	private static final String TABLENAMEETAPPENART = "etappenart";

	private static String aktuelleConnection = "";
	private static Connection connection = null;
	private static final String DATABASENAME = "tourdefrance2017";
	private static Statement stmt = null;

	/**
	 * Stellt Verbindung zum DBMS her
	 * 
	 * @param ip
	 * @param port
	 * @param user
	 * @param password
	 * @return
	 */
	public static String connect(String ipAdresse, String port, String benutzerName, String passwort) {

		try {

			String url = "jdbc:mariadb://" + ipAdresse + ":" + port + "?useSSL=false";
			connection = DriverManager.getConnection(url, benutzerName, passwort);
			System.out.println("Connect " + ipAdresse + "," + port + "," + benutzerName + "," + passwort);
			aktuelleConnection = "Connected to " + ipAdresse + ":" + port;

			return "Connection succeed";

		} catch (SQLException e) {
			return "failed";
		}
	}

	/**
	 * Verbindet das Programm mit der mySQL-Datenbank
	 * 
	 * @param ipAdresse
	 *            gibt die IP Adresse der Datenbank
	 * @param port
	 *            gibt den MySQL Port an
	 * @param benutzerName
	 *            Username zum Anmelden in der MySQL Datenbank
	 * @param passwort
	 *            Passwort zum Anmelden in der MySQL Datenbank
	 * @param databaseName
	 *            Datenbankname zum Anmelden in der MySQL Datenbank
	 * 
	 * @return String rt der Ok liefert oder falls Verbindung fehlgeschlagen den
	 *         jeweiligen Error
	 */
	public static String connectDB(String ipAdresse, String port, String benutzerName, String passwort,
			String database) {
		String rt;
		try {
			if (dbExists(connection, DATABASENAME)) {

				String url = "jdbc:mysql://" + ipAdresse + ":" + port + "/" + database;
				connection = DriverManager.getConnection(url, benutzerName, passwort);
				aktuelleConnection = "Connected to" + ipAdresse + ":" + port + "/" + database;
				rt = "Connection succeed";

			} else {
				rt = "Die Datenbank existiert (noch)nicht";
			}

		} catch (Exception x) {
			rt = "Exception";
		}
		return rt;

	}

	/**
	 * Pr�ft ob eine Datenbank existiert
	 * 
	 * @param con
	 *            Verbindung
	 * @param databaseName
	 *            DatenbankName
	 * @return ob eine Datenbank vorhanden ist
	 */
	private static boolean dbExists(Connection connection, String databaseName) {
		boolean dbvorhanden = false;
		try {
			if ((connection != null) && (!connection.isClosed())) {
				ResultSet resultSet = null;
				resultSet = connection.getMetaData().getCatalogs();
				resultSet.beforeFirst();
				while (resultSet.next() && (dbvorhanden == false)) {
					String dName = resultSet.getString(1);
					System.out.println(dName);
					if (databaseName.toUpperCase().equals(dName.toUpperCase())) {
						dbvorhanden = true;
					}
				}
				resultSet.close();
			}
		} catch (SQLException e) {
		}
		return dbvorhanden;

	}

	/**
	 * Schlie�e die Verbindung
	 */
	public void closeConnection() {
		aktuelleConnection = "";
		try {
			if ((connection != null) && (!connection.isClosed()))
				connection.close();
		} catch (Exception x) {
		}
	}

	/**
	 * Verbindet sich mit vorgegebenen Konstanten als Parameter fuer die connect
	 * Funktion
	 * 
	 * @return verbinde Lokal
	 */
	public static String connectLocal() {
		return connect(LOCAL_IP, LOCAL_PORT, LOCAL_USER, LOCAL_PW);
	}

	/**
	 * Verbindet sich mit vorgegebenen Konstanten als Parameter fuer die connect
	 * Funktion
	 * 
	 * @return verbinde Live
	 */
	public static String connectLIVE() {
		return connect(LIVE_IP, LIVE_PORT, LIVE_USER, LIVE_PW);
	}

	/**
	 * Verbindet sich mit vorgegebenen Konstanten als Parameter fuer die connect
	 * Funktion mit Angabe einer Datenbank
	 * 
	 * @return verbinde Lokal mit DB
	 */
	public static String connectLocalDB() {
		return connectDB(LOCAL_IP, LOCAL_PORT, LOCAL_USER, LOCAL_PW, LOCAL_DB);
	}

	/**
	 * Verbindet sich mit vorgegebenen Konstanten als Parameter fuer die connect
	 * Funktion mit Angabe einer Datenbank
	 * 
	 * @return verbinde Live mit DB
	 */
	public static String connectLIVEDB() {
		return connectDB(LIVE_IP, LIVE_PORT, LIVE_USER, LIVE_PW, LIVE_DB);
	}

	/**
	 * Diese Methode erzeugt eine Datenbank mit dem Namen als Parameter der vom
	 * User uebergeben wird
	 * 
	 * @param databaseName
	 *            Datenbankname der Datenbank die in PostgreSQL erstellt werden
	 *            soll
	 */
	public static String createDb() {
		String rt = "";
		boolean dbvorhanden = false;
		Statement stmt;
		ResultSet resultSet = null;

		try {
			resultSet = connection.getMetaData().getCatalogs();

			resultSet.beforeFirst();
			while (resultSet.next() && (dbvorhanden == false)) {

				String dName = resultSet.getString(1);
				System.out.println(dName);
				if (DATABASENAME.toUpperCase().equals(dName.toUpperCase())) {
					// falls vorhanden loeschen
					stmt = connection.createStatement();
					stmt.execute("drop schema " + dName);
					System.out.println(dName + " successfully deleted");
					dbvorhanden = true;
				}
			}
			resultSet.close();

			// Erstellt die Datenbank
			stmt = connection.createStatement();
			String databaseName2 = "CREATE DATABASE " + DATABASENAME;
			stmt.executeUpdate(databaseName2);
			rt = "succeed";

		} catch (SQLException e) {

			rt = "failed";
		}

		return rt;

	}

	/**
	 * L�dt die lokalen .csv dateien in die jeweiligen Tabellen 
	 * 
	 * @return
	 */
	public static String datenEingeben() {

		String sql = "";

		try {
			stmt = connection.createStatement();
			
			sql = "LOAD DATA LOCAL INFILE './resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps_2016/"
					+ "user2016.csv' " + "INTO TABLE user";
			
			stmt.executeQuery(sql);
			
			sql = "LOAD DATA LOCAL INFILE './resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps_2016/"
					+ "tipps2016.csv' " + "INTO TABLE tipps";
			
			stmt.executeQuery(sql);
			
			
			stmt.close();
			return "succeed";

		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * einen nach Datum und Uhrzeit sortierten Etappenplan der Tour de France
	 * 2017 mit allen in der Tabelle �etappen� enthaltenden Daten auf der
	 * Konsole ausgeben
	 * 
	 * @return
	 */
	public static String etappenplan() {

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM etappen ORDER BY datum");

			// Fetch each row from the result set
			while (rs.next()) {
				int etapnummer = rs.getInt("etappennummer");
				Date datum = rs.getDate("datum");
				String startort = rs.getString("startort");
				String zielort = rs.getString("zielort");
				Double laenge = rs.getDouble("laenge");
				int art = rs.getInt("art");

				System.out.println(
						etapnummer + "\t" + datum + "\t" + startort + "\t" + zielort + "\t" + laenge + "\t" + art);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return "";
	}

	/**
	 * Erstellt neue Tabellen in der MySQL-Datenbank sofern bereits eine Tabelle
	 * mit demselben Namen vorhanden ist, wird diese vorher geloescht
	 * 
	 * 
	 * @return liefert ob die Tabellen erfolgreich angelegt wurde nachdem
	 *         eventuell vorhandene geloescht wurden.
	 */
	public static String tabellenAnlegen() {

		Statement stmt = null;

		try {
			String sql = "";
			// Loescht alle Tabelle falls vorhanden
			stmt = connection.createStatement();

			// wegen der FOREIGN KEYs sonst loeschen nicht moeglich
			sql = "SET foreign_key_checks = 0";
			stmt.execute(sql);

			sql = "DROP TABLE IF EXISTS " + TABLENAMETIPPS;
			stmt.execute(sql);

			sql = "DROP TABLE IF EXISTS " + TABLENAMEUSER;
			stmt.execute(sql);

			sql = "DROP TABLE IF EXISTS " + TABLENAMEETAPPEN;
			stmt.execute(sql);

			sql = "DROP TABLE IF EXISTS " + TABLENAMETEAMS;
			stmt.execute(sql);

			sql = "DROP TABLE IF EXISTS " + TABLENAMEFAHRER;
			stmt.execute(sql);

			sql = "DROP TABLE IF EXISTS " + TABLENAMERANKING;
			stmt.execute(sql);

			sql = "DROP TABLE IF EXISTS " + TABLENAMEETAPPENART;
			stmt.execute(sql);

			// Erstellt die Tabelle user 
			stmt.execute("create table " + TABLENAMEUSER 
					+ "(userID int(11) not null auto_increment,userName varchar(100) not null,"
					+ "sessionID varchar(50) not null," + "vorname varchar(50) not null," + "nachname varchar(50) not null," 
					+ "passwort varchar(50) not null," + "angelegt timestamp not null default CURRENT_TIMESTAMP," 
					+ "primary key (userID))");

			// Erstellt die Tabelle etappenart 
			stmt.execute("create table " + TABLENAMEETAPPENART 
					+ "(artID int(11) not null auto_increment,"
					+ "bezeichnung varchar(50) null default null,"
					+ "primary key (artID))");

			// Erstellt die Tabelle etappen
			stmt.execute("create table " + TABLENAMEETAPPEN 
					+ "(etappenID int(11) not null auto_increment,etappennummer int(11) not null,"
					+ "datum DATETIME not null," + "startort varchar(50) not null," + "zielort varchar(50) not null," 
					+ "laenge double not null," + "art int(11) not null," + "fahrerPlatz1 varchar(50)," 
					+ "siegerzeit TIME," + "fahrerPlatz2 varchar(50)," + "fahrerPlatz3 varchar(50),"
					+ "teamPlatz1 varchar(50)," + "teamPlatz2 varchar(50),"
					+ "teamPlatz3 varchar(50)," + "fahrerGelb varchar(50),"
					+ "fahrerGruen varchar(50)," + "fahrerBerg varchar(50),"
					+ "dopingFahrer varchar(50)," + "dopingTeam varchar(50),"
					+ "primary key (etappenID),"
					+ "INDEX art_ibfk_1 (art),"
					+ "CONSTRAINT art_ibfk_1 FOREIGN KEY (art) REFERENCES etappenart(artID))");	

			// Erstellt die Tabelle teams
			stmt.execute("create table " + TABLENAMETEAMS 
					+ "(teamID int(11) not null auto_increment,teamName varchar(50) not null,"
					+ "teamBildUrl varchar(50),"
					+ "primary key (teamID))");

			// Erstellt die Tabelle fahrer
			stmt.execute("create table " + TABLENAMEFAHRER
					+ "(fahrerID int(11) not null auto_increment,startnummer int(11) not null,"
					+ "fahrerVorname varchar(50) not null," + "fahrerNachname varchar(50) not null," + "team int(11) not null," 
					+ "aktiv tinyint(1) not null," + "gesamtzeit time," + "etappensiege int(11) not null default '0'," 
					+ "punkteGruen int(11) not null default '0'," + "punkteBerg int(11) not null default '0',"
					+ "primary key (fahrerID),"
					+ "foreign key (team) references teams(teamID))");

			// Erstellt die Tabelle ranking
			stmt.execute("create table " + TABLENAMERANKING 
					+ "(rankingID int(11) not null auto_increment,datum DATETIME not null,"
					+ "userID int(11) not null," + "punkte int(11) not null default '0'," + "platz int(11) not null default '0'," 
					+ "primary key (rankingID),"
					+ "foreign key (userID) references user(userID))");

			// Erstellt die Tabelle tipps
			stmt.execute("create table " + TABLENAMETIPPS 
					+ "(tippID int(11) not null auto_increment,userID int(11) not null,etappenID int(11) not null,"
					+ "fahrerPlatz1 varchar(50)," + "fahrerPlatz2 varchar(50)," + "fahrerPlatz3 varchar(50),"
					+ "teamPlatz1 varchar(50)," + "teamPlatz2 varchar(50),"
					+ "teamPlatz3 varchar(50)," + "fahrerGelb varchar(50),"
					+ "fahrerGruen varchar(50)," + "fahrerBerg varchar(50),"
					+ "fahrerDoping varchar(50)," + "teamDoping varchar(50),"
					+ "primary key (tippID),"
					+ "foreign key (userID) references user(userID),"
					+ "foreign key (etappenID) references etappen(etappenID))");

			sql = "SET foreign_key_checks = 1";
			stmt.execute(sql);

			return "succeed";

		} catch (SQLException e) {
			e.printStackTrace();
			return "failed";
		}
	}


	public static String getAktuelleConnection() {
		return aktuelleConnection;
	}

	public static void setAktuelleConnection(String aktuelleConnection) {
		DBFunctions.aktuelleConnection = aktuelleConnection;
	}

}
