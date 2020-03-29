package com.raketlabs.qr.generator;

import static org.junit.Assert.assertThat;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.glxn.qrgen.javase.QRCode;

public class QRGenBarcodeGenerator {

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(300, 300)
                .withErrorCorrection(ErrorCorrectionLevel.H)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
        
        return ImageIO.read(bis);
    }
    
    
    public static File generateQRCodeFile(String barcodeText) throws Exception {
        
        File qrFile = QRCode
            .from(barcodeText)
            .withSize(300, 300)
            .withErrorCorrection(ErrorCorrectionLevel.H)
            .file();
        
        String qrfilename = qrFile.getName();
        
        File copied = new File("./public/" + qrFile.getName());
        Files.copy(qrFile.toPath(), copied.toPath(), StandardCopyOption.REPLACE_EXISTING);

        boolean exist = copied.exists();
        
        return copied;
    }
    
    
}