package com.java.module.${packageName}.web;

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

import com.java.module.entity.${name};
import com.java.module.${packageName}.service.${name}Service;

@Controller
@RequestMapping("${packageName}")
public class ${name}Controller {

	private static final Logger log = LoggerFactory.getLogger(${name}Controller.class);
	
	@Resource
	private ${name}Service ${packageName}Service;
	
	@RequestMapping(value="/list")
	public ModelAndView list(Model model){
		setCommandModel(model);
		ModelAndView mav = new ModelAndView();
		List<${name}> list = ${packageName}Service.getAll();
		mav.addObject("list",list);
		mav.setViewName("${packageName}/list");
		return mav;
	}
	@RequestMapping(value="/query")
	public ModelAndView queryList(@ModelAttribute("command") ${name} ${packageName}){
		
		ModelAndView mav = new ModelAndView();
		
/*		List<${name}> list = ${packageName}Service.getAll();
		mav.addObject("list",list);*/
		
		mav.addObject("command",${packageName});
		mav.setViewName("${packageName}/list");
		
		return mav;
	}
	public void setCommandModel(Model model){
		 if(!model.containsAttribute("command")) {
              model.addAttribute("command", new ${name}());
         }
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(Model model){
		setCommandModel(model);
		return "${packageName}/add";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@ModelAttribute("command") ${name} ${packageName}, RedirectAttributes redirectAttribute){

		${packageName}Service.save(${packageName});
		redirectAttribute.addFlashAttribute("message","添加成功");
		return "redirect:/${packageName}/list";
	}

	@RequestMapping(value="/toUpdate")
	public String toUpdate(@RequestParam("id") long id,Model model){
		
        model.addAttribute("command", ${packageName}Service.get(id));
        
		return "${packageName}/update";
	}
	
	@RequestMapping(value="/update")
	public String update(@ModelAttribute("command") ${name} ${packageName}, RedirectAttributes redirectAttribute){
		${packageName}Service.update(${packageName});
		redirectAttribute.addFlashAttribute("message","修改成功");
		return "redirect:/${packageName}/list";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam("id") long id, RedirectAttributes redirectAttribute){
		${packageName}Service.delete(id);
		redirectAttribute.addFlashAttribute("message","删除成功");
		return "redirect:/${packageName}/list";
	}
	
}
