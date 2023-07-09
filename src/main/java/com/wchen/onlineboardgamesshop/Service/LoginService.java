package com.wchen.onlineboardgamesshop.Service;

import com.wchen.onlineboardgamesshop.Entity.User;

public interface LoginService {
    User login(String username, String password);
//    User findUserByUUid(long uuid);
//    User findUserByEmail(String email);
}
