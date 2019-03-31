package com.jabirinc.jabirincpetclinic.services.map;

import com.jabirinc.jabirincpetclinic.model.PetType;
import com.jabirinc.jabirincpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Getinet on 2019-03-31
 */
@Service
public class PetTypeServiceMap
        extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public PetType save(PetType type) {
        return super.save(type);
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }
}
