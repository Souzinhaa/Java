package com.cliente.cadastro.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.cliente.cadastro.model.Pagamentos;

@EnableJpaRepositories
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamentos, Long>  {
	Page<Pagamentos> findByClienteId(Long cliente_id, Pageable pageable);
	Optional<Pagamentos> findByIdAndClienteId(Long id, Long cliente_id);
}
