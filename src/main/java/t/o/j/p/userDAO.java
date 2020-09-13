package t.o.j.p;


import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import t.o.j.d.userVO;


@Repository
public class userDAO implements userD {
	

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "t.o.j.mapper.userM";

	@Override
	public userVO _read(String id) {
		return sqlSession.selectOne(namespace+".selectByid", id);  
	}

	@Override
	public boolean update(userVO uVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean _add(userVO ab) {
		userVO uvo = _read(ab.getId());
		if(uvo!=null)
			return false;
		else {
			sqlSession.insert(namespace+".insert",ab);
			return true;
		}
			
	}

	@Override
	public ArrayList<userVO> getuvoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public userVO login(String id, String passwd) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
