package t.o.j.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import t.o.j.d.userVO;
import t.o.j.s.userS;

@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired(required=true)
	private userS Service;

	@RequestMapping(value= {"/"},method = RequestMethod.GET)
	public String home(Model model)throws Exception{
		
		model.addAttribute("message","아이디와 비밀번호를 입력하세요.");
		model.addAttribute("id","");
		
		return "login";
	}
	
	@RequestMapping(value= {"/login"},method = RequestMethod.POST)
	public String login(@RequestParam("id")String id,@RequestParam("passwd")String passwd, Model model)throws Exception{
		userVO uvo = Service.read(id);
		
		if(uvo == null || !uvo.getPasswd().equals(passwd)) {
			model.addAttribute("message","아이디 혹은 비밀번호가 잘못 되었습니다.");
			model.addAttribute("id",id);
			return "login";
		}
		else {
			System.out.println("로그인 성공 : ");
			model.addAttribute("my",uvo);
			return "forward:/list/";
		}
	}
	
	@RequestMapping(value= {"/join"},method = RequestMethod.GET)
	public String join(Model model)throws Exception{
		return "join";
	}
	
	@RequestMapping(value= {"/sign"},method = RequestMethod.POST)
	public String sign(@ModelAttribute("newbie")userVO uvo,Model model)throws Exception{
		
		if(Service.insert(uvo))
		{
			System.out.println("가입 성공 성공 : ");
			model.addAttribute("message","가입을 축하합니다.");
			model.addAttribute("id",uvo.getId());
		}
		else {
			model.addAttribute("message","가입 실패입니다.");
			model.addAttribute("id","");
		}
		
		return "login";
	}

}

