package tourDeFrance;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

public class DBFunctions {

	private static DBFunctions instance;
	// Lokale Anmeldedaten
	private static final String LOCAL_IP = "localhost";
	private static final String LOCAL_PORT = "3306";
	private static final String LOCAL_USER = "root";
	private static final String LOCAL_PW = "beerenfalle03";
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
	private static final String TABLENAMEERGEBNISSEBERG = "ergebnisseberg";
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
	 * Stellt Verbindung zur Datenbank her ohne Auswahl einer Datenbank
	 * 
	 * @param ip
	 * @param port
	 * @param user
	 * @param password
	 * @return erfolgreiche Verbindung
	 */
	public String connect(String ipAdresse, String port, String benutzerName, String passwort) {

		try {

			String url = "jdbc:mariadb://" + ipAdresse + ":" + port + "?useSSL=false";
			connection = DriverManager.getConnection(url, benutzerName, passwort);
			System.out.println("Connect " + ipAdresse + "," + port + "," + benutzerName + "," + passwort);
			aktuelleConnection = "Connected: " + ipAdresse + ":" + port;

			return CONNECTION_OK;

		} catch (SQLException e) {
			System.out.println("Connection failed! Check output console" + e);
			e.printStackTrace();
			return "failed";
		}
	}

	/**
	 * Stellt eine Verbindung zur Datenbank her mit Auswahl einer Datenbank
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
	public String connectDB(String ipAdresse, String port, String benutzerName, String passwort, String database) {
		String rt;
		try {
			if (CONNECTION_OK.equals(connect(ipAdresse, port, benutzerName, passwort))
					&& (dbExists(connection, LIVE_DB))) {

				String url = "jdbc:mysql://" + ipAdresse + ":" + port + "/" + database;
				connection = DriverManager.getConnection(url, benutzerName, passwort);
				aktuelleConnection = "Connected:" + ipAdresse + ":" + port + "/" + database;

				rt = CONNECTION_OK;

			} else {
				rt = "Die Datenbank existiert (noch)nicht";
			}

		} catch (Exception x) {
			System.out.println("Connection failed! Check output console" + x);
			x.printStackTrace();
			rt = "Exception:" + x.getLocalizedMessage();
		}
		return rt;
	}

	/**
	 * Schlie�t die Verbindung
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
	 * Verbindet sich mit vorgegebenen Konstanten als Parameter fuer die connect
	 * Funktion
	 * 
	 * @return verbinde Lokal
	 */
	public String connectLocal() {
		return connect(LOCAL_IP, LOCAL_PORT, LOCAL_USER, LOCAL_PW);
	}

	/**
	 * Verbindet sich mit vorgegebenen Konstanten als Parameter fuer die connect
	 * Funktion
	 * 
	 * @return verbinde Live
	 */
	public String connectLIVE() {
		return connect(LIVE_IP, LIVE_PORT, LIVE_USER, LIVE_PW);
	}

	/**
	 * Verbindet sich mit vorgegebenen Konstanten als Parameter fuer die connect
	 * Funktion mit Angabe einer Datenbank
	 * 
	 * @return verbinde Lokal mit DB
	 */
	public String connectLocalDB() {
		return connectDB(LOCAL_IP, LOCAL_PORT, LOCAL_USER, LOCAL_PW, LOCAL_DB);
	}

	/**
	 * Verbindet sich mit vorgegebenen Konstanten als Parameter fuer die connect
	 * Funktion mit Angabe einer Datenbank
	 * 
	 * @return verbinde Live mit DB
	 */
	public String connectLIVEDB() {
		return connectDB(LIVE_IP, LIVE_PORT, LIVE_USER, LIVE_PW, LIVE_DB);
	}

