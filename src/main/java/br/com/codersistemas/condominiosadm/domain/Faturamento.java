package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "faturamento")
public class Faturamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_faturamento", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_faturamento", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_faturamento", nullable = false)
	private Long id;

	@NotNull(message = "Periodo deve ser preenchido.")
	@JsonFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	@Column(name = "periodo", nullable = false)
	private LocalDate periodo;

	@ManyToOne
	@JoinColumn(name = "id_condominio", nullable = false)
	// @ForeignKey(name = "Faturamento_Condominio_fk")
	private Condominio condominio;

	@OneToMany(mappedBy = "faturamento")
	private List<Boleto> boletos;

}
