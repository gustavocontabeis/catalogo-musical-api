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
@Table(name="banda")
public class Banda implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_banda", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_banda", initialValue=1000, allocationSize=1) @Column(name="id_banda", nullable=false) 
	private Long id;

	@ClassLabelAttribute
	@NotNull(message = "Nome deve ser preenchido.")
	@Column(name="nome", length=150, nullable=false)
	private String nome;

	@OneToMany(mappedBy="banda")
	private List<Album> albuns;

}
//Ajuste os tamanhos dos campos.

