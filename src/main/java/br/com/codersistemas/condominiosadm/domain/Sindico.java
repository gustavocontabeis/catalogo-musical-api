package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
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
@Table(name = "sindico")
public class Sindico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_sindico", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_sindico", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_sindico", nullable = false)
	private Long id;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER )
	@JoinColumn(name = "id_pessoa", nullable = false, foreignKey = @ForeignKey(name = "Sindico_Pessoa_fk"))
	private Pessoa pessoa;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "De deve ser preenchido.")
	@Column(name = "de", length = 255, nullable = false)
	private LocalDate de;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "ate", length = 255, nullable = true)
	private LocalDate ate;

}
