package com.oracle.db;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
 
	public static Connection getConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";
		String username = "sis61"; 
		String password = "4229544"; 

		Connection conn = null;
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
 
	public static boolean executeSql(String sql) {
		Connection conn = getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.execute();
		} catch (SQLException e) {
			return false;
		} finally {
			close(conn, pstmt, null);
		}
		return true;
	}
 
	public static Map<String, String> getObject(String sql) {
		List<Map<String, String>> list = getList(sql);
		if (list.size() == 0) {
			return null;
		}
		return (Map<String, String>) list.get(0);
	}

	 
	public static List<Map<String, String>> getList(String sql) {
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ls = transtoListMap(rs);
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			close(conn, ps, rs);
		}
		return ls;
	}

	private static List<Map<String, String>> transtoListMap(ResultSet rs)
			throws SQLException {
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();
		int cols = rs.getMetaData().getColumnCount();
		while (rs.next()) {
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 1; i <= cols; i++) {
				map.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			}
			ls.add(map);
		}
		return ls;

	}

	public static void close(Connection con, PreparedStatement stmt,
			ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException se) {
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se) {
		}

		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
		} finally {
			con = null;
		}
	}
	 
}
