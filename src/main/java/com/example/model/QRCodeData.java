package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class QRCodeData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String producerName;
    private String batchNo;
    private String productType;
    private String productItem;
    private LocalDate productionDate;
    private LocalDate expiryDate;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductItem() {
        return productItem;
    }

    public void setProductItem(String productItem) {
        this.productItem = productItem;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Method to convert data to CSV format
    public String toCsvString() {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(expiryDate).append(",");
        csvBuilder.append(productionDate).append(",");
        csvBuilder.append(id).append(",");
        csvBuilder.append(batchNo).append(",");
        csvBuilder.append(productType).append(",");
        csvBuilder.append(producerName).append(",");
        csvBuilder.append(productItem);
        return csvBuilder.toString();
    }
}

