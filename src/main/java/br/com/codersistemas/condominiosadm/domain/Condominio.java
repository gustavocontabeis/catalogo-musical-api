package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.util.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="condominio")
@ToString(exclude = {"blocos", "faturamentos"})
public class Condominio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_condominio", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_condominio", initialValue=1000, allocationSize=1) @Column(name="id_condominio", nullable=false) 
	private Long id;

	@NotNull(message = "Nome deve ser preenchido.")
	@Column(name="nome", length=100, nullable=false)
	private String nome;

	@NotNull(message = "Logradouro deve ser preenchido.")
	@Column(name="logradouro", length=150, nullable=false)
	private String logradouro;

	@NotNull(message = "Numero deve ser preenchido.")
	@Column(name="numero", length=20, nullable=false)
	private String numero;

	@NotNull(message = "Bairro deve ser preenchido.")
	@Column(name="bairro", length=100, nullable=false)
	private String bairro;

	@NotNull(message = "Cidade deve ser preenchido.")
	@Column(name="cidade", length=100, nullable=false)
	private String cidade;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sindico", nullable = false)
	//@ForeignKey(name = "Condominio_sindico_fk")
	private Sindico sindico;

	@JsonIgnore
	@OneToMany(mappedBy="condominio", fetch = FetchType.LAZY)
	private List<Bloco> blocos;

	@JsonIgnore
	@OneToMany(mappedBy="condominio")
	private List<Faturamento> faturamentos;

}
//Ajuste os tamanhos dos campos.

