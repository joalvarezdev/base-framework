package com.joalvarez.baseframework.data.dto;

import java.util.List;

public class EndPointSecurityDTO implements BaseDTO{

    private String path;
    private List<String> authorities;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
