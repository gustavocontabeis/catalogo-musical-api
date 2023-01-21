package br.com.codersistemas.condominiosadm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.codersistemas.condominiosadm.enums.Fluxo;
import br.com.codersistemas.condominiosadm.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
//@Table(name = "authorities")
public class Authorities implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_authorities", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="seq_authorities", initialValue=1000, allocationSize=1) 
	@Column(name="id_authorities", nullable=false) 
	private Long id;
	
	@Column(name = "username", length = 200, nullable = false)
	private String username;
	
	@NotNull(message = "Authority deve ser preenchido.")
	@Column(name = "authority", length = 200, nullable = false)
	private String authority;

}