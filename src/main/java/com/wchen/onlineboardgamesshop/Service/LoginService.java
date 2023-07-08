package com.wchen.onlineboardgamesshop.Service;

import com.wchen.onlineboardgamesshop.Entity.User;

public interface LoginService {
    User findUserByUserName(String username);
    User findUserByUUid(long uuid);
}
