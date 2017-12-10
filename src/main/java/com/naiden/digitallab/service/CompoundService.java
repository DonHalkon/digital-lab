package com.naiden.digitallab.service;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.repository.CompoundRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IMolecularFormula;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.MolecularFormulaManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

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

    public Compound save(Compound compound) throws Exception {
        String iupacName = compound.getIupacName();
//        setMolecularFormulaBySmiles(compound);
        if (compound.getShortName().isEmpty() && !iupacName.isEmpty()) compound.setShortName(iupacName);
        return compoundRepository.save(compound);
    }


//    public void setMolecularFormulaBySmiles(Compound compound) {
//        if (!compound.getFormula().isEmpty()) return;
//        try {
//            SmilesParser sp = new SmilesParser(SilentChemObjectBuilder.getInstance());
//            IAtomContainer mol = sp.parseSmiles(compound.getSmiles());
//            IMolecularFormula molecularFormula = MolecularFormulaManipulator.getMolecularFormula(mol);
//            compound.setFormula(MolecularFormulaManipulator.getString(molecularFormula));
//        } catch (InvalidSmilesException e) {
//            e.printStackTrace();
//        }
//    }

    public void deleteById(Long aLong) {
        compoundRepository.delete(aLong);
    }

    public void update(Compound compound) {
        compoundRepository.save(compound);
    }

}
