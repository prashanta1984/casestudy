package com.ms.caseStudy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ms.caseStudy.bean.AuditData;

@Component
public class AuditServiceFallback implements AuditServiceproxy {

	@Override
	public List<AuditData> getAuditLogs(String token) {
		// TODO Auto-generated method stub
		System.out.println("Audit log fallback");
		return new ArrayList<>();
	}

	
	
}
