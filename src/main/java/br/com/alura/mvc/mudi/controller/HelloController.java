package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Anotação Spring
@Controller
public class HelloController {
	
	//Mapeando para o hello.html
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("nome", " do Luan");
		return "hello";
	}
	//Model é uma interface da Spring para substituir o HttpServlet
	
}
 