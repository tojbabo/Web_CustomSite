package t.o.j.p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import t.o.j.d.userVO;

public interface userD {

	public userVO _read(String id);
	public boolean update(userVO uVO) ;
	public boolean _add(userVO ab);
	public ArrayList<userVO> getuvoList();
	public userVO login(String id, String passwd) ;

}
