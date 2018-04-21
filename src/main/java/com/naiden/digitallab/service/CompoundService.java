package com.naiden.digitallab.service;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.repository.CompoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompoundService {

    @Autowired
    private CompoundRepository compoundRepository;

    public Iterable<Compound> findAll() {
        return compoundRepository.findAll();
    }

    public Compound findById(Long id) {
        return compoundRepository.findOne(id);
    }

    public Compound save(Compound compound) {
        String iupacName = compound.getIupacName();
        if (compound.getShortName().isEmpty() && !iupacName.isEmpty()) compound.setShortName(iupacName);
        return compoundRepository.save(compound);
    }

    public void deleteById(Long aLong) {
        compoundRepository.delete(aLong);
    }

    public void update(Compound compound) {
        compoundRepository.save(compound);
    }

}
