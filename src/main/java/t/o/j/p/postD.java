package t.o.j.p;


import java.util.List;
import t.o.j.d.postVO;

public interface postD {


	public postVO _read(String num);
	public postVO last_one();
	public boolean update(postVO pvo);
	public postVO _add(postVO ab) ;
	public List<postVO> getpvoList();
	public List<postVO> getpvoList(boolean opt,String what);
	public boolean delete(String num);

}
