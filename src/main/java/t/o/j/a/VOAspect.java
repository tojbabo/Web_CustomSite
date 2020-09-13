package t.o.j.a;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import t.o.j.d.cmtVO;
import t.o.j.d.postVO;
import t.o.j.d.userVO;

@Aspect
@Component
public class VOAspect {

	@AfterReturning(value = "execution(* _read(String))", returning = "member")
    public void afterReturningMethod(JoinPoint jp, userVO member) {
    	System.out.println("** read 호출 됨");
        Object[] obj = jp.getArgs();
        System.out.println("** 요청한 key : " + obj[0]);
        System.out.println("** 조회된 data :");
        show(member);
    }
	
	private void show(userVO uvo) {
		if(uvo !=null){
        System.out.println("------------------------------------");
        System.out.println("----ID       : " + uvo.getId());
        System.out.println("----PASSWD   : "+ uvo.getPasswd());
        System.out.println("----USERNAME : "+ uvo.getUsername());
        System.out.println("----MOBILE   : " + uvo.getMobile());
        System.out.println("----EMAIL    : " + uvo.getEmail());
        System.out.println("------------------------------------");}
		else
	        System.out.println("-----------------------------is null");
			
		
	}
	
	@AfterReturning(value = "execution(* getpvoList(..))", returning = "data")
    public void afterReturningMethod(JoinPoint jp, List<postVO> data) {
    	System.out.println("** 방 목록 불러옴");
        show(data);
      
    }
	private void show(List<postVO> pvo) {
        System.out.println("------------------------------------");
        for(postVO p : pvo) {
        	System.out.println("----" + p.getNum()+". "+p.getHead()+" <"+p.getName()+">");
        }
        System.out.println("------------------------------------");
		
	}
	
	@AfterReturning(value = "execution(* getcvoList(..))", returning = "data")
    public void afterReturningMethodC(JoinPoint jp, List<cmtVO> data) {
    	System.out.println("** 댓글 목록 불러옴");
        showC(data);
      
    }
	private void showC(List<cmtVO> cvo) {
        System.out.println("------------------------------------");
        for(cmtVO p : cvo) {
        	System.out.println("---- * " + p.getComment()+ "<"+p.getName()+">");
        }
        System.out.println("------------------------------------");
		
	}


}
