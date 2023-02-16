package com.dione.cafe_management_sysem.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    public ResponseEntity<String> signUp(Map<String, String> requestMap);
}
