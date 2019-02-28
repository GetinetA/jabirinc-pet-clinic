package com.jabirinc.jabirincpetclinic.services;

import com.jabirinc.jabirincpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
