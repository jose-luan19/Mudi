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
//Notação para padronizar as requisições, quer dizer que sempre irão partir de '/home'
@RequestMapping("/home")
public class HomeController {
	
	//Anotação para que Spring realiza a injenção de dependências
	
	//Utilizamos o @AutoWired para indicar ao Spring que o objeto anotado 
	//é um componente ou Bean dele e que queremos que ele nos dê uma instância 
	//por meio do recurso de injeção de dependência.
	@Autowired
	private PedidoRepository repository;
	
	@GetMapping
	public ModelAndView home(Principal principal) {
		
//		List<Pedido> pedidos = repository.recuperaTodosOsPedidos();
		List<Pedido> pedidos = repository.findAllByUsuario(principal.getName());
		
//		model.addAttribute("pedidos", pedidos);
//		
//		return "home";
		
		ModelAndView mv = new ModelAndView("home");
	    mv.addObject("pedidos", pedidos);
	    return mv; 
	}
	
	@GetMapping("/{status}")
	public String pageStatus(@PathVariable("status") String status, Model model) {
		//@PathVariable("status"): Notação para que o spring pegue a variavel no path ou url nesse caso
		List<Pedido> pedidos = repository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		//valueOf(): metodo para transformar uma string em um enum
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "home"; 
	}
	
	//Metodo para que quando haja um erro, não seja mostrado toda a exception na tela
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	
}
