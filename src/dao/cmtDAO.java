package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.*;

public class cmtDAO {
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
	public boolean add(cmtVO ab) {
		connect();
		String sql = "insert into comment(posting_num,id,name,cmt) values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Integer.toString(ab.getPosting_num()));
			pstmt.setString(2, ab.getId());
			pstmt.setString(3, ab.getName());
			pstmt.setString(4, ab.getComment());
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}
		return true;
	}

	public ArrayList<cmtVO> getcvoList(int num){
		connect();
		ArrayList<cmtVO> cvolist = new ArrayList<cmtVO>();
		String sql ="select * from comment where posting_num=? order by num desc";		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(num));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				cmtVO cvo = new cmtVO();
				cvo.setNum(Integer.parseInt(rs.getString("num")));
				cvo.setPosting_num(num);
				cvo.setId(rs.getString("id"));
				cvo.setName(rs.getString("name"));
				cvo.setComment(rs.getString("cmt"));
				cvolist.add(cvo);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cvolist;
	}	
}
