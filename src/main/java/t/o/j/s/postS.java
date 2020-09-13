package t.o.j.s;

import java.util.List;

import t.o.j.d.postVO;

public interface postS {
	public List<postVO> read();
	public List<postVO> search(String opt, String key);
	public postVO select(String num);
	public postVO add(postVO pvo);
	public boolean update(postVO pvo);
	public boolean delete(String num);

}
