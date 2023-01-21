package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.util.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="apartamento")
public class Apartamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_apartamento", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_apartamento", initialValue=1000, allocationSize=1) @Column(name="id_apartamento", nullable=false) 
	private Long id;

	@NotNull(message = "Numero deve ser preenchido.")
	@Column(name="numero", length=10, nullable=false)
	private String numero;

	@ManyToMany()
	@JoinTable(name = "apartamento_pessoas", 
	  joinColumns = @JoinColumn(name = "id_apartamento"), 
	  inverseJoinColumns = @JoinColumn(name = "id_pessoa"))
	private List<Pessoa> moradores;

	@JsonIgnoreProperties({"condominio", "apartamentos"})
	@ManyToOne @JoinColumn(name="id_bloco", nullable=false)//, foreignKey = @ForeignKey(name="Apartamento_Bloco_fk"))
	private Bloco bloco;

	@ManyToOne @JoinColumn(name="id_pessoa_proprietario", nullable=false)//, foreignKey = @ForeignKey(name="Apartamento_Pessoa_proprietario_fk"))
	private Pessoa proprietario;

	@ManyToOne @JoinColumn(name="id_pessoa_titular", nullable=false)//, foreignKey = @ForeignKey(name="Apartamento_Pessoa_titular_fk"))
	private Pessoa titular;
	
}
