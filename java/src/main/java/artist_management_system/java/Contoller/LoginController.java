package artist_management_system.java.Contoller;

import artist_management_system.java.ApiConstant.ApiConstant;
import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Security.Auth.JwtGenerator;
import artist_management_system.java.Security.Model.JwtUser;
import artist_management_system.java.Service.IUserService;
import artist_management_system.java.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserEntity user) {
        UserEntity userEntity = this.userService.findByEmail(user.getEmail(), true);

        if (userEntity == null) {
            return new ResponseEntity<>(Map.of("message","user not found or user is inactive"), HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matchPassword = passwordEncoder.matches(user.getPassword(), userEntity.getPassword());

        if(!matchPassword) {
            return new ResponseEntity<>(Map.of("message", "Wrong password","user", user), HttpStatus.UNAUTHORIZED);
        }

        JwtUser jwtUser = new JwtUser();
        jwtUser.setId(userEntity.getId());
        jwtUser.setUserName(userEntity.getEmail());

        if(userEntity.getRole() != null) {
            List<String> roles = StringUtils.getCommaSeparatedArray(userEntity.getRole().toString(), ",");
            jwtUser.setRoles(roles);
        }
        userEntity.setToken(jwtGenerator.generate(jwtUser));

//        return new ResponseEntity<>(Map.of("message", "Welcome " + userEntity.getFirstName()), HttpStatus.OK);
        return new ResponseEntity<>(Map.of("message", "Welcome " + userEntity.getFirstName(), "user", userEntity), HttpStatus.OK);
    }


    @PostMapping(ApiConstant.REGISTER)
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserEntity user) {
        UserEntity userEntity = this.userService.findByEmail(user.getEmail(), false);
        if(userEntity != null) {
            return new ResponseEntity<>(Map.of("message","User already exist"), HttpStatus.CONFLICT);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity1 = this.userService.save(user);
        if (userEntity1 == null) {
            return new ResponseEntity<>(Map.of("message","User not registered"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("message","User registered successfully"), HttpStatus.CREATED);
    }
}
