package com.demo.artist_management_system.Model;

import com.demo.artist_management_system.Model.BaseEntity.BaseEntity;
import com.demo.artist_management_system.Utils.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tbl_artist")
public class Artist extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "first_release_year")
    private Year firstReleaseYear;

    @Column(name = "no_of_albums_released")
    private Integer noOfAlbumsReleased;
}
