package t.o.j.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import t.o.j.d.cmtVO;
import t.o.j.d.postVO;
import t.o.j.d.userVO;
import t.o.j.s.cmtS;
import t.o.j.s.postS;
import t.o.j.s.userS;

@Controller
@RequestMapping(value="/post")
public class PostController {

	@Autowired(required=true)
	private postS Service;
	@Autowired(required=true)
	private userS ServiceU;
	@Autowired(required=true)
	private cmtS ServiceC;

	@RequestMapping(value= {"/"},method = RequestMethod.GET)
	public String home(@RequestParam("keyword")String key,Model model)throws Exception{
		
		
		int num = Integer.parseInt(key);
		
		model.addAttribute("cvo",ServiceC.read(num));
		return "open";
	}

	@RequestMapping(value= {"/write"},method = RequestMethod.GET)
	public String write(Model model)throws Exception{
		model.addAttribute("msg","내용을 입력하세요.");
		model.addAttribute("pvo",new postVO());
		
		return "writing";
	}
	
	@RequestMapping(value= {"/posting"},method = RequestMethod.POST)
	public String posting(@ModelAttribute("pvo")postVO pvo,Model model)throws Exception{
		
		
		if(pvo.getNum() == -1) {
			pvo = Service.add(pvo);
			model.addAttribute("msg","글이 성공적으로 작성 되었습니다.");
		}
		else {
			try {
			Service.update(pvo);
			model.addAttribute("msg","글을 성공적으로 수정 하였습니다.");
			}catch(Exception e) { 
				System.out.println("수정 에러!!");
				model.addAttribute("msg","글을 수정하는데 문제가 발생했습니다."); 
			} 
			pvo = Service.select(Integer.toString(pvo.getNum()));
		}
		
		model.addAttribute("pvo",pvo);
		model.addAttribute("uvo",ServiceU.read(pvo.getId()));
		model.addAttribute("cvo",ServiceC.read(pvo.getNum()));
		
		return "open";
	}
	
	@RequestMapping(value= {"/modify"},method = RequestMethod.POST)
	public String modify(@ModelAttribute("pvo")postVO pvo,Model model)throws Exception{
		
		model.addAttribute("msg","내용을 수정하세요.");
		model.addAttribute("uvo",ServiceU.read(pvo.getId()));
		
		return "writing";
	}
	
	@RequestMapping(value= {"/del"},method = RequestMethod.POST)
	public String del(@RequestParam("num")String num,Model model)throws Exception{
		Service.delete(num);
		return "forward:/list/entire";
	}
	
	@RequestMapping(value= {"/comment"},method = RequestMethod.POST)
	public String comment(@ModelAttribute("cvo_")cmtVO cvo,Model model)throws Exception{
		postVO pvo = Service.select(Integer.toString(cvo.getPosting_num()));
		ServiceC.add(cvo);
		
		
		model.addAttribute("pvo",pvo);
		model.addAttribute("uvo",ServiceU.read(cvo.getId()));
		model.addAttribute("cvo",ServiceC.read(pvo.getNum()));
		
		return "open";
	}

}

