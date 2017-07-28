package com.govpro.config.audit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.govpro.domain.PersistentAuditEvent;

@Component
public class AuditEventConverter {
	
	
	
	public List<AuditEvent> convertToAuditEvent(Iterable<PersistentAuditEvent> persistentAuditEvents) {
        if (persistentAuditEvents == null) {
            return Collections.emptyList();
        }
        List<AuditEvent> auditEvents = new ArrayList<>();
        for (PersistentAuditEvent persistentAuditEvent : persistentAuditEvents) {
            auditEvents.add(convertToAuditEvent(persistentAuditEvent));
        }
        return auditEvents;
    }
	
	 public AuditEvent convertToAuditEvent(PersistentAuditEvent persistentAuditEvent) {
	        if (persistentAuditEvent == null) {
	            return null;
	        }
	        return new AuditEvent(Date.from(persistentAuditEvent.getAuditEventDate()), persistentAuditEvent.getPrincipal(),
	            persistentAuditEvent.getAuditEventType(), convertToObjects(persistentAuditEvent.getData()));
	    }


	
	public Map<String, Object> convertToObjects(Map<String, String> data) {
        Map<String, Object> results = new HashMap<>();

        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                results.put(entry.getKey(), entry.getValue());
            }
        }
        return results;
    }
	
	public Map<String,String> convertToStrings(Map<String, Object> data){
		Map<String,String> strings=new HashMap<>();
		
		if(data!=null){
			for(Map.Entry<String, Object> entry:data.entrySet())
			{
				Object object=entry.getValue();
				if(object instanceof WebAuthenticationDetails){
					WebAuthenticationDetails authenticationDetails=(WebAuthenticationDetails)object;
					strings.put("remoteAddress", authenticationDetails.getRemoteAddress());
					strings.put("sessionId", authenticationDetails.getSessionId());
				}else if(object!=null){
					strings.put(entry.getKey(), object.toString());
				}else{
					strings.put(entry.getKey(), "null");
				}
				
			}
		}
		
		return strings;
	}
}
