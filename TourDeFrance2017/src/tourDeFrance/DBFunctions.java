package tourDeFrance;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBFunctions {

	private static DBFunctions instance;
	private String databaseName = "";

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	// Lokale Anmeldedaten
	private static final String LOCAL_IP = "localhost";
	private static final String LOCAL_PORT = "3311";
	private static final String LOCAL_USER = "root";
	private static final String LOCAL_PW = "chestworkout";
	private static final String LOCAL_DB = "tourdefrance2017";
	// Live Anmeldedaten
	private static final String LIVE_IP = "193.175.198.25";
	private static final String LIVE_PORT = "3306";
	private static final String LIVE_USER = "tdf2017_c";
	private static final String LIVE_PW = "tdf2017";
	private static final String LIVE_DB = "tourdefrance2017_c";
	// Tabellen
	private static final String TABLENAMEUSER = "user";
	private static final String TABLENAMEETAPPEN = "etappen";
	private static final String TABLENAMEFAHRER = "fahrer";
	private static final String TABLENAMETEAMS = "teams";
	private static final String TABLENAMERANKING = "ranking";
	private static final String TABLENAMETIPPS = "tipps";
	private static final String TABLENAMEETAPPENART = "etappenart";
	private static final String TABLENAMEEERGEBNISSEBERG = "ergebnisseberg";
	private static final String TABLENAMEERGEBNISSEGELB = "ergebnissegelb";
	private static final String TABLENAMEERGEBNISSEGRUEN = "ergebnissegruen";
	// Verbindungen
	private static final String CONNECTION_OK = "Connection succeed";
	private String aktuelleConnection = "";
	private Connection connection = null;
	private static Statement stmt = null;

	// Singleton
	public static DBFunctions getInstance() {
		if (instance == null)
			instance = new DBFunctions();
		return instance;
	}

	private DBFunctions() {
		super();
	}

	public Connection getConnection() {
		return connection;
	}

	/**
	 * 
	 * @param ipAdresse
	 *            IP Adresse der Datenbank
	 * @param port
	 *            Port
	 * @param benutzerName
	 *            Username zum Anmelden in der Datenbank
	 * @param passwort
	 *            Passwort zum Anmelden in der Datenbank
	 * @return
	 */
	public String connect(String ipAdresse, String port, String benutzerName, String passwort) {
		String rt;
		try {

			String url = "jdbc:mariadb://" + ipAdresse + ":" + port + "?useSSL=false";
			connection = DriverManager.getConnection(url, benutzerName, passwort);
			aktuelleConnection = "Connected to " + ipAdresse + ":" + "\n" + port;

			rt = CONNECTION_OK;

		} catch (SQLException e) {
			rt = "Connection failed! Check output console" + e;
			e.printStackTrace();

		}
		return rt;
	}

	/**
	 * Stellt eine Verbindung zur Datenbank her mit Auswahl einer Datenbank
	 * 
	 * @param ipAdresse
	 *            IP Adresse der Datenbank
	 * @param port
	 *            Port
	 * @param benutzerName
	 *            Username zum Anmelden in der Datenbank
	 * @param passwort
	 *            Passwort zum Anmelden in der Datenbank
	 * @param databaseName
	 *            Datenbankname zum Anmelden in der Datenbank
	 * 
	 * @return String rt der Ok liefert oder falls Verbindung fehlgeschlagen den
	 *         jeweiligen Error
	 */
	public String connectDB(String ipAdresse, String port, String benutzerName, String passwort, String database) {
		String rt;
		try {
			if (CONNECTION_OK.equals(connect(ipAdresse, port, benutzerName, passwort))
					&& (dbExists(connection, LIVE_DB) || (dbExists(connection, LOCAL_DB)))) {

				String url = "jdbc:mysql://" + ipAdresse + ":" + port + "/" + database;
				connection = DriverManager.getConnection(url, benutzerName, passwort);
				aktuelleConnection = "Connected to " + ipAdresse + ":" + "\n" + port + "/" + database;

				rt = CONNECTION_OK;

			} else {
				rt = "Die Datenbank existiert nicht";
			}

		} catch (Exception x) {
			System.out.println("Connection failed! Check output console" + x);
			x.printStackTrace();
			rt = "Exception:" + x.getLocalizedMessage();
		}
		return rt;
	}

	/**
	 * Schlieﬂt die Verbindung
	 * 
	 * @throws Exception
	 */
	public void closeConnection() throws SQLException {
		aktuelleConnection = "";
		try {
			if ((connection != null) && (!connection.isClosed()))
				connection.close();
		} catch (Exception x) {
			throw x;
		}
	}

	/**
	 * Verbindet sich mit gesetzten Connection Konstanten Lokal
	 * 
	 * @return verbinde Lokal
	 */
	public String connectLocal() {
		databaseName = LOCAL_DB;
		return connect(LOCAL_IP, LOCAL_PORT, LOCAL_USER, LOCAL_PW);
	}

	/**
	 * Verbindet sich mit gesetzten Connection Konstanten Live
	 * 
	 * @return verbinde Live
	 */
	public String connectLIVE() {
		databaseName = LIVE_DB;
		return connect(LIVE_IP, LIVE_PORT, LIVE_USER, LIVE_PW);
	}

	/**
	 * Verbindet sich mit gesetzten Connection Konstanten Lokal mit Auswahl einer
	 * Datenbank
	 * 
	 * @return verbinde Lokal mit DB
	 */
	public String connectLocalDB() {

		return connectDB(LOCAL_IP, LOCAL_PORT, LOCAL_USER, LOCAL_PW, LOCAL_DB);
	}

	/**
	 * Verbindet sich mit gesetzten Connection Konstanten Live mit Auswahl einer
	 * Datenbank
	 * 
	 * @return verbinde Live mit DB
	 */
	public String connectLIVEDB() {

		return connectDB(LIVE_IP, LIVE_PORT, LIVE_USER, LIVE_PW, LIVE_DB);
	}

	/**
	 * Prueft ob angegebene Datenbank existiert
	 * 
	 * @param con
	 *            Verbindung
	 * @param databaseName
	 *            DatenbankName
	 * @return ob eine Datenbank vorhanden ist
	 * @throws SQLException
	 */
	private boolean dbExists(Connection connection, String databaseName) throws SQLException {
		boolean dbvorhanden = false;
		try {
			if ((connection != null) && (!connection.isClosed())) {
				ResultSet resultSet = null;
				resultSet = connection.getMetaData().getCatalogs();
				resultSet.beforeFirst();
				while (resultSet.next() && (dbvorhanden == false)) {
					String dName = resultSet.getString(1);
					if (databaseName.toUpperCase().equals(dName.toUpperCase())) {
						dbvorhanden = true;
					}
				}
				resultSet.close();
			}
		} catch (SQLException e) {
			throw e;
		}
		return dbvorhanden;

	}

	/**
	 * Erzeugt eine Datenbank in MariaDB, falls bereits die Datenbank vorhanden ist
	 * soll diese zuvor geloescht werden
	 * 
	 * @param dataBaseName
	 * 
	 * @throws SQLException
	 */
	public String createDb(String dataBaseName) throws SQLException {
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
				if (dataBaseName.toUpperCase().equals(dName.toUpperCase())) {
					// loescht Datenbank
					stmt = connection.createStatement();
					stmt.execute("drop schema " + dName);
					dbvorhanden = true;
				}
			}
			resultSet.close();

			// Erstellt die Datenbank
			stmt = connection.createStatement();
			String databaseName2 = "CREATE DATABASE " + dataBaseName;
			stmt.executeUpdate(databaseName2);
			rt = "succeed";

		} catch (SQLException e) {
			rt = "failed";
			throw e;

		}

		return rt;

	}

	/**
	 * Erstellt neue Tabellen in der MySQL-Datenbank sofern bereits eine Tabelle mit
	 * demselben Namen vorhanden ist, wird diese vorher geloescht
	 * 
	 * 
	 * @return ob Tabellen erfolgreich erstellt wurden
	 */
	public String tabellenAnlegen() {

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

			sql = "DROP TABLE IF EXISTS " + TABLENAMEEERGEBNISSEBERG;
			stmt.execute(sql);

			sql = "DROP TABLE IF EXISTS " + TABLENAMEERGEBNISSEGELB;
			stmt.execute(sql);

			sql = "DROP TABLE IF EXISTS " + TABLENAMEERGEBNISSEGRUEN;
			stmt.execute(sql);

			// Tabelle user
			stmt.execute("create table " + TABLENAMEUSER
					+ "(userID int(11) not null auto_increment,userName varchar(100) not null,"
					+ "sessionID varchar(50) not null," + "vorname varchar(50) not null,"
					+ "nachname varchar(50) not null," + "passwort varchar(50) not null,"
					+ "angelegt timestamp not null default CURRENT_TIMESTAMP," + "primary key (userID))");

			// Tabelle etappenart
			stmt.execute("create table " + TABLENAMEETAPPENART + "(artID int(11) not null auto_increment,"
					+ "bezeichnung varchar(50) null default null," + "primary key (artID))");
			// Etappenart befuellen
			stmt.executeUpdate("INSERT INTO " + TABLENAMEETAPPENART + " " + "VALUES (1, 'Einzelzeitfahren')");
			stmt.executeUpdate("INSERT INTO " + TABLENAMEETAPPENART + " " + "VALUES (2, 'Flachetappe')");
			stmt.executeUpdate("INSERT INTO " + TABLENAMEETAPPENART + " " + "VALUES (3, 'Gebirge')");
			stmt.executeUpdate("INSERT INTO " + TABLENAMEETAPPENART + " " + "VALUES (4, 'Huegelig')");

			// Tabelle etappen
			stmt.execute("create table " + TABLENAMEETAPPEN
					+ "(etappenID int(11) not null auto_increment,etappennummer int(11) not null,"
					+ "datum DATETIME not null," + "startort varchar(50) not null," + "zielort varchar(50) not null,"
					+ "laenge double not null," + "art int(11) not null," + "fahrerPlatz1 varchar(50),"
					+ "siegerzeit TIME," + "fahrerPlatz2 varchar(50)," + "fahrerPlatz3 varchar(50),"
					+ "teamPlatz1 varchar(50)," + "teamPlatz2 varchar(50)," + "teamPlatz3 varchar(50),"
					+ "fahrerGelb varchar(50)," + "fahrerGruen varchar(50)," + "fahrerBerg varchar(50),"
					+ "dopingFahrer varchar(50)," + "dopingTeam varchar(50)," + "primary key (etappenID),"
					+ "INDEX art_ibfk_1 (art),"
					+ "CONSTRAINT art_ibfk_1 FOREIGN KEY (art) REFERENCES etappenart(artID))");

			// Tabelle teams
			stmt.execute("create table " + TABLENAMETEAMS
					+ "(teamID int(11) not null auto_increment,teamName varchar(50) not null,"
					+ "teamBildUrl varchar(50)," + "primary key (teamID))");

			// Tabelle fahrer
			stmt.execute("create table " + TABLENAMEFAHRER
					+ "(fahrerID int(11) not null auto_increment,startnummer int(11) not null,"
					+ "fahrerVorname varchar(50) not null," + "fahrerNachname varchar(50) not null,"
					+ "team int(11) not null," + "aktiv tinyint(1) not null," + "gesamtzeit time,"
					+ "etappensiege int(11) not null default '0'," + "punkteGruen int(11) not null default '0',"
					+ "punkteBerg int(11) not null default '0'," + "primary key (fahrerID),"
					+ "foreign key (team) references teams(teamID))");

			// Tabelle ranking
			stmt.execute("create table " + TABLENAMERANKING
					+ "(rankingID int(11) not null auto_increment,datum DATETIME not null," + "userID int(11) not null,"
					+ "punkte int(11) not null default '0'," + "platz int(11) not null default '0',"
					+ "primary key (rankingID)," + "foreign key (userID) references user(userID))");

			// Tabelle tipps
			stmt.execute("create table " + TABLENAMETIPPS
					+ "(tippID int(11) not null auto_increment,userID int(11) not null,etappenID int(11) not null,"
					+ "fahrerPlatz1 varchar(50)," + "fahrerPlatz2 varchar(50)," + "fahrerPlatz3 varchar(50),"
					+ "teamPlatz1 varchar(50)," + "teamPlatz2 varchar(50)," + "teamPlatz3 varchar(50),"
					+ "fahrerGelb varchar(50)," + "fahrerGruen varchar(50)," + "fahrerBerg varchar(50),"
					+ "fahrerDoping varchar(50)," + "teamDoping varchar(50)," + "primary key (tippID),"
					+ "foreign key (userID) references user(userID),"
					+ "foreign key (etappenID) references etappen(etappenID))");

			// Tabelle ergebnissberg
			stmt.execute("create table " + TABLENAMEEERGEBNISSEBERG
					+ "(ergebnisID int(11) not null auto_increment,etappe int(11) not null,startnummer int(11) not null,"
					+ "punkteTemp varchar(50) not null collate 'utf8_german2_ci'," + "punkte int(11) not null,"
					+ "primary key (ergebnisID))" + " COLLATE='utf8_german2_ci'" + " ENGINE=InnoDB"
					+ " AUTO_INCREMENT=300");

			// Tabelle ergebnissegelb
			stmt.execute("create table " + TABLENAMEERGEBNISSEGELB
					+ "(ergebnisID int(11) not null auto_increment,etappe int(11) not null,platz int(11) not null,"
					+ "startnummer int(11) not null," + "zeit varchar(50) not null collate 'utf8_german2_ci',"
					+ "primary key (ergebnisID))" + " COLLATE='utf8_german2_ci'" + " ENGINE=InnoDB"
					+ " AUTO_INCREMENT=5356");

			// Tabelle ergebnissegruen
			stmt.execute("create table " + TABLENAMEERGEBNISSEGRUEN
					+ "(ergebnisID int(11) not null auto_increment,etappe int(11) not null,startnummer int(11) not null,"
					+ "punkteTemp varchar(50) not null collate 'utf8_german2_ci'," + "punkte int(11) not null,"
					+ "primary key (ergebnisID))" + " COLLATE='utf8_german2_ci'" + " ENGINE=InnoDB"
					+ " AUTO_INCREMENT=620");

			sql = "SET foreign_key_checks = 1";
			stmt.execute(sql);

			return "succeed";

		} catch (SQLException e) {
			e.printStackTrace();
			return "failed";
		}
	}

	/**
	 * Laedt Daten in eine tabelle in der Datenbank
	 * 
	 * @param pfad
	 *            URL zur Datei
	 * @param table
	 *            tabellenName
	 * @return erfolgreich oder nicht
	 */
	public String datenEinlesen(String pfad, String table) {

		String sql = "";

		try {
			stmt = connection.createStatement();

			sql = "LOAD DATA LOCAL INFILE '" + pfad + "' INTO TABLE " + table;

			stmt.executeQuery(sql);

			stmt.close();
			return "succeed";

		} catch (SQLException e) {
			e.printStackTrace();
			return "failed";
		}
	}

	/**
	 * Liest alle csv Dateien ein aus dem Ordner resources
	 * 
	 * @return erfolgreich oder nicht
	 */
	public String ergebnisseEingeben() {

		File fileBerg = new File("C:/Users/Mehmet/git/TourDeFrance2017WHS/TourDeFrance2017/resources/"
				+ "Testdaten_Ergebnisse_Bergwertung_2016");
		File fileGelb = new File("C:/Users/Mehmet/git/TourDeFrance2017WHS/TourDeFrance2017/resources/"
				+ "Testdaten_Ergebnisse_Gesamtwertung_2016");
		File fileGruen = new File("C:/Users/Mehmet/git/TourDeFrance2017WHS/TourDeFrance2017/resources/"
				+ "Testdaten_Ergebnisse_Punktewertung_2016");

		String sql = "";

		try {
			stmt = connection.createStatement();

			for (int i = 1; i < fileBerg.list().length; i++) {

				sql = "LOAD DATA LOCAL INFILE './resources/Testdaten_Ergebnisse_Bergwertung_2016/" + "ergebnisseberg"
						+ i + "-2016.csv' " + "INTO TABLE ergebnisseberg";

				stmt.execute(sql);
			}

			for (int i = 1; i < fileGelb.list().length; i++) {

				sql = "LOAD DATA LOCAL INFILE './resources/Testdaten_Ergebnisse_Gesamtwertung_2016/" + "ergebnissegelb"
						+ i + "-2016.csv' " + "INTO TABLE ergebnissegelb";

				stmt.execute(sql);
			}

			for (int i = 1; i < fileGruen.list().length; i++) {

				sql = "LOAD DATA LOCAL INFILE './resources/Testdaten_Ergebnisse_Punktewertung_2016/" + "ergebnissegruen"
						+ i + "-2016.csv' " + "INTO TABLE ergebnissegruen";

				stmt.execute(sql);
			}

			stmt.close();
			return "succeed";

		} catch (SQLException e) {
			System.out.println(e);
			return "failed";
		}
	}

	/**
	 * Vergibt Punkte fuer Tipps
	 * 
	 * @throws SQLException
	 */
	public void vergebePunkte() throws SQLException {
		ResultSet tip = stmt.executeQuery("SELECT * FROM tipps ORDER BY userID");
		ResultSet eid = stmt.executeQuery("SELECT * FROM etappen ORDER BY etappenID");
		ResultSet points = stmt.executeQuery("SELECT * FROM ranking ORDER BY userID");

		boolean Continue = true;

		while (Continue) {
			if (tip.isLast()) {
				Continue = false;
			}
			if (eid.isLast()) {
				int punkte = 0;
				int punkteOld = points.getInt("punkte");

				if (eid.getString("fahrerPlatz1").equals(tip.getString("fahrerPlatz1"))) {
					punkte += 15;
				}
				if (eid.getString("fahrerPlatz2").equals(tip.getString("fahrerPlatz2"))) {
					punkte += 12;
				}
				if (eid.getString("fahrerPlatz3").equals(tip.getString("fahrerPlatz3"))) {
					punkte += 10;
				}
				if (eid.getString("teamPlatz1").equals(tip.getString("teamPlatz1"))) {
					punkte += 15;
				}
				if (eid.getString("teamPlatz2").equals(tip.getString("teamPlatz2"))) {
					punkte += 12;
				}
				if (eid.getString("teamPlatz3").equals(tip.getString("teamPlatz3"))) {
					punkte += 10;
				}
				if (eid.getString("fahrerGelb").equals(tip.getString("fahrerGelb"))) {
					punkte += 10;
				}
				if (eid.getString("fahrerGruen").equals(tip.getString("fahrerGruen"))) {
					punkte += 8;
				}
				if (eid.getString("fahrerBerg").equals(tip.getString("fahrerBerg"))) {
					punkte += 8;
				}
				if (eid.getString("fahrerDoping").equals(tip.getString("fahrerDoping"))) {
					punkte += 20;
				} else {
					if (tip.getString("fahrerDoping") != null) {
						punkte -= 3;
					}
				}
				if (eid.getString("teamDoping").equals(tip.getString("teamDoping"))) {
					punkte += 20;
				} else {
					if (tip.getString("teamDoping") != null) {
						punkte -= 3;
					}
				}
				points.updateInt("punkte", punkteOld + punkte);
				tip.next();
				points.next();
			} else {
				eid.next();
			}
		}
	}

	/**
	 * Gibt Spielern einen Platz im Ranking
	 * 
	 * @throws SQLException
	 */
	public void vergebePlatz() throws SQLException {
		ResultSet rank = stmt.executeQuery("SELECT * FROM ranking ORDER BY punkte DESC LIMIT 1000");
		ResultSet rankPrev = stmt.executeQuery("SELECT * FROM ranking ORDER BY punkte DESC LIMIT 1000");

		boolean Cont = true;

		while (Cont) {
			if (rank.isLast()) {
				Cont = false;
			}

			int rang = 1;

			if (rank.isFirst()) {
				rank.updateInt("platz", rang);
				rank.next();
			} else {
				if (rank.getInt("punkte") == rankPrev.getInt("punkte")) {
					rank.updateInt("platz", rang);
				} else {
					rang++;
					rank.updateInt("platz", rang);
					rank.next();
					rankPrev.next();
				}
			}
		}
	}

	/**
	 * Bestimmt Anzahl der Etappensiege eines Fahrers
	 * 
	 * @throws SQLException
	 */
	public void etappenSiege() throws SQLException {
		ResultSet win = stmt.executeQuery("SELECT * FROM etappen ORDER BY etappenID");
		ResultSet point = stmt.executeQuery("SELECT * FROM fahrer ORDER BY fahrerID");

		if (win.getString("fahrerPlatz1")
				.equals(point.getString("fahrerVorname") + " " + point.getString("fahrerNachname"))) {
			point.updateInt("etappensiege", point.getInt("etappensiege") + 1);
		} else {
			point.next();
		}
	}

	// Bestimmt ob Fahrer bei der letzen Etappe dabei war / noch aktiv ist.
	public void fahrerAktiv() throws SQLException {
		ResultSet active = stmt.executeQuery("SELECT * FROM ergebnissegelb ORDER BY startnummer");
		ResultSet in = stmt.executeQuery("SELECT * FROM fahrer ORDER BY startnummer");

		while (!in.isAfterLast()) {
			in.updateInt("akiv", 0);
			active.first();
			while (!active.isAfterLast()) {
				if (in.getInt("startnummer") == active.getInt("startnummer")) {
					in.updateInt("aktiv", 1);
					break;
				} else {
					active.next();
				}
			}
			in.next();

		}
	}

	/**
	 * Bestimmt Trger des Gelben Tikots nach der AKTUELLEN Etappe
	 * 
	 * @throws SQLException
	 */
	public void gelbesTrikot() throws SQLException {
		ResultSet fahrertime = stmt.executeQuery("SELECT * FROM ergebnissegelb ORDER BY startnummer");
		ResultSet yellowT = stmt.executeQuery("SELECT * FROM ergebnissegelb ORDER BY zeit ASC LIMIT 1000");
		ResultSet et = stmt.executeQuery("SELECT * FROM etappen ORDER BY etappennummer DESC LIMIT 1000");
		ResultSet fa = stmt.executeQuery("SELECT * FROM fahrer ORDER BY startnummer DESC LIMIT 1000");

		fahrertime.first();
		int sn = fahrertime.getInt("startnummer");

		while (!fahrertime.isAfterLast()) {
			while (fahrertime.getInt("startnummer") == sn) {
				int ztime = 0;

				if (fahrertime.getInt("etappe") == 1) {
					ztime = fahrertime.getInt("zeit");
					fahrertime.updateInt("zeit", fahrertime.getInt("zeit"));
				} else {
					fahrertime.updateInt("zeit", ztime + fahrertime.getInt("zeit"));
					ztime = fahrertime.getInt("zeit");
					fahrertime.next();
				}
			}
			sn = fahrertime.getInt("startnummer");
		}
		yellowT.first();
		fa.first();

		if (yellowT.getInt("startnummer") == fa.getInt("startnummer")) {
			et.updateString("fahrerGelb", fa.getString("fahrerVorname") + " " + fa.getString("fahrerNachname"));
		} else {
			while (!fa.isAfterLast()) {
				fa.next();
			}
		}
	}

	/**
	 * Bestimme Trger des Gruenen Trikots nach der AKTUELLEN Etappe
	 * 
	 * @throws SQLException
	 */
	public void gruenesTrikot() throws SQLException {
		ResultSet tGruen = stmt.executeQuery("SELECT * FROM ergebnissegruen ORDER BY punkte DESC LIMIT 1000");
		ResultSet etagruen = stmt.executeQuery("SELECT * FROM etappen ORDER BY etappenID");
		ResultSet tFahrer = stmt.executeQuery("SELECT * FROM fahrer ORDER BY startnummer");

		if (etagruen.isLast()) {
			if (tGruen.isFirst()) {
				while (!tFahrer.isAfterLast()) {
					if (tFahrer.getInt("startnummer") == tGruen.getInt("startnummer")) {
						etagruen.updateString("fahrerGelb",
								tFahrer.getString("fahrerVorame") + " " + tFahrer.getString("fahrerNachame"));
						break;
					} else {
						tFahrer.next();
					}
				}

			} else {
				tGruen.previous();
			}
		} else {
			etagruen.next();
		}

	}

	/**
	 * Bestimme Traeger des Berg-Trikots nach der AKTUELLEN Etappe
	 * 
	 * @throws SQLException
	 */
	public void bergTrikot() throws SQLException {
		ResultSet tBerg = stmt.executeQuery("SELECT * FROM ergebnissegelb ORDER BY punte DESC LIMIT 1000");
		ResultSet etaberg = stmt.executeQuery("SELECT * FROM etappen ORDER BY etappenID");
		ResultSet tFahrer = stmt.executeQuery("SELECT * FROM fahrer ORDER BY startnummer");

		if (etaberg.isLast()) {
			if (tBerg.isFirst()) {
				while (!tFahrer.isAfterLast()) {
					if (tFahrer.getInt("startnummer") == tBerg.getInt("startnummer")) {
						etaberg.updateString("fahrerGelb",
								tFahrer.getString("fahrerVorame") + " " + tFahrer.getString("fahrerNachame"));
						break;
					} else {
						tFahrer.next();
					}
				}

			} else {
				tBerg.previous();
			}
		} else {
			etaberg.next();
		}

	}

	/**
	 * Getter fuer aktuelle Connection
	 * 
	 * @return
	 */
	public String getAktuelleConnection() {
		return aktuelleConnection;
	}

	/**
	 * Setter fuer aktuelle Connection
	 * 
	 * @param aktuelleConnection
	 */
	public void setAktuelleConnection(String aktuelleConnection) {
		this.aktuelleConnection = aktuelleConnection;
	}

}
