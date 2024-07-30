package com.example.Controller;

import com.example.Repo.QRCodeDataRepository;
import com.example.Service.QRCodeService;
import com.example.model.QRCodeData;
import com.example.model.QRCodeForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;

@Controller
public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private QRCodeDataRepository qrCodeDataRepository;
    @RequestMapping("/form")
    public String index(Model model, HttpSession session) {
        String firstname = (String) session.getAttribute("firstname");
        if (firstname != null) {
            model.addAttribute("firstname", firstname);
        }
        model.addAttribute("qrCodeForm", new QRCodeForm());
        return "index";
    }
    @PostMapping("/showQRCode")
    public String showQRCode(@ModelAttribute QRCodeForm qrCodeForm, Model model) {
        String qrCodeContent = " Producer Name: " + qrCodeForm.getProducerName() +
                " Batch Number: " + qrCodeForm.getBatchNo() +
                " Product Type: " + qrCodeForm.getProductType() +
                " Product Item: " + qrCodeForm.getProductItem() +
                " Production Date: " + qrCodeForm.getProductionDate() +
                " Expiry Date: " + qrCodeForm.getExpiryDate();

        byte[] qrCode = qrCodeService.generateQRCode(qrCodeContent, 300, 300);
        String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCode);
        model.addAttribute("qrCodeContent", qrCodeBase64);
        model.addAttribute("expiryDate", qrCodeForm.getExpiryDate());

        // Save form data to the database
        QRCodeData qrCodeData = new QRCodeData();
        qrCodeData.setProducerName(qrCodeForm.getProducerName());
        qrCodeData.setBatchNo(qrCodeForm.getBatchNo());
        qrCodeData.setProductType(qrCodeForm.getProductType());
        qrCodeData.setProductItem(qrCodeForm.getProductItem());
        qrCodeData.setProductionDate(qrCodeForm.getProductionDate());
        qrCodeData.setExpiryDate(qrCodeForm.getExpiryDate());
        qrCodeDataRepository.save(qrCodeData);

        return "show-qr-code";
    }
}
