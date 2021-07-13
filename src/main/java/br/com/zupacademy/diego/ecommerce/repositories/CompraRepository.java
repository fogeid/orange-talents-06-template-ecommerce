package br.com.zupacademy.diego.ecommerce.repositories;

import br.com.zupacademy.diego.ecommerce.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
