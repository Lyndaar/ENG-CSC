package com.example.Controller;

import com.example.Service.QRCodeScanService;
import com.example.model.QRCodeScanData;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QRCodeScanController {
    @Autowired
    private QRCodeScanService qrCodeScanService;

    // Handler for showing scanner page
    @GetMapping("/scan")
    public String showScannerPage() {
        return "scanner";
    }

    // Handler for saving scanned QR code data
    @PostMapping("/save")
    public ResponseEntity<?> saveQRCodeData(@RequestBody List<QRCodeScanData> dataList) {
        try {
            List<QRCodeScanData> savedData = qrCodeScanService.saveQRCodeData(dataList);
            return ResponseEntity.ok(savedData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving data: " + e.getMessage());
        }
    }
}

