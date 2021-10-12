package br.com.codersistemas.condominiosadm.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.codersistemas.condominiosadm.domain.Bloco;

public class BlocoSpecification {

    public static Specification<Bloco> id(Long id) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Bloco> nome(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("nome"), nome);
    }

    public static Specification<Bloco> nomeStartWith(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), nome+"%");
    }
    
    public static Specification<Bloco> condominioId(Long id) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("condominio").get("id"), id);
    }

}
