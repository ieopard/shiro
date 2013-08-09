package com.java.module.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.framework.file.FileOperator;
import com.java.framework.session.ThreadSessionManage;
import com.java.module.entity.Privilege;
import com.java.module.entity.Role;
import com.java.module.entity.User;
import com.java.module.user.security.AuthUtils;
import com.java.module.user.service.UserService;

@Controller
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(Model model){
		setCommandModel(model);
		ModelAndView mav = new ModelAndView();
		List<User> list = userService.getAll();
		mav.addObject("list",list);
		mav.setViewName("user/list");
		return mav; 
	}
	
	@RequestMapping(value="/query")
	public ModelAndView queryList(@ModelAttribute("command") User user){
		
		ModelAndView mav = new ModelAndView();      
		   
//		List<User> list = userService.getAll();
//		mav.addObject("list",list);
		
		mav.addObject("command",user);
		mav.setViewName("user/list");   
		
		return mav;
	}
	public void setCommandModel(Model model){
		 if(!model.containsAttribute("command")) {
              model.addAttribute("command", new User());
         }
	}
	    
	@RequestMapping(value="/toAdd")
	public String toAdd(Model model){
		setCommandModel(model);
		return "user/add";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@ModelAttribute("command") User user, RedirectAttributes redirectAttribute){

		userService.save(user);
		redirectAttribute.addFlashAttribute("message","添加成功");
		return "redirect:/list";
	}

	@RequestMapping(value="/toUpdate")
	public String toUpdate(@RequestParam("id") long id,Model model){
        model.addAttribute("command", userService.get(id));
		return "user/update";
	}
	
	@RequestMapping(value="/update")
	public String update(@ModelAttribute("command") User user, RedirectAttributes redirectAttribute){
		userService.update(user);
		redirectAttribute.addFlashAttribute("message","修改成功");
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam("id") long id, RedirectAttributes redirectAttribute){
		try {
			userService.delete(id);
		} catch (Exception e) {
			log.info("删除异常",e);
			e.printStackTrace();
		}
		redirectAttribute.addFlashAttribute("message","删除成功");
		return "redirect:/list";
	}
	
	@RequestMapping(value="/deleteRole",method=RequestMethod.GET)
	public String forDdeleteRole(Model model){
		
		List<Role> list = userService.getAll(Role.class);
		
		model.addAttribute("list",list);
		return "privilege/delete";
	}
	
	@RequestMapping(value="/deleteRole")
	public String deleteRole(@RequestParam("id") long id, RedirectAttributes redirectAttribute){
		try {
			userService.deletes(id);
		} catch (Exception e) {
			log.info("删除异常",e);
			e.printStackTrace();
		}
		redirectAttribute.addFlashAttribute("message","删除角色成功");
		return "redirect:/list";
	}
	
	@RequestMapping(value="/privilege",method=RequestMethod.GET)
	public String privilegeGroup(Model model){
		
		List<Privilege> list = userService.getAll(Privilege.class);
		
		model.addAttribute("list",list);
		
		return "privilege/create";
	}
	
	@RequestMapping(value="/privilege",method=RequestMethod.POST)
	public String privilegeCreate(Model model,@RequestParam String role ,RedirectAttributes redirectAttribute,HttpServletRequest req){
		
		String[] ids = req.getParameterValues("id");
		
		List<Privilege> privileges = new ArrayList<Privilege>();
		
		for(String id : ids){
			privileges.add(new Privilege(Long.parseLong(id)));
		}
		userService.createRole(privileges, new Role(role));
		
		redirectAttribute.addFlashAttribute("message","创建用户权限成功");
		
		return "redirect:/list";
	}
	@RequestMapping(value="/authorization",method=RequestMethod.GET)
	public String roleList(Model model){
		
		List<Role> list = userService.getAll(Role.class);
		
		List<User> users = userService.getAll();
		
		model.addAttribute("list",list);
		model.addAttribute("users",users);
		
		return "privilege/authorization";
	}
	
	@RequestMapping(value="/authorization",method=RequestMethod.POST)
	public String authorization(Model model,@RequestParam long roleid,@RequestParam long userid,
			RedirectAttributes redirectAttribute,HttpServletRequest req){
		
		
		userService.createUserPrivilege(new User(userid),new Role(roleid));
		
		redirectAttribute.addFlashAttribute("message","授权用户权限成功");
		return "redirect:/list";
	}
	
	@RequestMapping(value="/showUserRole")
	public String showUserRole(Model model,RedirectAttributes redirectAttribute,HttpServletRequest req){
		
		List<Role> list = userService.getUserRole(AuthUtils.getUsername());
		
		model.addAttribute("list",list);
		
		return "privilege/showRole";
	}
	
	
	@RequestMapping(value = "/download",method=RequestMethod.POST)
	public void download(@RequestParam String fileName,HttpServletRequest request,HttpServletResponse response,ModelMap map,RedirectAttributes redirectAttribute) {
		FileOperator.downloadFile(request,response, "upload", fileName, "abc.txt");
	}
	
	@RequestMapping(value = "/upload",method=RequestMethod.POST) 	
	public String upload(MultipartHttpServletRequest req,ModelMap map,RedirectAttributes redirectAttribute) {
		FileOperator.FileUploads(req,"file","upload");
		redirectAttribute.addFlashAttribute("message","上传成功");
		return "redirect:/list";
	}
}
