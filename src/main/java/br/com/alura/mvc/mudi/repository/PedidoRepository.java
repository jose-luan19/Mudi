package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	
	public List<Pedido> findAll();
	
//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	public List<Pedido> recuperaTodosOsPedidos(){
//		//Consulta com HQL usa o nome da entidade
//		Query query = entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class);
//		return query.getResultList();
//	}
	
}
