package com.demo.artist_management_system.Model;

import com.demo.artist_management_system.Model.BaseEntity.BaseEntity;
import com.demo.artist_management_system.Utils.Enum.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_music")
public class Music extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "genre")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
