package com.example.Service;

import com.example.Repo.QRCodeDataRepository;
import com.example.model.QRCodeData;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class QRCodeService {

    @Autowired
    private QRCodeDataRepository qrCodeDataRepository;

    // Method to fetch data from the database based on ID range
    public List<QRCodeData> getDataInRange(Long startId, Long endId) {
        return qrCodeDataRepository.findByIdBetween(startId, endId);
    }
    public byte[] generateQRCode(String text, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, (bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF));
            }
        }

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "PNG", pngOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pngOutputStream.toByteArray();
    }
}
