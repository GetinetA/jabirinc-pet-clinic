package com.jabirinc.jabirincpetclinic.repositories;

import com.jabirinc.jabirincpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Getinet on 2019-04-21
 */
public interface PetRepository extends CrudRepository<Pet, Long> {
}