	/**
	 * Pr�ft ob eine Datenbank existiert
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
					System.out.println(dName);
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
	 * @param databaseName
	 * 
	 * @throws SQLException
	 */
	public String createDb() throws SQLException {
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
				if (LIVE_DB.toUpperCase().equals(dName.toUpperCase())) {
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
			String databaseName2 = "CREATE DATABASE " + LIVE_DB;
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
	 * @return liefert ob die Tabellen erfolgreich angelegt wurde nachdem eventuell
	 *         vorhandene geloescht wurden.
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

			sql = "DROP TABLE IF EXISTS " + TABLENAMEERGEBNISSEBERG;
			stmt.execute(sql);
			sql = "DROP TABLE IF EXISTS " + TABLENAMEERGEBNISSEGELB;
			stmt.execute(sql);
			sql = "DROP TABLE IF EXISTS " + TABLENAMEERGEBNISSEGRUEN;
			stmt.execute(sql);


			// Erstellt die Tabelle user
			stmt.execute("create table " + TABLENAMEUSER
					+ "(userID int(11) not null auto_increment,userName varchar(100) not null,"
					+ "sessionID varchar(50) not null," + "vorname varchar(50) not null,"
					+ "nachname varchar(50) not null," + "passwort varchar(50) not null,"
					+ "angelegt timestamp not null default CURRENT_TIMESTAMP," + "primary key (userID))");

			// Erstellt die Tabelle etappenart
			stmt.execute("create table " + TABLENAMEETAPPENART + "(artID int(11) not null auto_increment,"
					+ "bezeichnung varchar(50) null default null," + "primary key (artID))");
			// Bef�llt die Tabelle Etappenart
			stmt.executeUpdate("INSERT INTO " + TABLENAMEETAPPENART + " " + "VALUES (1, 'Einzelzeitfahren')");
			stmt.executeUpdate("INSERT INTO " + TABLENAMEETAPPENART + " " + "VALUES (2, 'Flachetappe')");
			stmt.executeUpdate("INSERT INTO " + TABLENAMEETAPPENART + " " + "VALUES (3, 'Gebirge')");
			stmt.executeUpdate("INSERT INTO " + TABLENAMEETAPPENART + " " + "VALUES (4, 'Huegelig')");

			// Erstellt die Tabelle etappen
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

			// Erstellt die Tabelle teams
			stmt.execute("create table " + TABLENAMETEAMS
					+ "(teamID int(11) not null auto_increment,teamName varchar(50) not null,"
					+ "teamBildUrl varchar(50)," + "primary key (teamID))");

			// Erstellt die Tabelle fahrer
			stmt.execute("create table " + TABLENAMEFAHRER
					+ "(fahrerID int(11) not null auto_increment,startnummer int(11) not null,"
					+ "fahrerVorname varchar(50) not null," + "fahrerNachname varchar(50) not null,"
					+ "team int(11) not null," + "aktiv tinyint(1) not null," + "gesamtzeit time,"
					+ "etappensiege int(11) not null default '0'," + "punkteGruen int(11) not null default '0',"
					+ "punkteBerg int(11) not null default '0'," + "primary key (fahrerID),"
					+ "foreign key (team) references teams(teamID))");

			// Erstellt die Tabelle ranking
			stmt.execute("create table " + TABLENAMERANKING
					+ "(rankingID int(11) not null auto_increment,datum DATETIME not null," + "userID int(11) not null,"
					+ "punkte int(11) not null default '0'," + "platz int(11) not null default '0',"
					+ "primary key (rankingID)," + "foreign key (userID) references user(userID))");

			// Erstellt die Tabelle tipps
			stmt.execute("create table " + TABLENAMETIPPS
					+ "(tippID int(11) not null auto_increment,userID int(11) not null,etappenID int(11) not null,"
					+ "fahrerPlatz1 varchar(50)," + "fahrerPlatz2 varchar(50)," + "fahrerPlatz3 varchar(50),"
					+ "teamPlatz1 varchar(50)," + "teamPlatz2 varchar(50)," + "teamPlatz3 varchar(50),"
					+ "fahrerGelb varchar(50)," + "fahrerGruen varchar(50)," + "fahrerBerg varchar(50),"
					+ "fahrerDoping varchar(50)," + "teamDoping varchar(50)," + "primary key (tippID),"
					+ "foreign key (userID) references user(userID),"
					+ "foreign key (etappenID) references etappen(etappenID))");
			// Tabelle ergebnisseberg
			stmt.execute("CREATE TABLE " + TABLENAMEERGEBNISSEBERG 
					+"	(ergebnisID INT(11) NOT NULL AUTO_INCREMENT,"
					+"	etappe INT(11) NOT NULL,"
					+"	startnummer INT(11) NOT NULL,"
					+"	punkteTemp VARCHAR(50) NOT NULL COLLATE 'utf8_german2_ci',"
					+"	punkte INT(11) NOT NULL,"
					+"	PRIMARY KEY (ergebnisID)"
					+")"
					+" COLLATE='utf8_german2_ci'"
					+" ENGINE=InnoDB"
					+" AUTO_INCREMENT=300"
					);
			// Tabelle ergebnissegelb
			stmt.execute("CREATE TABLE "  + TABLENAMEERGEBNISSEGELB 
					+"	(ergebnisID INT(11) NOT NULL AUTO_INCREMENT,"
					+"	etappe INT(11) NOT NULL,"
					+"	platz INT(11) NOT NULL,"
					+"	startnummer INT(11) NOT NULL,"
					+"	zeit VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',"
					+"	PRIMARY KEY (ergebnisID)"
					+")"
					+" COLLATE='utf8_german2_ci'"
					+" ENGINE=InnoDB"
					+" AUTO_INCREMENT=5356"
					);
			//Tabelle ergebnissegruen
			stmt.execute("CREATE TABLE " + TABLENAMEERGEBNISSEGRUEN
					+"	(ergebnisID INT(11) NOT NULL AUTO_INCREMENT,"
					+"	etappe INT(11) NOT NULL,"
					+"	startnummer INT(11) NOT NULL,"
					+"	punkteTemp VARCHAR(50) NOT NULL COLLATE 'utf8_german2_ci',"
					+"	punkte INT(11) NOT NULL,"
					+"	PRIMARY KEY (ergebnisID)"
					+")"
					+" COLLATE='utf8_german2_ci'"
					+" ENGINE=InnoDB"
					+" AUTO_INCREMENT=620"
					);
			

			sql = "SET foreign_key_checks = 1";
			stmt.execute(sql);
			
			return "succeed";

		} catch (SQLException e) {
			e.printStackTrace();
			return "failed";
		}
	}

