package com.naiden.digitallab.service;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.repository.CompoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompoundService {

    @Autowired
    private CompoundRepository compoundRepository;

    public Object findAll(){
        return compoundRepository.findAll();
    }

    public Compound findById(Long id){
        return compoundRepository.findOne(id);
    }

    public Compound save(Compound compound){

        return compoundRepository.save(compound);
    }
}
