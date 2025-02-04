package artist_management_system.java.Contoller;

import artist_management_system.java.ApiConstant.ApiConstant;
import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Model.MusicEntity;
import artist_management_system.java.Service.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.ARTIST)
public class ArtistController {

    private final IArtistService artistService;

    @Autowired
    public ArtistController(IArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping(ApiConstant.SAVE)
    public ResponseEntity<String> save(@RequestBody ArtistEntity artist) {
        ArtistEntity artistEntity = this.artistService.save(artist);
        if (artistEntity == null) {
            return new ResponseEntity<>("Artist not registered", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Artist registered successfully!!!", HttpStatus.CREATED);
    }

    @PutMapping(ApiConstant.UPDATE)
    public ResponseEntity<String> update(@RequestBody ArtistEntity artist) {
        ArtistEntity artistEntity = this.artistService.update(artist);

        if (artistEntity == null) {
            return new ResponseEntity<>("Artist not Found", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Artist Updated Successfully!!!", HttpStatus.OK);
    }

    @GetMapping(ApiConstant.ID)
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        ArtistEntity ArtistEntity = this.artistService.findById(id);
        if (ArtistEntity == null) {
            return new ResponseEntity<>("artist not Found", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(ArtistEntity, HttpStatus.OK);
    }

    @DeleteMapping(ApiConstant.ID)
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        boolean result = this.artistService.deleteById(id);
        if (!result) {
            return new ResponseEntity<>("artist not Found", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("artist Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALLBYPAGINATION)
    public ResponseEntity findAllByPagination(@RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {
        List<ArtistEntity> ArtistEntityList = this.artistService.findAllByPagination(page, size);
        if (ArtistEntityList.isEmpty()) {
            return new ResponseEntity<>("List not available", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(ArtistEntityList, HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALL)
    public ResponseEntity findAll() {
        List<ArtistEntity> artistEntityList = this.artistService.findAll();
        if (artistEntityList.isEmpty()) {
            return new ResponseEntity<>("List not available", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(artistEntityList, HttpStatus.OK);
    }
}
