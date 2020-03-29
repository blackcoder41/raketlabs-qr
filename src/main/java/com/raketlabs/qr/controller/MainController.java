    package com.raketlabs.qr.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.raketlabs.model.GenericResponse;
import com.raketlabs.qr.DataBlock;
import com.raketlabs.qr.Institution;
import com.raketlabs.qr.QR;
import com.raketlabs.qr.QRForm;
import com.raketlabs.qr.service.InstitutionService;
import com.raketlabs.qr.service.QRFormService;
import com.raketlabs.util.ControllerUtils;
import com.raketlabs.util.StringUtils;


@Controller
public class MainController {

    @Autowired
    QRFormService mQRFormService;
    
    @Autowired
    InstitutionService mInstitutionService;
    
    
    /**
     * This is the home page and it shows the account details form
     * @param model
     * @return
     */
    @RequestMapping(value = {"/"})
    public String index (Model model) {
        
        model.addAttribute("institutionList", mInstitutionService.getList());
        return "forms/create-qr";
    }
    
    
    /**
     * This generates the QR data as JSON
     * @param institutionId
     * @param accountName
     * @param accountNumber
     * @param mobileNumber
     * @param city
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value="/generate/qr", method=RequestMethod.POST)
    public ResponseEntity<Object> generate (
        @RequestParam(required=true, name="institution") Long institutionId,
        String accountName,
        String accountNumber,
        String mobileNumber,
        String city,
        HttpServletRequest httpServletRequest) {
        
        String acquirerId = mInstitutionService.getCode(institutionId);
        QR qr = generateQRData(acquirerId, accountName, accountNumber, mobileNumber, city);
        
        HttpStatus status = HttpStatus.OK;
        String message = "Thank you for your feedback.";
        
        return new ResponseEntity<Object>(new GenericResponse(status.value(), qr.toString()), status); 
    }
    
    
    @GetMapping("/generate/qr/html")
    public String viewQR (HttpServletRequest request) {
        
        QR qr  = ControllerUtils.retrieveFlashAttribute(request, "qr");
        if (qr != null) return "forms/generate-qr";
        return "redirect:/";
    }
    
    /**
     * This generates and display the QR code
     * @param institutionId
     * @param accountName
     * @param accountNumber
     * @param mobileNumber
     * @param city
     * @param model
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/generate/qr/html")
    public String generate (
        @RequestParam(required=true, name="institution") Long institutionId,
        String accountName,
        String accountNumber,
        String mobileNumber,
        String city,  
        RedirectAttributes redirectAttributes,
        HttpServletRequest httpServletRequest) {
        
        String acquirerId = mInstitutionService.getCode(institutionId);
        QR qr = generateQRData(
                acquirerId,
                accountName, 
                accountNumber, 
                mobileNumber, 
                city);
        
        redirectAttributes.addFlashAttribute("qr", qr);
        
        return "redirect:/generate/qr/html";
    }
    
    
    /**
     * This page shows the form to create/add an Institution or BSIF
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/c/institution")
    public String createInstitutionForm (
        HttpServletRequest httpServletRequest) {
        return "forms/c-institution";        
    }
    
    
    /**
     * This persists the submitted institution in the database
     * @param name
     * @param code
     * @param model
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/c/institution")
    public String createInstitutionForm (
        @RequestParam(required=true, name="institution") String name,
        @RequestParam(required=true, name="code") String code, 
        Model model,
        HttpServletRequest httpServletRequest) {
        
        Institution institution = new Institution();
        institution.setName(name);
        institution.setCode(code);
        mInstitutionService.save(institution);
        
        return "forms/c-institution";        
    }
    
    
    /**
     * This shows the list of submitted QR details
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/r/qrforms")
    public String listQRForms (Model model) {
        model.addAttribute("qrFormList", mQRFormService.list());
        return "forms/r-qrforms";        
    }
    
    /**
     * This generates the QR data and persists the fields in the database
     * @param acquirerId
     * @param accountName
     * @param accountNumber
     * @param mobileNumber
     * @param city
     * @return
     */
    protected QR generateQRData (String acquirerId, String accountName, String accountNumber, String mobileNumber, String city) {
        
        QRForm qrForm = new QRForm();
        qrForm.setAcquirerId(acquirerId);
        qrForm.setAccountName(accountName);
        qrForm.setAccountNumber(accountNumber);
        qrForm.setMobileNumber(mobileNumber);
        qrForm.setCity(city);
        
        mQRFormService.save(qrForm);
        
        DataBlock merchantInfo = new DataBlock()
        .addField(QR.PAYMENT_SYSTEM_UNIQUE_ID,   "com.p2pqrpay")
        .addField(QR.ACQUIRER_ID,                StringUtils.left(acquirerId, 11))
        .addField(QR.PAYMENT_TYPE,               "99964403")
        .addField(QR.MERCHANT_CREDIT_ACCOUNT,    StringUtils.left(accountNumber, 19))
        .addField(QR.MOBILE_NUMBER,              StringUtils.left(mobileNumber, 15));
        
        DataBlock qrData = new DataBlock()
        .addField(QR.PAYLOAD_FORMAT_INDICATION,  "01")
        .addField(QR.POINT_OF_INITIATION_METHOD, "11")
        .addField(QR.MERCHANT_ACCOUNT_INFO,      merchantInfo.toString())
        .addField(QR.MERCHANT_CATEGORY_CODE,     "6016")
        .addField(QR.TRANSACTION_CURRENCY_CODE,  "608")
        .addField(QR.COUNTRY_CODE,               "PH")
        .addField(QR.MERCHANT_NAME,              StringUtils.left(accountName, 25))
        .addField(QR.MERCHANT_CITY,              StringUtils.left(city, 15))
        .addField(QR.CYCLIC_REDUNDANCY_CHECK,    "0000");
        
        QR qr = new QR(qrData);
        
        return qr;
    }
    
    @PostMapping("/download")
    public void getFile(String data, HttpServletResponse response) {
        
        response.setContentType("image/png");
        
        byte[] decodedBytes = Base64.getMimeDecoder().decode(data);
        String decodedMime = new String(decodedBytes);
        
        try {
          response.getWriter().write(decodedMime);
          response.flushBuffer();
        } catch (IOException ex) {
          //log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
          throw new RuntimeException("IOError writing file to output stream");
        }

    }
    
    @GetMapping("/test")
    public String test () {
        return "forms/test";
    }
}