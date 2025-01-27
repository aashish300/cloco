package com.demo.artist_management_system.Controller;

import com.demo.artist_management_system.ApiConstant.ApiConstant;
import com.demo.artist_management_system.Model.UserEntity;
import com.demo.artist_management_system.Security.Auth.JwtGenerator;
import com.demo.artist_management_system.Security.Model.JwtUser;
import com.demo.artist_management_system.Service.IUserService;
import com.demo.artist_management_system.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    private final IUserService userService;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public LoginController(IUserService userService, JwtGenerator jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping(ApiConstant.LOGIN)
    public ResponseEntity<String> login(@RequestBody UserEntity user) {
        UserEntity userEntity = this.userService.findByEmail(user.getEmail());
        if (userEntity != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean matchPassword = passwordEncoder.matches(user.getPassword(),userEntity.getPassword());
            if (matchPassword) {
                JwtUser jwtUser = new JwtUser();
                jwtUser.setId(userEntity.getId());
                jwtUser.setUserName(userEntity.getEmail());

                if (userEntity.getRole() != null) {
                    List<String> roles = StringUtils.getCommaSeparatedArray(userEntity.getRole().toString(), ",");
                    jwtUser.setRole(roles);
                    user.setRole(userEntity.getRole());
                }

                user.setId(userEntity.getId());
                user.setToken(jwtGenerator.generate(jwtUser));
                user.setFirstName(userEntity.getFirstName());
                user.setLastName(userEntity.getLastName());
                return new ResponseEntity<>("Welcome " + userEntity.getFirstName() + userEntity.getLastName(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Incorrect Email or Password.", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("User not found.", HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
        }
    }

    @PostMapping(ApiConstant.REGISTER)
    public ResponseEntity<String> register(@RequestBody UserEntity user) {
        UserEntity userEntity = this.userService.findByEmail(user.getEmail());
        if (userEntity != null) {
            return new ResponseEntity<>("User already exist.", HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userService.save(user);
        return new ResponseEntity<>("User saved Successfully!!!", HttpStatus.OK);
    }
}
