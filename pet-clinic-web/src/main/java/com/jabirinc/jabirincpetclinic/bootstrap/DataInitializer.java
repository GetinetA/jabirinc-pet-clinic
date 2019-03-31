package com.jabirinc.jabirincpetclinic.bootstrap;

import com.jabirinc.jabirincpetclinic.model.Owner;
import com.jabirinc.jabirincpetclinic.model.Pet;
import com.jabirinc.jabirincpetclinic.model.PetType;
import com.jabirinc.jabirincpetclinic.model.Vet;
import com.jabirinc.jabirincpetclinic.services.OwnerService;
import com.jabirinc.jabirincpetclinic.services.PetTypeService;
import com.jabirinc.jabirincpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by Getinet on 2019-03-01
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataInitializer(OwnerService ownerService,
                           VetService vetService,
                           PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded PetTypes....");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("15625 42nd Ave S");
        owner1.setCity("Tukwila");
        owner1.setTelephone("206abcwxyz");

        Pet mikesPet = new Pet();
        mikesPet.setName("Rosco");
        mikesPet.setBirthDate(LocalDate.of(2015,1,5));
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("15625 42nd Ave S");
        owner2.setCity("Tukwila");
        owner2.setTelephone("206abcwxyz");

        Pet fionasCat = new Pet();
        fionasCat.setName("Chelse");
        fionasCat.setBirthDate(LocalDate.of(2016,12,5));
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setOwner(owner2);
        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
