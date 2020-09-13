package t.o.j.s;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import t.o.j.d.cmtVO;
import t.o.j.p.cmtD;

@Service
public class cmtService implements cmtS {

	@Autowired
	private cmtD DAO;

	@Override
	public List<cmtVO> read(int num) {
		return DAO.getcvoList(num);
	}

	@Override
	public boolean add(cmtVO cvo) {
		DAO._add(cvo);
		return false;
	}
	
}
