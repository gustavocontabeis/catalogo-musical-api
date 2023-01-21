package br.com.codersistemas.condominiosadm.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.codersistemas.condominiosadm.domain.Apartamento;
import br.com.codersistemas.condominiosadm.domain.Bloco;
import br.com.codersistemas.condominiosadm.domain.Pessoa;
import br.com.codersistemas.condominiosadm.enums.QueryOperator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterSpecification {
	  private String field;
	  private QueryOperator operator;
	  private String value;
	  private List<String> values;//Used in case of IN operator
}
