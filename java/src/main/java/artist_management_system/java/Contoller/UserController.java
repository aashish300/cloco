package artist_management_system.java.Contoller;

import artist_management_system.java.ApiConstant.ApiConstant;
import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.USER)
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(ApiConstant.SAVE)
    public ResponseEntity<String> save(@RequestBody UserEntity user) {
        UserEntity userEntity = this.userService.findByEmail(user.getEmail());
        if (userEntity != null) {
            return new ResponseEntity<>("User already exist", HttpStatus.CONFLICT);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity1 = this.userService.save(user);
        if (userEntity1 == null) {
            return new ResponseEntity<>("User not registered", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PutMapping(ApiConstant.UPDATE)
    public ResponseEntity<String> update(@RequestBody UserEntity user) {
        UserEntity userEntity = this.userService.update(user);
        if (userEntity == null) {
            return new ResponseEntity<>("User not Found", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("User Updated Successfully!!!", HttpStatus.OK);
    }

    @GetMapping(ApiConstant.ID)
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        UserEntity userEntity = this.userService.findById(id);
        if (userEntity == null) {
            return new ResponseEntity<>("User not Found", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @DeleteMapping(ApiConstant.ID)
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        boolean result = this.userService.deleteById(id);
        if (!result) {
            return new ResponseEntity<>("User not Found", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALLBYPAGINATION)
    public ResponseEntity findAllByPagination(@RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {
        List<UserEntity> userEntityList = this.userService.findAllByPagination(page, size);
        if (userEntityList.isEmpty()) {
            return new ResponseEntity<>("List not available", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALL)
    public ResponseEntity findAll() {
        List<UserEntity> userEntityList = this.userService.findAll();
        if (userEntityList.isEmpty()) {
            return new ResponseEntity<>("List not available", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }
}
