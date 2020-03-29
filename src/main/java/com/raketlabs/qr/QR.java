package com.raketlabs.qr;

import java.util.ArrayList;

import com.raketlabs.util.CRC;

public class QR {
    
    public static String PAYLOAD_FORMAT_INDICATION  = "00";
    public static String POINT_OF_INITIATION_METHOD = "01";
    public static String MERCHANT_ACCOUNT_INFO      = "27";
    public static String PAYMENT_SYSTEM_UNIQUE_ID   = "00";
    public static String ACQUIRER_ID                = "01";
    public static String PAYMENT_TYPE               = "02";
    public static String MERCHANT_CREDIT_ACCOUNT    = "04";
    public static String MOBILE_NUMBER              = "05";
    public static String MERCHANT_CATEGORY_CODE     = "52";
    public static String TRANSACTION_CURRENCY_CODE  = "53";
    public static String COUNTRY_CODE               = "58";
    public static String MERCHANT_NAME              = "59";
    public static String MERCHANT_CITY              = "60";
    public static String CYCLIC_REDUNDANCY_CHECK    = "63";
    
    private DataBlock data;
    
    public QR (DataBlock data) {
        this.data = data;
    }
    
    public QR (String data) {
        
    }
    
    public String getStringData () {
        
        String strData = data.toString().substring(0, data.toString().length()-4);
        
        data.getField(QR.CYCLIC_REDUNDANCY_CHECK).setValue(CRC.CRCFFFF(strData));
        
        return data.toString();
    }
    
    @Override public String toString () {
        
        return getStringData();
    }
}
