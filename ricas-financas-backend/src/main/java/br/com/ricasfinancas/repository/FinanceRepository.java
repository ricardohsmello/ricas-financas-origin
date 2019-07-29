package br.com.ricasfinancas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ricasfinancas.model.Finance;

/**
 * @author ricardo.mello
 *
 */
@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long> {

}
