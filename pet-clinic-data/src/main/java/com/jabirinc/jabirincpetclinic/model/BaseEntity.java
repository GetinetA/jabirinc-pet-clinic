package com.jabirinc.jabirincpetclinic.model;

import java.io.Serializable;

/**
 * Created by Getinet on 2019-02-26
 */
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
