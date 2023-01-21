package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.codersistemas.condominiosadm.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_pessoa", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_pessoa", nullable = false)
	private Long id;

	@NotNull(message = "Nome deve ser preenchido.")
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@NotNull(message = "Cpf deve ser preenchido.")
	@Column(name = "cpf", length = 20, nullable = false, unique = true)
	private String cpf;

	@NotNull(message = "Genero deve ser preenchido.")
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private Genero genero;

	@JsonFormat(pattern="dd/MM/yyyy")
	@NotNull(message = "Nascimento deve ser preenchido.")
	@Column(name = "nascimento", nullable = false)
	private LocalDate nascimento;

}
