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
        log.info("Inside signUp {}", requestMap);
       try {


        if (valudateSignUpMap(requestMap)){
            User user = userDao.findByEmailId(requestMap.get("email"));
   /*         On va établir une condition comme quoi que si l'email n'est pas trouvé
            l'object user sera quant à lui null
            Et dans ce cas on pourra créer un nouveau user sinon c'est parce que l' email existe déjà.
            */
            if (Objects.isNull(user)){
                userDao.save(getUserFromMap(requestMap));
                return CafeUtils.getResponseEntity("Successful Registered", HttpStatus.OK);
            }else {
                //Vérification si le mail existe déjà.
                return CafeUtils.getResponseEntity("Email already exits.", HttpStatus.BAD_REQUEST);
            }
        }else {
            return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WEBT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Les éléments indispensable avant validation du formulaire d'inscription
    private boolean valudateSignUpMap(Map<String, String> requestMap){
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")  &&
                requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }
        else {
            return false;
        }
    }

    //Création d'une classe qui va extraire la valeur de requestMap
    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setPassword(requestMap.get("password"));
        user.setRole(requestMap.get("role"));
        return user;
    }
}
