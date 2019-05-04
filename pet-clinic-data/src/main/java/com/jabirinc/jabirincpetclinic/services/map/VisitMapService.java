package com.jabirinc.jabirincpetclinic.services.map;

import com.jabirinc.jabirincpetclinic.model.Visit;
import com.jabirinc.jabirincpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Getinet on 2019-05-04
 */
@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {


    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Visit save(Visit visit) {

        if (visit.getPet() == null || visit.getPet().getOwner() == null
                || visit.getPet().getId() == null || visit.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invalid Visit.");
        }
        return super.save(visit);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
