package t.o.j.p;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import t.o.j.d.postVO;
@Repository
public class postDAO implements postD{
	
	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "t.o.j.mapper.postM";

	@Override
	public postVO _read(String num) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".selectByid",num); 
	}

	@Override
	public postVO last_one() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".selectlast"); 
	}

	@Override
	@Transactional ( propagation=Propagation.SUPPORTS, isolation=Isolation.READ_COMMITTED, timeout=10 )
	public boolean update(postVO pvo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".update_body",pvo);
		sqlSession.update(namespace+".update_head",pvo);
		//sqlSession.update(namespace+".update",pvo);
		return true;
	}

	@Override
	public postVO _add(postVO ab) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".insert",ab);
	
		return last_one();
	}

	@Override
	public List<postVO> getpvoList() {
		return sqlSession.selectList(namespace+".selectAll_sort");  
	}

	@Override
	public List<postVO> getpvoList(boolean opt, String what) {
		if(opt) return sqlSession.selectList(namespace+".selectAll_title",what);  
		else return sqlSession.selectList(namespace+".selectAll_name",what);  
	}

	@Override
	public boolean delete(String num) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".delete",num);
		return false;
	}

}