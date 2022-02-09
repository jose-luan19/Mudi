package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private PedidoRepository repository;

	@GetMapping("pedido")
	public ModelAndView home(Principal principal) {
		
		List<Pedido> pedidos = repository.findAllByUsuario(principal.getName());
		
		ModelAndView mv = new ModelAndView("usuario/home");
	    mv.addObject("pedidos", pedidos);
	    return mv; 
	}
	
	@GetMapping("pedido/{status}")
	public String pageStatus(@PathVariable("status") String status, Model model, Principal principal) {
		//@PathVariable("status"): Notação para que o spring pegue a variavel no path ou url nesse caso
		List<Pedido> pedidos = repository.findByStatusEUsuario(StatusPedido.valueOf(status.toUpperCase()),principal.getName());
		//valueOf(): metodo para transformar uma string em um enum
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "usuario/home"; 
	}
	
	//Metodo para que quando haja um erro, não seja mostrado toda a exception na tela
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}
	
}
