package com.java.module.viewUser.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.module.entity.ViewUser;
import com.java.module.viewUser.service.ViewUserService;

@Controller
@RequestMapping("viewUser")
public class ViewUserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewUserController.class);
	
	@Resource
	private ViewUserService viewUserService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(Model model){
		setCommandModel(model);
		ModelAndView mav = new ModelAndView();
		List<ViewUser> list = viewUserService.getAll();
		mav.addObject("list",list);
		mav.setViewName("viewUser/list");
		return mav;
	}
	@RequestMapping(value="/query")
	public ModelAndView queryList(@ModelAttribute("command") ViewUser viewUser){
		
		ModelAndView mav = new ModelAndView();
		
/*		List<ViewUser> list = viewUserService.getAll();
		mav.addObject("list",list);*/
		
		mav.addObject("command",viewUser);
		mav.setViewName("viewUser/list");
		
		return mav;
	}
	public void setCommandModel(Model model){
		 if(!model.containsAttribute("command")) {
              model.addAttribute("command", new ViewUser());
         }
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(Model model){
		setCommandModel(model);
		return "viewUser/add";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@ModelAttribute("command") ViewUser viewUser, RedirectAttributes redirectAttribute){

		viewUserService.save(viewUser);
		redirectAttribute.addFlashAttribute("message","添加成功");
		return "redirect:/list";
	}

	@RequestMapping(value="/toUpdate")
	public String toUpdate(@RequestParam("id") long id,Model model){
		
        model.addAttribute("command", viewUserService.get(id));
        
		return "viewUser/update";
	}
	
	@RequestMapping(value="/update")
	public String update(@ModelAttribute("command") ViewUser viewUser, RedirectAttributes redirectAttribute){
		viewUserService.update(viewUser);
		redirectAttribute.addFlashAttribute("message","修改成功");
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam("id") long id, RedirectAttributes redirectAttribute){
		viewUserService.delete(id);
		redirectAttribute.addFlashAttribute("message","删除成功");
		return "redirect:/list";
	}
	
}
