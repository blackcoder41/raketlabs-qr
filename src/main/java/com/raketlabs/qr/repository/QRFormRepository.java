package com.raketlabs.qr.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.raketlabs.qr.QRForm;

@Repository
@Component
public interface QRFormRepository extends JpaRepository<QRForm, Long> {
    
}
