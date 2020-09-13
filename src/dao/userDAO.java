package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.userVO;


public class userDAO {
	Connection conn=null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/jspdb?useSSL=false&serverTimezone=UTC";
	
	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url,"jspbook","passwd");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public userVO read(String id) {
		connect();
		ArrayList<userVO> uvoList = new ArrayList<userVO>();
		uvoList = getuvoList();
		
		for(userVO vo : uvoList) {
			if(vo.getId().equals(id)) {
				return vo;
			}
		}
		disconnect();
		return null;
		
	}
	public boolean update(userVO uVO) {
		connect();
		String sql = "update account set passwd=?, username=?, mobile=?, email=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uVO.getPasswd());
			pstmt.setString(2,uVO.getUsername());
			pstmt.setString(3,uVO.getMobile());
			pstmt.setString(4,uVO.getEmail());
			pstmt.setString(5, uVO.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}
		return true;
	}
	public boolean add(userVO ab) {
		connect();
		String sql = "insert into account values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ab.getId());
			pstmt.setString(2, ab.getPasswd());
			pstmt.setString(3,ab.getUsername());
			pstmt.setString(4,ab.getMobile());
			pstmt.setString(5,ab.getEmail());
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}
		return true;
	}
	public ArrayList<userVO> getuvoList(){
		connect();
		ArrayList<userVO> uvolist = new ArrayList<userVO>();
		String sql = "select * from account";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				userVO uvo = new userVO();
				uvo.setId(rs.getString("id"));
				uvo.setPasswd(rs.getString("passwd"));
				uvo.setUsername(rs.getString("username"));
				uvo.setMobile(rs.getString("mobile"));
				uvo.setEmail(rs.getString("email"));
				uvolist.add(uvo);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return uvolist;
	}

	public userVO login(String id, String passwd) {
		userVO uvo = null;
		connect();
		String sql = "select * from account where id=? and passwd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2,passwd);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				uvo = new userVO();
				uvo.setId(rs.getString("id"));
				uvo.setPasswd(rs.getString("passwd"));
				uvo.setUsername(rs.getString("username"));
				uvo.setMobile(rs.getString("mobile"));
				uvo.setEmail(rs.getString("email"));
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return uvo;
	}
}
