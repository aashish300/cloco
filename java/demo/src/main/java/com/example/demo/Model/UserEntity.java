package com.example.demo.Model;

import com.example.demo.Model.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_user")
public class UserEntity extends BaseEntity {

    @Column(name = "mark")
    private long mark;

}
