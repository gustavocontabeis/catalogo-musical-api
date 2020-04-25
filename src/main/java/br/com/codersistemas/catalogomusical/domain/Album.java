package br.com.codersistemas.catalogomusical.domain;

import java.io.Serializable;
import java.util.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import br.com.codersistemas.catalogomusical.enums.EstiloMusical;
import br.com.codersistemas.libs.annotations.ClassLabelAttribute;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="album")
public class Album implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_album", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_album", initialValue=1000, allocationSize=1) 
	@Column(name="id_album", nullable=false) 
	private Long id;

	@ClassLabelAttribute
	@NotNull(message = "Nome deve ser preenchido.")
	@Column(name="nome", length=255, nullable=false)
	private String nome;

	@NotNull @Column(name="ano", nullable=true)
	private Integer ano;

	@ManyToOne @JoinColumn(name="id_banda", nullable=false)
	private Banda banda;

	@NotNull(message = "Estilo musical deve ser preenchido.")
	@Enumerated(EnumType.STRING)
	@Column(length=50, nullable=false)
	private EstiloMusical estiloMusical;

	@Transient
	//@ManyToMany()
	private List<Artista> artistas;

	@OneToMany(mappedBy="album")
	private List<Musica> musicas;

}
//Ajuste os tamanhos dos campos.

