package com.joalvarez.baseframework.config;

import com.joalvarez.baseframework.data.dto.EndPointSecurityDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(
        prefix = "api.security"
)
public class EndPointSecurityConfig {

    private List<EndPointSecurityDTO> endpoints;

    public List<EndPointSecurityDTO> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<EndPointSecurityDTO> endpoints) {
        this.endpoints = endpoints;
    }
}
