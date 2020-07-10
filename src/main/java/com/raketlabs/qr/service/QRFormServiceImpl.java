package com.raketlabs.qr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raketlabs.qr.QRForm;
import com.raketlabs.qr.repository.QRFormRepository;

@Service
public class QRFormServiceImpl implements QRFormService {

    @Autowired
    QRFormRepository mQRFormRepository;
    
    @Override
    public QRForm save (QRForm qrForm) {
        
        mQRFormRepository.save(qrForm);
        return null;
    }

    @Override
    public List<QRForm> list () {
        return mQRFormRepository.findAll();
    }
    
    @Override
    public Page<QRForm> list (Pageable pageable) {
        return mQRFormRepository.findAll(pageable);
    
    }
    
}
