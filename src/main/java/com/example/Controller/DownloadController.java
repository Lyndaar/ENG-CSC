package com.example.Controller;

import com.example.Service.QRCodeService;
import com.example.model.QRCodeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class DownloadController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/downloadData")
    public ResponseEntity<byte[]> downloadDataAsCsv(@RequestParam("startId") Long startId,
                                                    @RequestParam("endId") Long endId) {
        try {
            // Fetch data from the service based on the provided ID range
            List<QRCodeData> dataList = qrCodeService.getDataInRange(startId, endId);

            // Prepare CSV data
            StringBuilder csv = new StringBuilder();
            csv.append("Expiry Date,Production date,S/n,Batch Num,first select,QR content/name,second select\n");
            for (QRCodeData data : dataList) {
                csv.append(data.toCsvString()).append("\n");
            }

            // Set the CSV response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentDispositionFormData("attachment", "data.csv");

            return new ResponseEntity<>(csv.toString().getBytes(), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
