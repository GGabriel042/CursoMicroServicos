package br.com.cursoMicroServicos.pagamentos.repository;

import br.com.cursoMicroServicos.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PagamentosRepository extends JpaRepository<Pagamento, Long> {
}
