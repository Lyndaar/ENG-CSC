package com.example.Repo;

import com.example.model.QRCodeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QRCodeDataRepository extends JpaRepository<QRCodeData, Long> {
    List<QRCodeData> findByIdBetween(Long startId, Long endId);

}
