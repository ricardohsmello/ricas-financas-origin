package br.com.ricasfinancas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ricasfinancas.model.Finance;
import br.com.ricasfinancas.service.FinanceService;
import br.com.ricasfinancas.swaggerconfig.ControllerDocumentation;
import io.swagger.annotations.Api;

/**
 * @author ricardo.mello
 *
 */
@RestController
@RequestMapping("/api/finances")
@ControllerDocumentation
@Api(value="finance-context", description="Rest API for working in the finance context")
public class FinanceController {

	@Autowired
	private FinanceService financeService;

	@RequestMapping(value = "/finance", method = RequestMethod.GET)
	public ResponseEntity<List<Finance>> getAll() {
		return new ResponseEntity<>(financeService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/finance/{id}", method = RequestMethod.GET)
	public ResponseEntity<Finance> getId(@PathVariable("id") Long id) {
		
		Finance finance = getOne(id);
			
		if (finance == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(finance, HttpStatus.OK);
	}

	@RequestMapping(value = "/finance", method = RequestMethod.POST)
	public Finance add(@RequestBody Finance finance) {
		return financeService.save(finance);
	}

	@RequestMapping(value = "/finance/{id}", method = RequestMethod.PATCH)
	public void update(@RequestBody Finance finance, @PathVariable("id") Long id) {
		finance.setId(id);
		financeService.update(finance);
	}
	

	@RequestMapping(value = "/finance/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Finance> delete(@PathVariable("id") Long id) {
		Finance finance = getOne(id);
		
		if (finance == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		financeService.delete(finance);
		
		return new ResponseEntity<>(finance, HttpStatus.OK);

	} 
	private Finance getOne(Long id) {
		Optional<Finance> optional = financeService.findOne(id);
		Finance finance = null;
		
		if (optional.isPresent()) {
			finance = optional.get();
		}
		
		return finance;
	}
}
