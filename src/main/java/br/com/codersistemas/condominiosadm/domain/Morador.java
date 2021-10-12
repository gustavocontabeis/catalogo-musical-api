package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "morador")
@ToString(exclude = { "apartamento" })
public class Morador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_morador", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_morador", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_morador", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_pessoa", nullable = false) // @ForeignKey(name="Morador_Pessoa_fk")
	private Pessoa pessoa;

	@NotNull(message = "Proprietario deve ser preenchido.")
	@Column(name = "proprietario", length = 1, nullable = false)
	private Boolean proprietario;

	@ManyToOne
	@JoinColumn(name = "id_apartamento", nullable = false) // @ForeignKey(name="Morador_Apartamento_fk")
	private Apartamento apartamento;

}
