package tourDeFrance.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tourDeFrance.DBFunctions;

public class EtappenDAO {

	public static List<Etappe> searchEtappen(String etappenID) throws SQLException{
		String selectStmt = "SELECT * FROM etappen WHERE etappenID="+etappenID;
		Statement stmt = DBFunctions.getInstance().getConnection().createStatement();
		ResultSet rsEtappe = stmt.executeQuery(selectStmt);
		List<Etappe> etappen = getEtappenFromResultSet(rsEtappe);
		return etappen;
		
	}
	
	
	public static List<Etappe> getEtappenFromResultSet(ResultSet rs) throws SQLException{
		List<Etappe> rt = new ArrayList<Etappe>();
		if(rs.next()){
			Etappe ep = new Etappe();
			ep.setEtappenID(rs.getInt("etappenID"));
			ep.setStartOrt(rs.getString("startort"));
			ep.setZielOrt(rs.getString("zielort"));
			ep.setLaenge(rs.getDouble("laenge"));
			rt.add(ep);
		}
		return rt;
		
		
	}
}
