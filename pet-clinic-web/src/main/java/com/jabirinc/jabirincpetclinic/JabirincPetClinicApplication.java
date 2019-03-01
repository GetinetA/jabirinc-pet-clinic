package com.jabirinc.jabirincpetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * SpringBoot by default scan packages where this class is defined and below.
 * If one has a package defined outside/above this class package, we need to
 * tell and specify where SpringBoot do component scan.
 * For example if we have a package "com.jabirinc.outside", include this
 * @ComponentScan(basePackages = {"com.jabirinc.outside", "com.jabirinc.jabirincpetclinic"})
 */

public class JabirincPetClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JabirincPetClinicApplication.class, args);
    }

}
