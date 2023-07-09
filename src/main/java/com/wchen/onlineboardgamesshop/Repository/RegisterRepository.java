package com.wchen.onlineboardgamesshop.Repository;

import com.wchen.onlineboardgamesshop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RegisterRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into online_shop.user(username, password, email, role) VALUES (:username, :password, :email, :role)")
    public void createUser(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("role") String role);

    @Query(nativeQuery = true, value = "select * from online_shop.user where user.username = :username")
    User findUserByUserName(@Param("username") String username);
}
