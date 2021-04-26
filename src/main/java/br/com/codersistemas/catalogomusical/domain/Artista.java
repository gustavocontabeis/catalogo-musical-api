package br.com.codersistemas.catalogomusical.domain;

import java.io.Serializable;
import java.util.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.codersistemas.catalogomusical.enums.Genero;
import br.com.codersistemas.libs.annotations.ClassLabelAttribute;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="artista")
public class Artista implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_artista", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_artista", initialValue=1000, allocationSize=1) @Column(name="id_artista", nullable=false) 
	private Long id;

	@ClassLabelAttribute
	@NotNull(message = "Nome deve ser preenchido.")
	@Column(name="nome", length=150, nullable=false)
	private String nome;
	
	@NotNull(message = "Nascimento deve ser preenchido.")
 	@JsonFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="nascimento", nullable=false)
	private Date nascimento;
	
	@Transient
	private Long nascimento2;

	@NotNull(message = "Genero deve ser preenchido.")
	@Enumerated(EnumType.STRING)
	@Column(length=255, nullable=false)
	private Genero genero;

	@ManyToOne @JoinColumn(name="id_paiz", nullable=false)
	private Paiz paizOrigem;

	@ManyToMany(cascade= {CascadeType.DETACH})
	private List<Instrumento> instrumento;
	
	public Long getNascimento2() {
		return nascimento != null ? nascimento.getTime() : null;
	}

}

