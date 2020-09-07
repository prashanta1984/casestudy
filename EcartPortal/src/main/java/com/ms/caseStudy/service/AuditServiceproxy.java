package com.ms.caseStudy.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ms.caseStudy.bean.AuditData;

@FeignClient(name = "AuditMS", fallback = AuditServiceFallback.class)
public interface AuditServiceproxy {
	
	@RequestMapping(value = "/audits/allLogs", method = RequestMethod.GET)
	public List<AuditData> getAuditLogs(@RequestHeader("Authorization") String token );
	
}
