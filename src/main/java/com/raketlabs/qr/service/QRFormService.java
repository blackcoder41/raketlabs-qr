package com.raketlabs.qr.service;

import java.util.List;

import com.raketlabs.qr.QRForm;

public interface QRFormService {

    public QRForm save (QRForm qrForm);
    
    public List<QRForm> list ();
    
}
