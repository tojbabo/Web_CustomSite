package t.o.j.s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import t.o.j.d.userVO;
import t.o.j.p.userD;

@Service
	
public class userService implements userS {


	@Autowired
	private userD DAO;
	
	@Override
	public userVO read(String id) {
		return DAO._read(id);
	}

	@Override
	public boolean insert(userVO uvo) {
		return DAO._add(uvo);
	}

}
