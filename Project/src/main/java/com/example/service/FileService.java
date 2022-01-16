package com.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.model.FileData;

public class FileService {
	private String drive;
	private String url;
	private String dbId;
	private String dbPw;

	public FileService() {
		drive = "oracle.jdbc.driver.OracleDriver";
		url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		dbId = "LASTKIM";
		dbPw = "12345";
	}

	public void changeCH(String nick) throws ClassNotFoundException, SQLException {
		Class.forName(drive);
		Connection con = DriverManager.getConnection(url, dbId, dbPw);
		String sql = "UPDATE FILE_BLOCK SET checked = 1 WHERE nick=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, nick);
		int result = ps.executeUpdate();
		ps.close();
		con.close();
	}
	public void unChecked() throws ClassNotFoundException, SQLException {
		Class.forName(drive);
		Connection con = DriverManager.getConnection(url, dbId, dbPw);
		Statement st = con.createStatement();
		String sql = "UPDATE FILE_BLOCK SET checked=0 WHERE fix=1";
		int result = st.executeUpdate(sql);
		System.out.println(result);
		st.close();
		con.close();
	}
	public void deleteFile(String nick) throws ClassNotFoundException, SQLException {
		Class.forName(drive);
		Connection con = DriverManager.getConnection(url, dbId, dbPw);
		Statement st = con.createStatement();
		String sql = "DELETE FROM FILE_BLOCK WHERE nick='"+nick+"'";
		int result = st.executeUpdate(sql);
		System.out.println(result);
		st.close();
		con.close();
	}
	public void insertFile(String nick) throws SQLException, ClassNotFoundException {
		if(nick==null||nick.equals("")||nick.equals("null"))
			return;
		Class.forName(drive);
		Connection con = DriverManager.getConnection(url, dbId, dbPw);
		String sql = "INSERT INTO FILE_BLOCK (nick,fix,checked, wdate) VALUES (?,0,0,SYSTIMESTAMP)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, nick);
		int result = ps.executeUpdate();
		System.out.println(result);
		ps.close();
		con.close();
	}

	public boolean isCheck(String nick) throws ClassNotFoundException, SQLException {
		Class.forName(drive);
		Connection con = DriverManager.getConnection(url, dbId, dbPw);
		Statement st = con.createStatement();
		String sql = "SELECT nick FROM FILE_BLOCK";
		ResultSet rs = st.executeQuery(sql);
		boolean is = false;
		while(rs.next()) {
			String n = rs.getString("nick");
			if(n.equals(nick)) {
				is = true;
			}
		}
		rs.close();
		st.close();
		con.close();
		return is;
	}
	public ArrayList<FileData> getFiles() throws ClassNotFoundException, SQLException {
		Class.forName(drive);
		Connection con = DriverManager.getConnection(url, dbId, dbPw);
		Statement st = con.createStatement();
		String sql = "SELECT * FROM FILE_BLOCK";
		ArrayList<FileData> fArr = new ArrayList<FileData>();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			String nick = rs.getString("nick");
			int fix = rs.getInt("fix");
			int checked = rs.getInt("checked");
			FileData fd = new FileData(nick, fix, checked);
			fArr.add(fd);
		}
		rs.close();
		st.close();
		con.close();
		return fArr;
	}
	public int count() throws ClassNotFoundException, SQLException {
		Class.forName(drive);
		Connection con = DriverManager.getConnection(url, dbId, dbPw);
		Statement st = con.createStatement();
		String sql = "SELECT count(*) FROM FILE_BLOCK WHERE fix=0";
		ResultSet rs = st.executeQuery(sql);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		st.close();
		con.close();
		return count;
	}
}
