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

import com.gft.challenge.entities.Student;
import com.gft.challenge.services.DanceclassService;
import com.gft.challenge.services.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;	
	
	@Autowired
	DanceclassService danceclassService;	
	
		
	/*
	 * @RequestMapping(method= RequestMethod.GET, path="/get") public ModelAndView
	 * getStudent(@RequestParam Long id) {
	 * 
	 * ModelAndView mv = new ModelAndView("/");
	 * 
	 * try {
	 * 
	 * Student student = studentService.getStudent(id);
	 * 
	 * mv.addObject("student", student);
	 * 
	 * }catch(Exception e) { mv.addObject("message", e.getMessage()); }
	 * 
	 * return mv; }
	 */
		
	@RequestMapping(method= RequestMethod.GET, path="/new")
	public ModelAndView studentForm() {
		
		ModelAndView mv = new ModelAndView("student/studentform.html");
		mv.addObject("student", new Student());
		mv.addObject("list", danceclassService.danceclassList());
		
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.GET, path="/edit")
	public ModelAndView editStudent(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("student/studentform.html");
		
		try {
			Student student = studentService.getStudent(id);
			mv.addObject("student", student);
		} catch (Exception e) {
		 	e.printStackTrace();
		}
		
		mv.addObject("list", danceclassService.danceclassList());
		
		return mv;
	}
	
	
	@RequestMapping(method= RequestMethod.POST)
	public ModelAndView createStudent(@Valid Student student, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("redirect:/student");
		
		boolean newStudent = true;
		
		if(student.getId() != null) {
			newStudent = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("student", student);
			return mv;
		}
		
		Student savedStudent = studentService.saveStudent(student);
		
		if(newStudent) {
			mv.addObject("student", new Student());
		} else {
			mv.addObject("student", savedStudent);
		}
		
		redirectAttributes.addFlashAttribute("message", "Success: student saved!");
		
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.GET, path="/delete")
	public ModelAndView deleteStudent(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/student");
		
		try {
			studentService.deleteStudent(id);
			redirectAttributes.addFlashAttribute("message", "Success: student deleted!");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("message", "Something went worng. This student may not exist.");
		}			
		
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView studentList(String name) {
		
		ModelAndView mv = new ModelAndView("student/studentlist.html");
		
		if(name != null) {			
			mv.addObject("list", studentService.findStudentByName(name));			
		} else {
			mv.addObject("list", studentService.studentList());
		}		
		
		return mv;
	}
	
}
