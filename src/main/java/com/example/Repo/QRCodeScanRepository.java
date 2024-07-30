package com.example.Repo;

import com.example.model.QRCodeScanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeScanRepository extends JpaRepository<QRCodeScanData, Long> {
}