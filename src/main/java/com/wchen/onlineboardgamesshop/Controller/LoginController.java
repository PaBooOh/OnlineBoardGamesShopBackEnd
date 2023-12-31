package com.wchen.onlineboardgamesshop.Controller;

import com.wchen.onlineboardgamesshop.Entity.User;
import com.wchen.onlineboardgamesshop.MyUtils.JwtUtil;
import com.wchen.onlineboardgamesshop.Service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/loginByUsername")
    public ResponseEntity<?> login(@RequestBody User frontEndUser)
    {
        User user = loginService.login(frontEndUser.getUserName(), frontEndUser.getPassword());
        Map<String, Object> body = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        // Wrong password or username does not exist
        logger.info(frontEndUser.getUserName());
        logger.info(frontEndUser.getPassword());
        if (user == null) {
            logger.info("Not found");
            body.put("success", false);
            body.put("data", data);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }

        // Success
        logger.info("Login Success");
        body.put("success", true);
        // Tokens
        String jwt = JwtUtil.generateAccessToken(user.getUserName()); // 创建访问令牌
        String refreshToken = JwtUtil.generateRefreshToken(user.getUserName()); // 创建刷新令牌
        Date expirationDate = JwtUtil.getExpirationDateFromToken(jwt); // 获取令牌过期时间
        data.put("username", user.getUserName());
        List<String> roles = new ArrayList<>();
        roles.add(user.getRole());
        data.put("roles", roles); // 假设你的用户类有一个返回角色列表的方法
        data.put("accessToken", jwt);
        data.put("refreshToken", refreshToken);
        data.put("expires", expirationDate);
        body.put("data", data);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

//    @PostMapping("/findUserByUUid")
//    public ResponseEntity<?> findUserByUUid(@RequestBody User frontEndUser)
//    {
//        User user = loginService.findUserByUUid(frontEndUser.getUuid());
//        if (user != null && user.getPassword().equals(frontEndUser.getPassword()))
//        {
//            return ResponseEntity.ok("Matching"); // 200
//        }
//        return ResponseEntity.notFound().build(); // 404
//    }
}
