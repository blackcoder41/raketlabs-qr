package com.raketlabs.qr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raketlabs.qr.Institution;
import com.raketlabs.qr.repository.InstitutionRepository;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    InstitutionRepository mInstitutionRepository;
    
    @Override
    public Institution save(Institution institution) {
        
        mInstitutionRepository.save(institution);
        return null;
    }

    @Override
    public List<Institution> getList() {
        
        return mInstitutionRepository.findAll();
    }

    @Override
    public String getCode(Long id) {
        
        // Todo: handle not found
        return mInstitutionRepository.getOne(id).getCode();
    }
    
    
}
