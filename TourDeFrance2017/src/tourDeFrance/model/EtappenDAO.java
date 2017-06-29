package tourDeFrance.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tourDeFrance.DBFunctions;

public class EtappenDAO {

	public static List<Etappe> searchEtappen() throws SQLException {
		String selectStmt = "SELECT e.etappenID, e.startort, e.zielort, e.laenge, e.datum, a.bezeichnung"
				+ " FROM etappen e left join etappenart a on e.art=a.artID"; // WHERE etappenID=" + etappenID;
		Statement stmt = DBFunctions.getInstance().getConnection().createStatement();
		ResultSet rsEtappe = stmt.executeQuery(selectStmt);
		List<Etappe> etappen = getEtappenFromResultSet(rsEtappe);
		return etappen;

	}

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
//	//*******************************
//    //SELECT Employees
//    //*******************************
//    public static ObservableList<Employee> searchEmployees () throws SQLException, ClassNotFoundException {
//        //Declare a SELECT statement
//        String selectStmt = "SELECT * FROM employees";
// 
//        //Execute SELECT statement
//        try {
//            //Get ResultSet from dbExecuteQuery method
//            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
// 
//            //Send ResultSet to the getEmployeeList method and get employee object
//            ObservableList<Employee> empList = getEmployeeList(rsEmps);
// 
//            //Return employee object
//            return empList;
//        } catch (SQLException e) {
//            System.out.println("SQL select operation has been failed: " + e);
//            //Return exception
//            throw e;
//        }
//    }
// 
//    //Select * from employees operation
//    private static ObservableList<Employee> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
//        //Declare a observable List which comprises of Employee objects
//        ObservableList<Employee> empList = FXCollections.observableArrayList();
// 
//        while (rs.next()) {
//            Employee emp = new Employee();
//            emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
//            emp.setFirstName(rs.getString("FIRST_NAME"));
//            emp.setLastName(rs.getString("LAST_NAME"));
//            emp.setEmail(rs.getString("EMAIL"));
//            emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
//            emp.setHireDate(rs.getDate("HIRE_DATE"));
//            emp.setJobId(rs.getString("JOB_ID"));
//            emp.setSalary(rs.getInt("SALARY"));
//            emp.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
//            emp.setManagerId(rs.getInt("MANAGER_ID"));
//            emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
//            //Add employee to the ObservableList
//            empList.add(emp);
//        }
//        //return empList (ObservableList of Employees)
//        return empList;
//    }
	
	
}
