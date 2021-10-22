package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "centro_de_custo")
public class CentroDeCusto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_centro_de_custo", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_centro_de_custo", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_centro_de_custo", nullable = false)
	private Long id;

	@NotNull(message = "Nome deve ser preenchido.")
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

}
