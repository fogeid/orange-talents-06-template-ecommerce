package br.com.zupacademy.diego.ecommerce.repositories;

import br.com.zupacademy.diego.ecommerce.models.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
