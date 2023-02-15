package com.dione.cafe_management_sysem.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    public default ResponseEntity<String> signUp(Map<String, String> requestMap){
        return null;
    };
}
