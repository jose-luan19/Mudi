package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Authority;
import br.com.alura.mvc.mudi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
