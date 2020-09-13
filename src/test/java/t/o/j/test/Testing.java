package t.o.j.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import t.o.j.d.postVO;
import t.o.j.d.userVO;
import t.o.j.p.postD;
import t.o.j.p.userD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class Testing {
	
	@Autowired
	private userD uD;
	@Autowired
	private postD pD;
	
	@Test
	public void login_TEST() {
			
		String id = "a";
		String pass = "a";
		
		userVO uvo = uD._read(id);
		boolean res;
		if(uvo==null)
			res = false;
		else if(!uvo.getPasswd().equals(pass))
			res = false;
		else
			res =true;
		
		System.out.println(" ++++++++ JUNIT TEST ++++++++");
		System.out.println(" + ID     : " + id);
		System.out.println(" + PASSWD : " + pass);
		System.out.println(" + RESLT  : " + true);
	}
	
	@Test
	public void read_test() {
		int index = 2;
		List<postVO> plist = pD.getpvoList();
		int count;
		
		count = index*10;
		if(plist.size()<count) {
			System.out.println(" + 리스트 크기가 "+count+" 보다 작습니다.");
			System.out.println(" + ["+count+"]부터 ["+plist.size()+"]까지 반환합니다.");
		}
		else {
			System.out.println(" + 리스트 크기가 "+count+" 보다 큽니다.");
			System.out.println(" + ["+count+"]부터 ["+(count+10)+"]까지 반환합니다.");
			
		}
		
	}
}
