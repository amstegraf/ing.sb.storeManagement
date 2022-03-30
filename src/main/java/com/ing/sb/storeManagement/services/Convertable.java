package com.ing.sb.storeManagement.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Convertable<E, D> {
    E convertToEntity(D dto);

    D convertToDTO(E entity);

    default List<D> convertToDTO(Collection<E> entities) {
        return entities.stream().map(e -> convertToDTO(e)).collect(Collectors.toList());
    }
}
