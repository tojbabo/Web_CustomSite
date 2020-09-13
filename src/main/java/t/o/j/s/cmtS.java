package t.o.j.s;

import java.util.List;

import t.o.j.d.cmtVO;

public interface cmtS {
	public List<cmtVO> read(int num);
	public boolean add(cmtVO cvo);
}
