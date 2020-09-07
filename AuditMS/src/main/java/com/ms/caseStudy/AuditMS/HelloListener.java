package com.ms.caseStudy.AuditMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.ms.caseStudy.AuditMS.model.AuditData;
import com.ms.caseStudy.AuditMS.model.AuditRepository;

@Component
@EnableBinding(HelloBinding.class)
public class HelloListener {

	@Autowired
	AuditRepository auditRepository;
	
    @StreamListener(target = HelloBinding.GREETING)
    public void processMessage(String msg) {
        String[] creds = msg.split("\\|");
        System.out.println(creds[0] +" : "+creds[1]);
        AuditData auditData = new AuditData();
        auditData.setTrantype("Invalid Cred");
        auditData.setUser(creds[0]);
        auditData.setPassword(creds[1]);
        auditRepository.save(auditData);
    }
}