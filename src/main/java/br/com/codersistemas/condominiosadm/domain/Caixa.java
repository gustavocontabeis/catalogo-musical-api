package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import br.com.codersistemas.condominiosadm.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "caixa")
public class Caixa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_caixa", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_caixa", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_caixa", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_condominio", nullable = false) // @ForeignKey(name="Caixa_Condominio_fk")
	private Condominio condominio;

	@ManyToOne
	@JoinColumn(name = "id_pessoa", nullable = false) // @ForeignKey(name="Caixa_Pessoa_fk")
	private Pessoa pessoa;

	@NotNull(message = "Data deve ser preenchido.")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false)
	private LocalDateTime data;

	@NotNull(message = "Contro de custo deve ser preenchido.")
	@ManyToOne
	@JoinColumn(name = "id_centro_de_custo", nullable = false) // @ForeignKey(name="Caixa_CentroDeCusto_fk")
	private CentroDeCusto centroDeCusto;

	@NotNull(message = "Tipo documento deve ser preenchido.")
	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private TipoDocumento tipoDocumento;

	@NotNull(message = "Para deve ser preenchido.")
	@Column(name = "para", length = 100, nullable = false)
	private String para;

	@NotNull(message = "Cpf cnpj deve ser preenchido.")
	@Column(name = "cpf_cnpj", length = 20, nullable = false)
	private String cpfCnpj;

	@NotNull(message = "Descrição deve ser preenchido.")
	@Column(name = "descricao", length = 255, nullable = false)
	private String descricao;

	@NotNull(message = "Valor deve ser preenchido.")
	@Column(name = "valor", precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;

	@NotNull(message = "Saldo deve ser preenchido.")
	@Column(name = "saldo", precision = 10, scale = 2, nullable = false)
	private BigDecimal saldo;

}