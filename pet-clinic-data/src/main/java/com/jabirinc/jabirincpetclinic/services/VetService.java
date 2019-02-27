package com.jabirinc.jabirincpetclinic.services;

import com.jabirinc.jabirincpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
