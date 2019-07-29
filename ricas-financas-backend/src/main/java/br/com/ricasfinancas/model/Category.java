package br.com.ricasfinancas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ricasfinancas.util.FinanceType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ricardo.mello
 *
 */
@Entity
@Table(name = "categorys")
@Data
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
	@SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq", allocationSize = 1)
	@Column(name = "idcategory", updatable = false, nullable = false)
	private Long id;

	private FinanceType type;

	private String name;

}
