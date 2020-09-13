package t.o.j.c;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import t.o.j.d.postVO;
import t.o.j.d.userVO;
import t.o.j.s.postS;
import t.o.j.s.userS;

@Controller
@RequestMapping(value="/list")
public class ListController {
	userVO _uvo;
	String _key;
	String _keyword;
	int index;
	
	@Autowired
	private postS Service;
	@Autowired
	private userS Service_U;

	@RequestMapping(value= {"/"},method = RequestMethod.POST)
	public String home(@RequestParam("id")String id,Model model)throws Exception{
		System.out.println(id);
		_uvo = Service_U.read(id);
		_key ="";
		_keyword="";
		index =0;
		
		return "forward:/list/view";
	}
	
	@RequestMapping(value= {"/entire"},method = {RequestMethod.GET,RequestMethod.POST})
	public String entire(Model model)throws Exception{
		_key = "";
		_keyword = "";
		index = 0;
		
		return "forward:/list/view";
	}
		

	@RequestMapping(value= {"/view"},method = {RequestMethod.POST,RequestMethod.GET})
	public String view(Model model)throws Exception{
		List<postVO> pvolist;
			
		if(_keyword.equals(""))
			pvolist = Service.read();
		else 
			pvolist = Service.search(_key, _keyword);
			

		model.addAttribute("size",(pvolist.size()-1)/10 +1);
	
		int num = index*10;
		
		if(pvolist.size() < num+10)
			pvolist = pvolist.subList(num, pvolist.size());
		else
			pvolist = pvolist.subList(num,num+10);
		
		model.addAttribute("postList",pvolist);
		
		return "posting";
	}

	@RequestMapping(value= {"/index"},method = RequestMethod.GET)
	public String index(@RequestParam("index")int i,Model model)throws Exception{
		index = i;
		return "forward:/list/view";
	}
	
	@RequestMapping(value= {"/search_select"},method = RequestMethod.GET)
	public String search_select(@RequestParam("keyword")String key,Model model)throws Exception{
		index = 0;
		_keyword = Service.select(key).getName();
		_key = "이 름";
		return "forward:/list/view";
	}
	
	@RequestMapping(value= {"/search"},method = RequestMethod.POST)
	public String search(@RequestParam("keyword")String key,@RequestParam("opt")String opt,Model model)throws Exception{
		index = 0;
		_key = opt;
		_keyword = "%"+key+"%";
		return "forward:/list/view";
	}
	
	@RequestMapping(value= {"/select"},method = RequestMethod.GET)
	public String select(@RequestParam("keyword")String key,Model model)throws Exception{
		model.addAttribute("pvo",Service.select(key));
		model.addAttribute("uvo",_uvo);

		return "forward:/post/";
	}	
	
	@RequestMapping(value= {"/write"},method = RequestMethod.GET)
	public String write(Model model)throws Exception{
		model.addAttribute("uvo",_uvo);
		return "forward:/post/write";
	}
	
	@RequestMapping(value= {"/logout"},method = RequestMethod.GET)
	public String logout(Model model)throws Exception{
		
		model.addAttribute("message","안녕히 가세요.");
		model.addAttribute("id",_uvo.getId());
		return "login";
	}

	
}

