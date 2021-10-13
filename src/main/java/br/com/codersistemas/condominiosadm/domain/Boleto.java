package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
@ToString(exclude = {"apartamento", "faturamento", "titular"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "boleto")
public class Boleto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_boleto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_boleto", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_boleto", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_apartamento", nullable = false) // @ForeignKey(name="Boleto_Apartamento_fk")
	private Apartamento apartamento;

	@ManyToOne
	@JoinColumn(name = "id_faturamento", nullable = false) // @ForeignKey(name="Boleto_Faturamento_fk")
	private Faturamento faturamento;

	@ManyToOne
	@JoinColumn(name = "id_pessoa_titular", nullable = false) // @ForeignKey(name="Boleto_Pessoa_fk")
	private Pessoa titular;

	@NotNull(message = "Vencimento deve ser preenchido.")
	@JsonFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	@Column(name = "vencimento", nullable = false)
	private LocalDate vencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	@Column(name = "pagamento", nullable = true)
	private LocalDate pagamento;

	@NotNull(message = "Valor deve ser preenchido.")
	@Column(name = "valor", precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;

	@Column(name = "juros", precision = 10, scale = 2, nullable = true)
	private BigDecimal juros;

	@Column(name = "multa", precision = 10, scale = 2, nullable = true)
	private BigDecimal multa;

	@NotNull(message = "Total deve ser preenchido.")
	@Column(name = "total", precision = 10, scale = 2, nullable = false)
	private BigDecimal total;

}
