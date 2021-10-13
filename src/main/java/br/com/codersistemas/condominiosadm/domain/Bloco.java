package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.codersistemas.condominiosadm.enums.TipoBloco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = {"apartamentos"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bloco")
public class Bloco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_bloco", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_bloco", initialValue = 1000, allocationSize = 1)
	@Column(name = "id_bloco", nullable = false)
	private Long id;

	@NotNull(message = "Nome deve ser preenchido.")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@NotNull(message = "Tipo deve ser preenchido.")
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private TipoBloco tipo;

	@ManyToOne
	@JoinColumn(name = "id_condominio", nullable = false) // @ForeignKey(name="Bloco_Condominio_fk")
	private Condominio condominio;

	@OneToMany(mappedBy = "bloco")
	private List<Apartamento> apartamentos;

}