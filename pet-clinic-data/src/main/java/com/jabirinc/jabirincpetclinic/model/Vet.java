package com.jabirinc.jabirincpetclinic.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Getinet on 2019-02-25
 */
public class Vet extends Person {

    private Set<Speciality> specialities = new HashSet<>();

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
