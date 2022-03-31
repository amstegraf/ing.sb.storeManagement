package com.ing.sb.storeManagement.specifications;

import com.ing.sb.storeManagement.entities.Product;
import com.ing.sb.storeManagement.entities.Product_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification {

    private ProductSpecification() {
    }

    public static Specification<Product> filterByTitle(String title) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (title == null) {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }

                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(Product_.title)),
                        criteriaBuilder.lower(criteriaBuilder.literal("%" + title + "%"))
                );
            }
        };
    }

    public static Specification<Product> filterByStartPrice(Integer startPrice) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (startPrice == null) {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }

                return criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.price), startPrice);
            }
        };
    }

    public static Specification<Product> filterByEndPrice(Integer endPrice) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (endPrice == null) {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }

                return criteriaBuilder.lessThanOrEqualTo(root.get(Product_.price), endPrice);
            }
        };
    }
}
