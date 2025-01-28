package artist_management_system.java.Model;

import artist_management_system.java.Model.BaseEntity.BaseEntity;
import artist_management_system.java.Utils.Enum.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class UserEntity extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private Role role;

    @Column(name = "address")
    private String address;

    @Column(name = "token")
    private String token;
}
