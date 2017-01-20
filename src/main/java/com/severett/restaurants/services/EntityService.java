package com.severett.restaurants.services;

import java.util.List;

public interface EntityService<E> {

    public E saveEntity(E entity);

    public List<E> saveEntities(Iterable<E> entities);

}
