package com.raketlabs.qr.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raketlabs.qr.QRForm;

public interface QRFormService {

    public QRForm save (QRForm qrForm);
    
    public List<QRForm> list ();
    
    public Page<QRForm> list (Pageable pageable);
    
}
