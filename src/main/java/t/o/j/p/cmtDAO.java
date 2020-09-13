package t.o.j.p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import t.o.j.d.cmtVO;
import t.o.j.d.postVO;

	
	

@Repository
public class cmtDAO implements cmtD {
	
	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "t.o.j.mapper.cmtM";
	
	@Override
	public boolean _add(cmtVO ab) {
		// TODO Auto-generated method stub
		
		sqlSession.insert(namespace+".insert",ab); ;
		return true;
	}

	@Override
	public List<cmtVO> getcvoList(int num) {
		// TODO Auto-generated method stub

		return sqlSession.selectList(namespace+".selectAll",num);
	}

}
