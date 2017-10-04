package com.naiden.digitallab.repository;

import com.naiden.digitallab.model.Compound;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompoundRepository extends CrudRepository<Compound, Long> {
    List<Compound> findByIupacName(String iupacName);
    List<Compound> findByCid(String cid);
    List<Compound> findByShortName(String shortName);
    void deleteById(Long id);
}
