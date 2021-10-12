package br.com.codersistemas.condominiosadm.dto;

import lombok.Data;

@Data
public class FilterItem {
	private String name, field, matchMode, value;
	
	private FilterMetadata value2;
}
