package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
public class HomeController {
	
	//Anotação para que Spring realiza a injenção de dependências
	
	//Utilizamos o @AutoWired para indicar ao Spring que o objeto anotado 
	//é um componente ou Bean dele e que queremos que ele nos dê uma instância 
	//por meio do recurso de injeção de dependência.
	@Autowired
	private PedidoRepository repository;
	
	@GetMapping("/home")
	public ModelAndView home() {
		
//		List<Pedido> pedidos = repository.recuperaTodosOsPedidos();
		List<Pedido> pedidos = repository.findAll();
		
//		model.addAttribute("pedidos", pedidos);
//		
//		return "home";
		
		ModelAndView mv = new ModelAndView("home");
	    mv.addObject("pedidos", pedidos);
	    return mv; 
	}
	
}