	private List<String> getFilesFromDirectory(String path) throws IOException {
		List<String> filenames = new ArrayList<>();

		try (InputStream in = getResourceAsStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String resource;

			while ((resource = br.readLine()) != null) {
				if (resource.toLowerCase().endsWith(".csv"))
					filenames.add(resource);
			}
		}

		return filenames;
	}

	private InputStream getResourceAsStream(String resource) {
		final InputStream in = getContextClassLoader().getResourceAsStream(resource);

		return in == null ? getClass().getResourceAsStream(resource) : in;
	}

	private ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public List<String> getResourcesFromDirectory(final String directory) {
		List<String> retval = new ArrayList<String>();
		// URL dirURL = ClassLoader.getSystemClassLoader().getResource(directory);
		URL dirURL = this.getClass().getClassLoader().getResource(directory);
		// Enumeration<URL> urls =
		// getClass().getClassLoader().findResources("resources/" + directory);
		try {
			String s = "";
			if (dirURL == null) {
				/*
				 * In case of a jar file, we can't actually find a directory. Have to assume the
				 * same jar as clazz.
				 */
				String me = this.getClass().getName().replace(".", "/") + ".class";
				dirURL = this.getClass().getClassLoader().getResource(me);

				s = dirURL.toURI().toString().replace("bin/tourDeFrance/DBFunctions.class", directory);
			} else
				s = dirURL.toURI().toString();
			// C:\Users\julian\git\TourDeFrance2017WHS\TourDeFrance2017\resources
			/// C:/Users/julian/git/TourDeFrance2017WHS/TourDeFrance2017/resources/Testdaten_Ergebnisse_Bergwertung_2016/
			System.out.println(s);
			// File dir = new File(s);
			// final File[] fileList = dir.listFiles();
			// for (final File file : fileList) {
			// if (file.isFile()) {
			//
			// final String fileName = file.getCanonicalPath();
			// final boolean accept = fileName.toLowerCase().endsWith(".csv");
			// if (accept) {
			// retval.add(fileName);
			// }
			// }
			// }
			retval = getFilesFromDirectory(s);
		} catch (final Exception e) {
			throw new Error(e);
		}

		return retval;
	}

	/**
	 * Laedt Daten in eine tabelle in der Datenbank
	 * 
	 * @param pfad
	 *            URL zur Datei
	 * @param table
	 *            tabellenName
	 * @return erfolgreich
	 */
	// pfad = ./resources/" + verzeichnis + "/" + datei
	// pfad = "C:/dev/daten/" + datei
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

	public String getAktuelleConnection() {
		return aktuelleConnection;
	}

	public void setAktuelleConnection(String aktuelleConnection) {
		this.aktuelleConnection = aktuelleConnection;
	}

}
