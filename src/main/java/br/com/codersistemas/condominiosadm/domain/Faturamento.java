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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = {"condominio", "boletos"})
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
	@Column(name = "periodo", nullable = false)
	private LocalDate periodo;

	@JsonIgnoreProperties({"blocos", "faturamentos", "sindico"})
	@ManyToOne
	@JoinColumn(name = "id_condominio", nullable = false)
	private Condominio condominio;

	@JsonIgnore
	@OneToMany(mappedBy = "faturamento")
	private List<Boleto> boletos;

}
