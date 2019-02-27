package com.jabirinc.jabirincpetclinic.services;

import com.jabirinc.jabirincpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
