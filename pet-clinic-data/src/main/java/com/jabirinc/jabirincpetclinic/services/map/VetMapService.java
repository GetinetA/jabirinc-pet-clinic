package com.jabirinc.jabirincpetclinic.services.map;

import com.jabirinc.jabirincpetclinic.model.Speciality;
import com.jabirinc.jabirincpetclinic.model.Vet;
import com.jabirinc.jabirincpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Getinet on 2019-02-28
 */
@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityMapService specialityMapService;

    public VetMapService(SpecialityMapService specialityServiceMap) {
        this.specialityMapService = specialityServiceMap;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if (object.getSpecialities().size() > 0) {

            object.getSpecialities().forEach(speciality -> {

                // check if speciality is already persisted; if not save and get id
                if (speciality.getId() == null) {
                    Speciality savedSpeciality = specialityMapService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }

            });
        }

        return super.save(object);
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
