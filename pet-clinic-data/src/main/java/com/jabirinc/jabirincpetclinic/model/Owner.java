package com.jabirinc.jabirincpetclinic.model;

import java.util.Set;

/**
 * Created by Getinet on 2019-02-25
 */
public class Owner extends Person {

    private Set<Pet> pets;

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
