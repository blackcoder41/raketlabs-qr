package com.raketlabs.qr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raketlabs.qr.QRForm;
import com.raketlabs.qr.repository.QRFormRepository;

@Service
public class QRFormServiceImpl implements QRFormService {

    @Autowired
    QRFormRepository mQRFormRepository;
    
    @Override
    public QRForm save(QRForm qrForm) {
        
        mQRFormRepository.save(qrForm);
        return null;
    }

    @Override
    public List<QRForm> list() {
        return mQRFormRepository.findAll();
    }
    
    
}
