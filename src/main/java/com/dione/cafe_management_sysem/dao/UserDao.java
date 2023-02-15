package com.dione.cafe_management_sysem.dao;

import com.dione.cafe_management_sysem.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmailId(@Param("email")String email);
}
