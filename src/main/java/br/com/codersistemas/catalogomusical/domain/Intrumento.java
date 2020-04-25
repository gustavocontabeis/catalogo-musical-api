package br.com.codersistemas.catalogomusical.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.codersistemas.libs.annotations.ClassLabelAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="intrumento")
public class Intrumento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_intrumento", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_intrumento", initialValue=1000, allocationSize=1) @Column(name="id_intrumento", nullable=false) 
	private Long id;

	@ClassLabelAttribute
	@NotNull(message = "Nome deve ser preenchido.")
	@Column(name="nome", length=50, nullable=false)
	private String nome;

}

