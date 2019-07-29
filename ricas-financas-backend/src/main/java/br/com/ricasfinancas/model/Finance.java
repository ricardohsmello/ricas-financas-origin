package br.com.ricasfinancas.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ricardo.mello
 *
 */
@Entity
@Table(name = "finances")
@Data
@NoArgsConstructor
public class Finance {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "finance_id_seq")
	@SequenceGenerator(name = "finance_id_seq", sequenceName = "finance_id_seq", allocationSize = 1)
	@Column(name = "idfinance", updatable = false, nullable = false)
	private Long id;

	private double value;

	private String description;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "idcategory")
	private Category category;

}