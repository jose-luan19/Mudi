package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	
	public List<Pedido> findAll();
	
	
	public List<Pedido> findByStatus(StatusPedido status, Pageable paginacao);
	
	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username ")
	public List<Pedido> findAllByUsuario(@Param("username")String username);

	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username and p.status = :status")
	public List<Pedido> findByStatusEUsuario(@Param("status")StatusPedido status, @Param("username")String username);
	
//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	public List<Pedido> recuperaTodosOsPedidos(){
//		//Consulta com HQL usa o nome da entidade
//		Query query = entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class);
//		return query.getResultList();
//	}
	
}
