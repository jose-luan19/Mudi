package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		Sort sort = Sort.by("dataDaEntrega").descending();
		PageRequest paginacao = PageRequest.of(0, 3, sort);
		
		List<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE, paginacao);
			
		ModelAndView mv = new ModelAndView("home");
	    mv.addObject("pedidos", pedidos);
	    return mv; 
	}
	
}
