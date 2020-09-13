package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.postVO;

public class postDAO {
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
	public postVO read(int num) {
		connect();
		postVO target=null;
		ArrayList<postVO> pvoList = new ArrayList<postVO>();
		pvoList = getpvoList();
		for(postVO vo : pvoList) {
			if(vo.getNum() == num)
				target = vo;
		}
		
		disconnect();
		return target;
		
	}
	public postVO last_one() {
		connect();
		String sql = "select * from posting order by num desc limit 1";
		postVO pvo = new postVO();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				pvo.setNum(Integer.parseInt(rs.getString("num")));
				pvo.setId(rs.getString("id"));
				pvo.setName(rs.getString("name"));
				pvo.setHead(rs.getString("head"));
				pvo.setBody(rs.getString("body"));
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return pvo;
	}
	public boolean update(postVO pvo) {
		connect();
		String sql = "update posting set head=?, body=? where num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pvo.getHead());
			pstmt.setString(2,pvo.getBody());
			pstmt.setString(3,Integer.toString(pvo.getNum()));
			
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
	public boolean add(postVO ab) {
		connect();
		String sql = "insert into posting(id,name,head,body) values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ab.getId());
			pstmt.setString(2,ab.getName());
			pstmt.setString(3, ab.getHead());
			pstmt.setString(4,ab.getBody());
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}
		return true;
	}
	public ArrayList<postVO> getpvoList(){
		connect();
		ArrayList<postVO> pvolist = new ArrayList<postVO>();
		String sql = "select * from posting order by num desc";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				postVO pvo = new postVO();
				pvo.setNum(Integer.parseInt(rs.getString("num")));
				pvo.setId(rs.getString("id"));
				pvo.setName(rs.getString("name"));
				pvo.setHead(rs.getString("head"));
				pvo.setBody(rs.getString("body"));
				pvolist.add(pvo);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return pvolist;
	}
	public ArrayList<postVO> getpvoList(boolean opt,String what){
		connect(); 
		ArrayList<postVO> pvolist = new ArrayList<postVO>();
		String sql;
		if(opt) sql = "select * from posting where name like ? order by num desc";
		else sql = "select * from posting where head like ? order by num desc";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, what);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				postVO pvo = new postVO();
				pvo.setNum(Integer.parseInt(rs.getString("num")));
				pvo.setId(rs.getString("id"));
				pvo.setName(rs.getString("name"));
				pvo.setHead(rs.getString("head"));
				pvo.setBody(rs.getString("body"));
				pvolist.add(pvo);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return pvolist;
	}	
	public boolean delete(String num) {
		connect();
		String sql = "delete from posting where num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}
		return true;
	}

}