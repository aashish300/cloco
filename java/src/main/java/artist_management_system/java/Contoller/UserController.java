package artist_management_system.java.Contoller;

import artist_management_system.java.ApiConstant.ApiConstant;
import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiConstant.USER)
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(ApiConstant.SAVE)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<Map<String, Object>> save(@RequestBody UserEntity user) {
        UserEntity userEntity = this.userService.findByEmail(user.getEmail(), false);
        if (userEntity != null) {
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

    @PutMapping(ApiConstant.UPDATE)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<Map<String , Object>> update(@RequestBody UserEntity user) {
        UserEntity userEntity = this.userService.findByEmail(user.getEmail(), false);
        if (userEntity != null) {
            return new ResponseEntity<>(Map.of("message","User already exist"), HttpStatus.CONFLICT);
        }

        UserEntity userEntity1 = this.userService.update(user);
        if (userEntity1 == null) {
            return new ResponseEntity<>(Map.of("message","User not Found"), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(Map.of("message","User Updated Successfully!!!"), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.ID)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<Map<String , Object>> findById(@PathVariable("id") Integer id) {
        UserEntity userEntity = this.userService.findById(id);
        if (userEntity == null) {
            return new ResponseEntity<>(Map.of("message","User not Found"), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(Map.of("data",userEntity), HttpStatus.OK);
    }

    @DeleteMapping(ApiConstant.ID)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<Map<String , Object>> deleteById(@PathVariable("id") Integer id) {
        boolean result = this.userService.deleteById(id);
        if (!result) {
            return new ResponseEntity<>(Map.of("message","User not Found"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("message","User Deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALLBYPAGINATION)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<Map<String , Object>> findAllByPagination(@RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {
        List<UserEntity> userEntityList = this.userService.findAllByPagination(page, size);
        if (userEntityList.isEmpty()) {
            return new ResponseEntity<>(Map.of("message","List not available"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("data",userEntityList), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALL)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<Map<String , Object>> findAll() {
        List<UserEntity> userEntityList = this.userService.findAll();
        if (userEntityList.isEmpty()) {
            return new ResponseEntity<>(Map.of("message","List not available"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("data",userEntityList), HttpStatus.OK);
    }
}
