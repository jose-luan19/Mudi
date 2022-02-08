package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;

	//@GetMapping("formulario")
	@RequestMapping(method = RequestMethod.GET, value="formulario")
	public String formulario(RequisicaoNovoPedido requisicaoNovoPedido){
		return "pedido/formulario";
	}
	
	//@PostMapping("novo")
	@RequestMapping(method = RequestMethod.POST, value="novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		//@Valid: Orientando o spring para vaildar essa requisição
		//E o resultado da validação será retornado na variavel result do tipo BindingResult
		
		if(result.hasErrors()) {
			return "pedido/formulario";
		}
		
		//Metodo do spring para pegar o username do usuario logado
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		
		Pedido pedido = requisicao.toPedido();
		//Com isso os pedidos serão salvos no banco pertecendo ao ususario logado
		pedido.setUser(user);
		
		pedidoRepository.save(pedido);
		
		return "redirect:/home";
		//Method.POST usa-se redirect
		//Method.GET usa-se forward
	}
}
