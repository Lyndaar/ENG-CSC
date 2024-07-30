package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "qr_codes")
public class QRCodeScanData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producerName", nullable = false)
    private String producerName;

    @Column(name = "batchNo", nullable = false)
    private String batchNo;

    @Column(name = "productType", nullable = false)
    private String productType;

    @Column(name = "productItem", nullable = false)
    private String productItem;

    @Column(name = "productionDate", nullable = false)
    private String productionDate;

    @Column(name = "expiryDate", nullable = false)
    private String expiryDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "vehicleNumber", nullable = false)
    private String vehicleNumber;

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

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
