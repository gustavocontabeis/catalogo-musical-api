package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "banco_lancamento")
@ToString(exclude = { "banco" })
public class BancoLancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_banco_lancamento", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_banco_lancamento", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_banco_lancamento", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_banco", nullable = false) // @ForeignKey(name="BancoLancamento_Banco_fk")
	private Banco banco;

	@NotNull(message = "Data deve ser preenchido.")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", length = 255, nullable = false)
	private LocalDateTime data;

	@ManyToOne
	@JoinColumn(name = "id_centro_de_custo", nullable = false) // @ForeignKey(name="BancoLancamento_CentroDeCusto_fk")
	private CentroDeCusto centroDeCusto;

	@Column(name = "documento", length = 100, nullable = true)
	private String documento;

	@NotNull(message = "Historico deve ser preenchido.")
	@Column(name = "historico", length = 255, nullable = false)
	private String historico;

	@NotNull(message = "Valor deve ser preenchido.")
	@Column(name = "valor", precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;

	@NotNull(message = "Saldo deve ser preenchido.")
	@Column(name = "saldo", precision = 10, scale = 2, nullable = false)
	private BigDecimal saldo;

}
