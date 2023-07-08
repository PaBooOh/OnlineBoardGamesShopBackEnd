package com.wchen.onlineboardgamesshop.Service;

import com.wchen.onlineboardgamesshop.Entity.User;

public interface UserService {
    User findUserByUserName(String username);
    User findUserByUUid(long uuid);
}
