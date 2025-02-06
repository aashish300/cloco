package artist_management_system.java.Contoller;

import artist_management_system.java.ApiConstant.ApiConstant;
import artist_management_system.java.Model.MusicEntity;
import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiConstant.MUSIC)
public class MusicController {

    private final IMusicService musicService;

    @Autowired
    public MusicController(IMusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping(ApiConstant.SAVE)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<String> save(@RequestBody MusicEntity music) {
        MusicEntity musicEntity = this.musicService.save(music);
        if (musicEntity == null) {
            return new ResponseEntity<>("Music not registered", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Music registered successfully", HttpStatus.CREATED);
    }

    @PutMapping(ApiConstant.UPDATE)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<String> update(@RequestBody MusicEntity music) {
        MusicEntity musicEntity = this.musicService.update(music);

        if (musicEntity == null) {
            return new ResponseEntity<>("Music not Found", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Music Updated Successfully!!!", HttpStatus.OK);
    }

    @GetMapping(ApiConstant.ID)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Integer id) {
        MusicEntity musicEntity = this.musicService.findById(id);
        if (musicEntity == null) {
            return new ResponseEntity<>(Map.of("message","artist not Found"), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(Map.of("data",musicEntity), HttpStatus.OK);
    }

    @DeleteMapping(ApiConstant.ID)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        boolean result = this.musicService.deleteById(id);
        if (!result) {
            return new ResponseEntity<>("artist not Found", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("artist Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALLBYPAGINATION)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<Map<String, Object>> findAllByPagination(@RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {
        List<MusicEntity> musicEntityList = this.musicService.findAllByPagination(page, size);
        if (musicEntityList.isEmpty()) {
            return new ResponseEntity<>(Map.of("message","List not available"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("data", musicEntityList), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALL)
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<Map<String, Object>> findAll() {
        List<MusicEntity> musicEntityList = this.musicService.findAll();
        if (musicEntityList.isEmpty()) {
            return new ResponseEntity<>(Map.of("message","List not available"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("data",musicEntityList), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.MUSICBYARTISTID)
    public ResponseEntity<Map<String , Object>> findMusicByArtistId(@PathVariable("id") Integer artistId) {
        List<MusicEntity> musicEntityList = this.musicService.findMusicByArtistId(artistId);
        if (musicEntityList.isEmpty()) {
            return new ResponseEntity<>(Map.of("message","List not available"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("data",musicEntityList), HttpStatus.OK);
    }
}
