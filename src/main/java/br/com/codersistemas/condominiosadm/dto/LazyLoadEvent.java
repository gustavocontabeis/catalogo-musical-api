package br.com.codersistemas.condominiosadm.dto;

import lombok.Data;

@Data
public class LazyLoadEvent {
	
	private int first;
	private int rows;
	private String sortField;
	private int sortOrder;
	private SortMeta[] multiSortMeta;
	
//	private Filter[] filters;
	
//	private Filter filters: {
//    [s: string]: FilterMetadata;
//  };
	private FilterItem[] globalFilter;
    
	public int getPage() {
		return first == 0 ? first : first / rows;
	}
}
