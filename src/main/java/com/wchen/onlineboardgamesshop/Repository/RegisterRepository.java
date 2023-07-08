package com.wchen.onlineboardgamesshop.Repository;

import com.wchen.onlineboardgamesshop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegisterRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "insert into online_shop.user(username, password, email, role) VALUES (:name, :password, :email, :role)")
    public void createUser(@Param("name") String name, @Param("password") String pwd, @Param("email") String email, @Param("role") String role);

}
