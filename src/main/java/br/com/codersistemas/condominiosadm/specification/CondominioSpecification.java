package br.com.codersistemas.condominiosadm.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.codersistemas.condominiosadm.domain.Bloco;
import br.com.codersistemas.condominiosadm.domain.Condominio;

public class CondominioSpecification {

    public static Specification<Condominio> id(Long id) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Condominio> nome(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("nome"), nome);
    }

    public static Specification<Condominio> nomeStartWith(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), nome+"%");
    }
    
    public static Specification<Bloco> sinficoCpf(String cpf) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("sindico").get("pessoa").get("cpf"), cpf);
    }

}
