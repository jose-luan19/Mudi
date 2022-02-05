package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository repository;

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
		
		Pedido pedido = requisicao.toPedido();
		
		repository.save(pedido);
		
		return "pedido/formulario";
	}
}
