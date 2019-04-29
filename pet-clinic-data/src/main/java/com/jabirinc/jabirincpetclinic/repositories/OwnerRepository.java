package com.jabirinc.jabirincpetclinic.repositories;

import com.jabirinc.jabirincpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Getinet on 2019-04-21
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
}
