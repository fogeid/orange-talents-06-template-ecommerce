package br.com.zupacademy.diego.ecommerce.repositories;

import br.com.zupacademy.diego.ecommerce.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
