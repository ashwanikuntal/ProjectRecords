package org.projectdiaries.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

public class ProjectDAO {
	
	Connection conn = null;
	DateFormat dfdate = new SimpleDateFormat("MM/dd/yyyy");
	DateFormat dftime = new SimpleDateFormat("HH:mm:ss");
	
	public ProjectDAO(Connection con) {
		if(null == con)
			throw new RuntimeException("The Connection is null");
		else 
			conn = con;
	}
	
	public ResultSet getTaskStatus(String projectName) {
		ResultSet rs = null; 
		String sql = "Select * from project_details where project_name = '" + projectName + "' order by last_modified_time desc LIMIT 1";
		try {
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return rs;
	}
	
	public void insertProjectRow(String pName, String bRate) {
		String sql = "Insert into project_details (project_name, start_time, end_time, date, rate, wages, status, last_modified_time) value(?, ?, ?, ?, ?, ?, ?, now())";
		try {
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString (1, pName);
		    pst.setString (2, dftime.format(Calendar.getInstance().getTime()));
		    pst.setString (3, null);
		    pst.setString (4, dfdate.format(Calendar.getInstance().getTime()));
		    pst.setString (5, bRate);
		    pst.setString (6, "0");
		    pst.setString (7, "In Progress");
		    
		    pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet fetchSelectedProjectRows(String projectName) {
		ResultSet rs = null;
		String sql = "Select * from project_details where project_name = '" + projectName + "' order by last_modified_time desc";
		try {
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return rs;
	}
	
	public void updateProjectRow(String pName) throws ParseException {
		ResultSet rs = null; 
		int SNo = 0;
		Date st_time = null; Date end_time = null; String rate = null; long wages = 0;
		String sqlfind = "Select * from project_details order by last_modified_time desc LIMIT 1";
		try {
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sqlfind);
			rs = pst.executeQuery();
			if(rs.next()) {
				SNo = (Integer) rs.getInt(1);
				st_time = dftime.parse(rs.getString("start_time"));
				rate = (String) rs.getString("rate");
			}
			end_time = dftime.parse(dftime.format(Calendar.getInstance().getTime()));
			long diff = end_time.getTime() - st_time.getTime();
			long diffHours = diff / (60 * 60 * 1000) % 24;
			wages = Integer.parseInt(rate) * diffHours;
			String sqlupdate = "Update project_details set end_time = ?, wages = ?, status = ?, last_modified_time = now() where SNo = '" + SNo + "'";
			PreparedStatement pstnext = (PreparedStatement) conn.prepareStatement(sqlupdate);
			pstnext.setString(1, dftime.format(Calendar.getInstance().getTime()));
			pstnext.setString(2, String.valueOf(wages));
			pstnext.setString(3, "Completed");
			pstnext.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public ResultSet fetchCompletedProjectRows(String projectName) {
		ResultSet rs = null;
		String sql = "Select * from project_details where project_name = '" + projectName + "' and status = 'COMPLETED' order by last_modified_time desc";
		try {
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return rs;
	}
}
