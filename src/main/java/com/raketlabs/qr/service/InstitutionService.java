package com.raketlabs.qr.service;

import java.util.List;

import com.raketlabs.qr.Institution;

public interface InstitutionService {

    public Institution save (Institution institution);
    
    public List<Institution> getList ();
    
    public String getCode (Long id);
}
