package com.jabirinc.jabirincpetclinic.services.map;

import com.jabirinc.jabirincpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    final Long ownerId = 100L;
    final String lastName = "Jabir";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 23L;
        Owner owner2 = Owner.builder().id(id).build();
        ownerMapService.save(owner2);
        assertEquals(id, owner2.getId());
    }

    @Test
    void saveNoId() {
        Owner noIdOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(noIdOwner);
        assertNotNull(noIdOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner jabir = ownerMapService.findByLastName(lastName);
        assertNotNull(jabir);
        assertEquals(lastName, jabir.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner jabir = ownerMapService.findByLastName("foo");
        assertNull(jabir);
    }
}