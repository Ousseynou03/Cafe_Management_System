package com.dione.cafe_management_sysem.restImpl;

import com.dione.cafe_management_sysem.constents.CafeConstants;
import com.dione.cafe_management_sysem.rest.UserRest;
import com.dione.cafe_management_sysem.service.UserService;
import com.dione.cafe_management_sysem.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.dione.cafe_management_sysem.utils.CafeUtils.getResponseEntity;


@RestController
public class UserRestImpl implements UserRest {
    //Injection des dépendances
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            userService.signUp(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WEBT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
