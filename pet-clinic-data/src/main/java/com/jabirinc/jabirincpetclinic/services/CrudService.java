package com.jabirinc.jabirincpetclinic.services;

import java.util.Set;

/**
 * Created by Getinet on 2019-02-27
 */
public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T type);

    void delete(T object);

    void deleteById(ID id);


}
