package artist_management_system.java.Model;

import artist_management_system.java.Model.BaseEntity.BaseEntity;
import artist_management_system.java.Utils.Enum.Gender;
import jakarta.persistence.*;

import java.time.Year;
import java.util.Date;

@Entity
@Table(name = "tbl_artist")
public class ArtistEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private Date dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "gender_enum")
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "first_release_year")
    private Year firstReleaseYear;

    @Column(name = "no_of_albums_released")
    private Integer noOfAlbumsReleased;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Year getFirstReleaseYear() {
        return firstReleaseYear;
    }

    public void setFirstReleaseYear(Year firstReleaseYear) {
        this.firstReleaseYear = firstReleaseYear;
    }

    public Integer getNoOfAlbumsReleased() {
        return noOfAlbumsReleased;
    }

    public void setNoOfAlbumsReleased(Integer noOfAlbumsReleased) {
        this.noOfAlbumsReleased = noOfAlbumsReleased;
    }
}
