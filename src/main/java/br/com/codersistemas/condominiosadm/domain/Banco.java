package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.codersistemas.condominiosadm.enums.TipoContabancaria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "banco")
public class Banco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_banco", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_banco", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_banco", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_condominio", nullable = false) // @ForeignKey(name="Banco_Condominio_fk")
	private Condominio condominio;

	@NotEmpty
	@Column(name = "numero", nullable = true)
	private Integer numero;

	@NotNull(message = "Agencia deve ser preenchido.")
	@Column(name = "agencia", length = 10, nullable = false)
	private String agencia;

	@NotNull(message = "Conta deve ser preenchido.")
	@Column(name = "conta", length = 15, nullable = false)
	private String conta;

	@NotNull(message = "Tipo deve ser preenchido.")
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private TipoContabancaria tipo;

	@OneToMany(mappedBy = "banco")
	private List<BancoLancamento> lancamentos;

}
//Ajuste os tamanhos dos campos.
