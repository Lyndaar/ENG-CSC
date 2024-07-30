package com.example.Service;

import com.example.Repo.QRCodeScanRepository;
import com.example.model.QRCodeScanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QRCodeScanService {
    @Autowired
    private QRCodeScanRepository qrCodeScanRepository;

    public List<QRCodeScanData> saveQRCodeData(List<QRCodeScanData> qrCodeScanDataList) {
        return qrCodeScanRepository.saveAll(qrCodeScanDataList);
    }
}
