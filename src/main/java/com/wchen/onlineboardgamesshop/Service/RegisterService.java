package com.wchen.onlineboardgamesshop.Service;

import com.wchen.onlineboardgamesshop.Entity.User;

public interface RegisterService {
    User register(String username, String password, String email);
}
