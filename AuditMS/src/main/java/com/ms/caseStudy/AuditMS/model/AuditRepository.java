package com.ms.caseStudy.AuditMS.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AuditRepository extends JpaRepository<AuditData, Integer> {
	
}
