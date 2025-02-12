package com.portal.audit.service;

import com.portal.audit.entity.EventLog;
import com.portal.audit.repository.EventLogRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EventLogService {

    private final EventLogRepository eventLogRepository;

    public EventLogService(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    @Async
    public CompletableFuture<Void> logEvent(String serviceName, String methodName, String action, String performedBy) {
        return CompletableFuture.runAsync(() -> {
            EventLog eventLog = new EventLog(serviceName, methodName, action, performedBy);
            eventLogRepository.save(eventLog);
        });
    }
}
