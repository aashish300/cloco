package com.example.demo.Model.BaseEntity;


import com.example.demo.Utils.CustomEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(CustomEntityListener.class)
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @NotNull
    @Column(name = "status")
    private String status = DBConstantsEnum.ACTIVE.getStatus();
}
