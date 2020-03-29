package com.raketlabs.qr.controller;


import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.raketlabs.qr.generator.BarbecueBarcodeGenerator;
import com.raketlabs.qr.generator.Barcode4jBarcodeGenerator;
import com.raketlabs.qr.generator.QRGenBarcodeGenerator;
import com.raketlabs.qr.generator.ZxingBarcodeGenerator;
import com.raketlabs.util.ControllerUtils;

@RestController
@RequestMapping("/barcodes")
public class BarcodesController {
    
    @Autowired HttpServletRequest request;

    //Barbecue library

    @GetMapping(value = "/barbecue/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueUPCABarcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(BarbecueBarcodeGenerator.generateUPCABarcodeImage(barcode));
    }

    @GetMapping(value = "/barbecue/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(BarbecueBarcodeGenerator.generateEAN13BarcodeImage(barcode));
    }

    @PostMapping(value = "/barbecue/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueCode128Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(BarbecueBarcodeGenerator.generateCode128BarcodeImage(barcode));
    }

    @PostMapping(value = "/barbecue/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecuePDF417Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(BarbecueBarcodeGenerator.generatePDF417BarcodeImage(barcode));
    }

    //Barcode4j library

    @GetMapping(value = "/barcode4j/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode4jUPCABarcode(@PathVariable("barcode") String barcode) {
        return okResponse(Barcode4jBarcodeGenerator.generateUPCABarcodeImage(barcode));
    }

    @GetMapping(value = "/barcode4j/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode4jEAN13Barcode(@PathVariable("barcode") String barcode) {
        return okResponse(Barcode4jBarcodeGenerator.generateEAN13BarcodeImage(barcode));
    }

    @PostMapping(value = "/barcode4j/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode4jCode128Barcode(@RequestBody String barcode) {
        return okResponse(Barcode4jBarcodeGenerator.generateCode128BarcodeImage(barcode));
    }

    @PostMapping(value = "/barcode4j/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode4jPDF417Barcode(@RequestBody String barcode) {
        return okResponse(Barcode4jBarcodeGenerator.generatePDF417BarcodeImage(barcode));
    }

    //Zxing library

    @GetMapping(value = "/zxing/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingUPCABarcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateUPCABarcodeImage(barcode));
    }

    @GetMapping(value = "/zxing/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingEAN13Barcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateEAN13BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingCode128Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateCode128BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingPDF417Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generatePDF417BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateQRCodeImage(barcode));
    }

    //QRGen

    @PostMapping(value = "/qrgen/qrcode")
    public RedirectView qrgenQRCode (String barcode, RedirectAttributes redirect) throws Exception {
        File qrFile = QRGenBarcodeGenerator.generateQRCodeFile(barcode);
        return new RedirectView("/qr/" + qrFile.getName(), true);
    }
    
    
    @GetMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> qrgenGet(HttpServletRequest request) throws Exception {
        
        BufferedImage bufferedImage = ControllerUtils.retrieveFlashAttribute(request, "bufferedImage");
        
        if (bufferedImage != null) {
            return okResponse(bufferedImage); 
        }
        
        return null;
    }

    
    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        
        String referer = request.getHeader("referer");
        
        if (referer != null && referer.contains(baseUrl()))
        return new ResponseEntity<>(image, HttpStatus.OK);
        
        return null;
    }
    
    
    public String baseUrl () {
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return baseUrl;
    }
}
