package com.jabirinc.jabirincpetclinic.services.map;

import com.jabirinc.jabirincpetclinic.model.Vet;
import com.jabirinc.jabirincpetclinic.services.CrudService;

import java.util.Set;

/**
 * Created by Getinet on 2019-02-28
 */
public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet type) {
        return super.save(type.getId(), type);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
