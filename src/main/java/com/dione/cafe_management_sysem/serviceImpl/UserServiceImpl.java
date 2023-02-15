package com.dione.cafe_management_sysem.serviceImpl;

import com.dione.cafe_management_sysem.POJO.User;
import com.dione.cafe_management_sysem.constents.CafeConstants;
import com.dione.cafe_management_sysem.dao.UserDao;
import com.dione.cafe_management_sysem.service.UserService;
import com.dione.cafe_management_sysem.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j  //Cette annotation est utilisée pour créer une instance du logger SLF4J
@Service
public class UserServiceImpl implements UserService {

    //Injection dépendances de UserDao
    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        if (valudateSignUpMap(requestMap)){
            User user = userDao.findByEmailId(requestMap.get("email"));
   /*         On va établir une condition comme quoi que si l'email n'est pas trouvé
            l'object user sera quant à lui null
            */
            if (Objects.isNull(user)){

            }else {
                //Vérification si le mail existe déjà.
                return CafeUtils.getResponseEntity("Emil already exits.", HttpStatus.BAD_REQUEST);
            }
        }else {
            return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private boolean valudateSignUpMap(Map<String, String> requestMap){
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")  &&
                requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }
        else {
            return false;
        }
    }
}
