package io.github.publications.publicationsapi.infra.repostiory.specs;

import io.github.publications.publicationsapi.domain.entity.Publication;
import org.springframework.data.jpa.domain.Specification;

public class PublicationSpecs {
    private PublicationSpecs(){}

    public static Specification<Publication> unidadeEqual(String unidade){
        return (root, q, cb) -> cb.equal(root.get("unidade"), unidade);
    }

    public static Specification<Publication> numeroProcessoLike(String numeroProcesso) {
        return (root, q, cb) -> cb.like(cb.upper(root.get("numeroProcesso")),"%"+numeroProcesso.toUpperCase()+"%");
    }

    public static Specification<Publication> orgaoLike(String orgao) {
        return (root, q, cb) -> cb.like(cb.upper(root.get("orgao")),"%"+orgao.toUpperCase()+"%");
    }

    public static Specification<Publication> descriptionLike(String descricao) {
        return (root, q, cb) -> cb.like(cb.upper(root.get("descricao")),"%"+descricao.toUpperCase()+"%");
    }

    public static Specification<Publication> documentTsVector(String query) {
        return (root, q, cb) -> cb.like(root.get("documento"),"@@ ecommerce_tsquery("+query+")");
    }
}
