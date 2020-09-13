package t.o.j.s;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import t.o.j.d.postVO;
import t.o.j.p.postD;

@Service
public class postService implements postS {

	@Autowired
	private postD DAO;
	
	@Override
	public List<postVO> read() {
		return DAO.getpvoList();
	}

	@Override
	public List<postVO> search(String opt, String key) {
		// TODO Auto-generated method stub
		boolean temp;
		if(opt.equals("이 름"))
			temp = false;
		else
			temp = true;
			
		return DAO.getpvoList(temp, key);
	}

	@Override
	public postVO select(String num) {
		return DAO._read(num);
	}

	@Override
	public postVO add(postVO pvo) {
		return DAO._add(pvo);
	}

	@Override
	public boolean update(postVO pvo) {
		// TODO Auto-generated method stub
		return DAO.update(pvo);
	}

	@Override
	public boolean delete(String num) {
		return DAO.delete(num);
	}

}
