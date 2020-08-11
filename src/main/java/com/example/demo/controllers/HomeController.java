package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Empleado;
import com.example.demo.services.IEmpleadoService;

@Controller
@SessionAttributes("empleado")
public class HomeController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("empleado", new Empleado());
		return "form";
	}
	
	@PostMapping("/form")
	public String save(@Valid Empleado empleado, BindingResult result,
			RedirectAttributes flash, SessionStatus status) {
		
		
		if(result.hasErrors() && empleado.getId() == null) {
			return "form";
		}
		
		String flashMsg = (empleado.getId()!=null ? "Empleado editado con éxito" : "Empleado creado con éxito");
		empleadoService.save(empleado);
		
		flash.addFlashAttribute("success", flashMsg);
		status.setComplete();
		
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("empleados", empleadoService.listadoEmpleados());
		return "listadoEmpleados";
	}
	
	@GetMapping("/detalles/{id}")
	public String detallesEmpleado(@PathVariable("id") Long id, Model model) {
		model.addAttribute("empleado", empleadoService.findById(id));
		return "detalles";
	}
	
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long id, RedirectAttributes flash) {
		empleadoService.delete(id);
		flash.addFlashAttribute("success", "Empleado eliminado!");
		
		
		return "redirect:/list";
	}
	
	@GetMapping("/update/{id}")
	public String rellenarFormulario(@PathVariable("id") Long id, Model model) {
		
		Empleado emp = null;
		
		if(id > 0) {
			emp = empleadoService.findById(id);
			model.addAttribute("empleado", emp);
		}
		
		
		return "form";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
