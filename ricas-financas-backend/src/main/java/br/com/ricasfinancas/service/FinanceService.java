package br.com.ricasfinancas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ricasfinancas.model.Finance;
import br.com.ricasfinancas.repository.FinanceRepository;

/**
 * @author ricardo.mello
 *
 */
@Service	
public class FinanceService {
 
	@Autowired
	private FinanceRepository financeRepository;
	
	public List<Finance> findAll() {
		return financeRepository.findAll();
	}

	public Finance save(Finance financa) {
		return financeRepository.saveAndFlush(financa);
	}
	
	public void delete(Finance financa) {
		financeRepository.delete(financa);
	}

	public Optional<Finance> findOne(Long id) {
		return financeRepository.findById(id);
	}

	public void deleteById(Long id) {
		financeRepository.deleteById(id);
	}

	public void update(Finance finance) {
		Finance newFinance = financeRepository.getOne(finance.getId());
		newFinance.setDescription(finance.getDescription());
		newFinance.setValue(finance.getValue());
		newFinance.setCategory(finance.getCategory());
		save(newFinance);
	}
}
