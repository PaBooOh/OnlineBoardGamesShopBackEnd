package com.wchen.onlineboardgamesshop.Controller;

import com.wchen.onlineboardgamesshop.Entity.User;
import com.wchen.onlineboardgamesshop.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private UserService loginService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/findUserByUserName")
    public ResponseEntity<?> findUserByUserName(@RequestBody User frontEndUser)
    {
        User user = loginService.findUserByUserName(frontEndUser.getUserName());
        logger.info(frontEndUser.getUserName());
        logger.info(frontEndUser.getPassword());
        if (user != null)
        {
            logger.info(user.getPassword());
        }
        if (user != null && user.getPassword().equals(frontEndUser.getPassword()))
        {
            logger.info("Matching");
            return ResponseEntity.ok("Matching");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/findUserByUUid")
    public ResponseEntity<?> findUserByUUid(@RequestBody User frontEndUser)
    {
        User user = loginService.findUserByUUid(frontEndUser.getUuid());
        if (user != null && user.getPassword().equals(frontEndUser.getPassword()))
        {
            return ResponseEntity.ok("Matching"); // 200
        }
        return ResponseEntity.notFound().build(); // 404
    }
}
