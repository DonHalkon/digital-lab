package com.naiden.digitallab.service;

import com.naiden.digitallab.model.Reagent;
import com.naiden.digitallab.repository.ReagentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReagentService {

    private final ReagentRepository reagentRepository;

    @Autowired
    public ReagentService(ReagentRepository reagentRepository) {
        this.reagentRepository = reagentRepository;
    }

    public Object findAll(){
        return reagentRepository.findAll();
    }

    public Reagent findById(Long id){
        return reagentRepository.findById(id).get();
    }

    public Reagent save(Reagent reagent){
        return reagentRepository.save(reagent);
    }
}
