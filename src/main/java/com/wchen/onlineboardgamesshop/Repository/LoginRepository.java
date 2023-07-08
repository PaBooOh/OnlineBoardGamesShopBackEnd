package com.wchen.onlineboardgamesshop.Repository;

import com.wchen.onlineboardgamesshop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer>{

    @Query(nativeQuery = true, value = "select * from online_shop.user where user.username=:username")
    public User findUserByUserName(@Param("username") String name);

    @Query(nativeQuery = true, value = "select * from online_shop.user where user.uuid=:uuid")
    public User findUserByUUid(@Param("uuid") long uuid);

    @Query(nativeQuery = true, value = "select * from online_shop.user where user.email=:email")
    public User findUserByEmail(@Param("email") String email);

}
