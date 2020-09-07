package com.ms.caseStudy.AuditMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.caseStudy.AuditMS.model.AuditData;
import com.ms.caseStudy.AuditMS.model.AuditRepository;

@RestController
@RequestMapping("/audits")
public class AuditController {
	
	@Autowired
	AuditRepository auditRepository;
	
	@GetMapping("/allLogs")
	public List<AuditData> getLogs() {
		List<AuditData> auditLogs = auditRepository.findAll();
		System.out.println("size "+auditLogs.size());
		return auditLogs;
	}
}
