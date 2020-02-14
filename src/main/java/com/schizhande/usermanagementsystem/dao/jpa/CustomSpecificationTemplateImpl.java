package com.schizhande.usermanagementsystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CustomSpecificationTemplateImpl<T> implements Specification<T> {
    private static final Logger log = LoggerFactory.getLogger(CustomSpecificationTemplateImpl.class);
    private final SearchCriteria searchCriteria;

    CustomSpecificationTemplateImpl(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        String[] keys = this.searchCriteria.getKey().split(Pattern.quote("."));
        if (this.searchCriteria.getOperation().equalsIgnoreCase(Operations.GREATER_THAN.sign)) {
            return builder.greaterThanOrEqualTo(this.getRoot(this.searchCriteria.getKey(), root, builder, keys), this.searchCriteria.getValue().toString());
        } else if (this.searchCriteria.getOperation().equalsIgnoreCase(Operations.LESS_THAN.sign)) {
            return builder.lessThanOrEqualTo(this.getRoot(this.searchCriteria.getKey(), root, builder, keys), this.searchCriteria.getValue().toString());
        } else if (this.searchCriteria.getOperation().equalsIgnoreCase(Operations.EQUALS.sign)) {
            return this.getRoot(this.searchCriteria.getKey(), root, builder, keys).getJavaType().equals(String.class) ? builder.like(this.getRoot(this.searchCriteria.getKey(), root, builder, keys), "%" + this.searchCriteria.getValue() + "%") : builder.equal(this.getRoot(this.searchCriteria.getKey(), root, builder, keys), this.searchCriteria.getValue());
        } else {
            return null;
        }
    }

    private Expression getRoot(String key, Root<T> root, CriteriaBuilder builder, String... keys) {
        Arrays.asList(keys).forEach((key1) -> {
            log.info("---> {}", key1);
        });
        if (keys.length <= 1) {
            return root.get(key);
        } else {
            Path<Object> newRoot = root.get(keys[0]);

            for(int i = 1; i < keys.length - 1; ++i) {
                newRoot = newRoot.get(keys[i]);
            }

            return newRoot.get(keys[keys.length - 1]);
        }
    }
}
