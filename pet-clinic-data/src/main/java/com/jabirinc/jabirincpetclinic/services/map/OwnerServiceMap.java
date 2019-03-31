package com.jabirinc.jabirincpetclinic.services.map;

import com.jabirinc.jabirincpetclinic.model.Owner;
import com.jabirinc.jabirincpetclinic.model.Pet;
import com.jabirinc.jabirincpetclinic.model.PetType;
import com.jabirinc.jabirincpetclinic.services.OwnerService;
import com.jabirinc.jabirincpetclinic.services.PetService;
import com.jabirinc.jabirincpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Getinet on 2019-02-27
 */
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {

        if (owner != null) {

            if (owner.getPets() != null) {

                owner.getPets().forEach(pet -> {

                    // check if pet type is already persisted; if not save and get id
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            PetType savedPetType = petTypeService.save(pet.getPetType());
                            pet.getPetType().setId(savedPetType.getId());
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required.");
                    }

                    // check if pet is already persisted; if not save and get id
                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }

            return super.save(owner);

        } else {
            return owner;
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
