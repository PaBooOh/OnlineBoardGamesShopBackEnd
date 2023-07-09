package com.wchen.onlineboardgamesshop.Controller;

import com.wchen.onlineboardgamesshop.Entity.User;
import com.wchen.onlineboardgamesshop.MyUtils.JwtUtil;
import com.wchen.onlineboardgamesshop.Service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/register")
@RestController
public class RegisterController {

    private final RegisterService registerService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    public RegisterController(RegisterService registerService)
    {
        this.registerService = registerService;
    }


    @PostMapping("/registerByUsername")
    public ResponseEntity<?> register(@RequestBody User frontEndUser)
    {
        User user = registerService.register(frontEndUser.getUserName(), frontEndUser.getPassword(), frontEndUser.getEmail());
        Map<String, Object> body = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        // Wrong password or username does not exist
        logger.info(frontEndUser.getUserName());
        logger.info(frontEndUser.getPassword());
        logger.info(frontEndUser.getEmail());
        if (user == null) {
            logger.info("Register Failed");
            body.put("success", false);
            body.put("data", data);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }

        // Success
        logger.info("Register Succeed");

        // Tokens
        String jwt = JwtUtil.generateAccessToken(user.getUserName()); // 创建访问令牌
        String refreshToken = JwtUtil.generateRefreshToken(user.getUserName()); // 创建刷新令牌
        Date expirationDate = JwtUtil.getExpirationDateFromToken(jwt); // 获取令牌过期时间
        data.put("username", user.getUserName());
        List<String> roles = new ArrayList<>();
        roles.add(user.getRole());
        body.put("success", true);
        data.put("roles", roles);
        data.put("accessToken", jwt);
        data.put("refreshToken", refreshToken);
        data.put("expires", expirationDate);
        body.put("data", data);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}

