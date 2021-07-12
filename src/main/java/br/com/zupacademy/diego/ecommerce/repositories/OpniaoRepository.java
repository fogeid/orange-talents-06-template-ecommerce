package br.com.zupacademy.diego.ecommerce.repositories;

import br.com.zupacademy.diego.ecommerce.models.Opniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpniaoRepository extends JpaRepository<Opniao, Long> {
}
