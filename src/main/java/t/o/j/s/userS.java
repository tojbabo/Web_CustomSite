package t.o.j.s;

import t.o.j.d.userVO;

public interface userS {
	public userVO read(String id);
	public boolean insert(userVO uvo);

}
