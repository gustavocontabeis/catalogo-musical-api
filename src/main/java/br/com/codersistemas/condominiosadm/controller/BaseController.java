package br.com.codersistemas.condominiosadm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.dto.FilterItem;
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.dto.SortMeta;
import br.com.codersistemas.libs.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController<T> {
	
	protected PageRequest getPageRequest(LazyLoadEvent event) {
		return PageRequest.of(event.getPage(), event.getRows(), getSort(event));
	}
	
	protected Sort getSort(LazyLoadEvent event) {
		List<Order> orders = new ArrayList<>();
		if(event.getMultiSortMeta() != null && event.getMultiSortMeta().length > 0) {
			for(int i = 0 ; i < event.getMultiSortMeta().length ; i++) {
				SortMeta sortMeta = event.getMultiSortMeta()[i];
				orders.add( sortMeta.getOrder() == 1 ? Order.asc(sortMeta.getField()) : Order.desc(sortMeta.getField()) );
			}
		} else {
			orders.add(Order.asc("id"));
		}
		log.info("{}", orders);
		return Sort.by(orders);
	}
	
	protected Specification<T> createSpecification(LazyLoadEvent event) {
		List<Predicate> predicates = new ArrayList<>();
		Specification<T> specification = new Specification<T>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				for (FilterItem filterItem : event.getGlobalFilter()) {
					String field = StringUtil.uncapitalize(filterItem.getField());
					log.debug("--------------------------");
					log.debug("Field: {}", field);
					log.debug("MatchMode: {}", filterItem.getMatchMode());
					log.debug("Value: {}", filterItem.getValue());
					switch (filterItem.getMatchMode()) {
					case "equals":
						predicates.add(criteriaBuilder.equal(root.get(field), filterItem.getValue()));
						break;
					case "notEquals":
						predicates.add(criteriaBuilder.not(criteriaBuilder.equal(root.get(field), filterItem.getValue())));
						break;
					case "startsWith":
						predicates.add(criteriaBuilder.like(root.get(field), filterItem.getValue() + "%"));
						break;
					case "endsWith":
						predicates.add(criteriaBuilder.like(root.get(field), "%" + filterItem.getValue()));
						break;
					case "contains":
						predicates.add(criteriaBuilder.like(root.get(field), "%" + filterItem.getValue() + "%"));
						break;
					case "notContains":
						predicates.add(criteriaBuilder.not(criteriaBuilder.like(root.get(field), "%" + filterItem.getValue() + "%")));
						break;
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
		return specification;
	}
	
}
