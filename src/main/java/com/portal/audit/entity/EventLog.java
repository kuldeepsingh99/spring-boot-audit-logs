package com.portal.audit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "event_log")
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "action")
    private String action;

    @Column(name = "performed_by")
    private String performedBy;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public EventLog(String serviceName, String methodName, String action, String performedBy) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = LocalDateTime.now();
    }
}
