package com.gft.challenge.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.challenge.entities.Danceclass;
import com.gft.challenge.services.DanceclassService;
import com.gft.challenge.services.StudentService;

@Controller
@RequestMapping("/danceclass")
public class DanceclassController {
	
	@Autowired
	private DanceclassService danceclassService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(method= RequestMethod.GET, path= "/new")
	public ModelAndView newDanceclass() {
		
		ModelAndView mv = new ModelAndView("danceclass/form.html");
		mv.addObject("danceclass", new Danceclass());
		
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.POST, path= "/new")
	public ModelAndView saveDanceclass(@Valid Danceclass danceclass, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("danceclass/form.html");
		
		boolean newDanceclass = true;
		
		if(danceclass.getId() != null) {
			newDanceclass = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("danceclass", danceclass);
			return mv;
		}
		
		Danceclass savedDanceclass = danceclassService.saveDanceclass(danceclass);
		
		if(newDanceclass) {
			mv.addObject("danceclass", new Danceclass());
		} else {
			mv.addObject("danceclass", savedDanceclass);
		}
		
		mv.addObject("message", "Success: class saved!");
		
		return mv;		
	}
	
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView listDanceclass() {
		
		ModelAndView mv = new ModelAndView("danceclass/list.html");
		mv.addObject("list", danceclassService.danceclassList());
		
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView editDanceclass(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("danceclass/form.html");
		Danceclass danceclass;
		
		try {
			danceclass = danceclassService.getDanceclass(id);
		}catch(Exception e) {
			danceclass = new Danceclass();
			mv.addObject("message", e.getMessage());
		}
		
		mv.addObject("danceclass", danceclass);
		mv.addObject("listofstudents", studentService.studentList());
		
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteDanceclass(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/danceclass");
				
		try {
			danceclassService.deleteDanceclass(id);
			redirectAttributes.addFlashAttribute("message", "Success: class deleted!");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("message", "Something went wrong! " + e.getMessage());
		}
			
		return mv;
	}

}
