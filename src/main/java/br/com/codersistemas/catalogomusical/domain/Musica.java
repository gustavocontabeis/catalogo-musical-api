package br.com.codersistemas.catalogomusical.domain;

import java.io.Serializable;
import java.util.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import br.com.codersistemas.libs.annotations.ClassLabelAttribute;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="musica")
public class Musica implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_musica", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_musica", initialValue=1000, allocationSize=1) @Column(name="id_musica", nullable=false) 
	private Long id;

	@ClassLabelAttribute
	@NotNull(message = "Nome deve ser preenchido.")
	@Column(name="nome", length=255, nullable=false)
	private String nome;

	@ManyToOne @JoinColumn(name="id_album", nullable=false)
	private Album album;

	@Column(name="letra_en", length=255, nullable=true)
	private String letraEn;

	@Column(name="letra_pt", length=255, nullable=true)
	private String letraPt;

	@Column(name="partitura", length=255, nullable=true)
	private String partitura;

}

