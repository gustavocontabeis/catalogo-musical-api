package br.com.codersistemas.condominiosadm.dto;

import lombok.Data;

@Data
public class FilterMetadata {
	private String value, matchMode, operator;
}
