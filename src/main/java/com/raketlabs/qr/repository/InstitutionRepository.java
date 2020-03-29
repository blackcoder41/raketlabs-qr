package com.raketlabs.qr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.raketlabs.qr.Institution;

@Repository
@Component
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
