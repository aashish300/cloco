package artist_management_system.java.Contoller;

import artist_management_system.java.ApiConstant.ApiConstant;
import artist_management_system.java.Model.MusicEntity;
import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.MUSIC)
public class MusicController {

    private final IMusicService musicService;

    @Autowired
    public MusicController(IMusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping(ApiConstant.SAVE)
    public ResponseEntity<String> save(@RequestBody MusicEntity music) {
        MusicEntity musicEntity = this.musicService.save(music);
        if (musicEntity == null) {
            return new ResponseEntity<>("Music not registered", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Music registered successfully", HttpStatus.CREATED);
    }

    @PutMapping(ApiConstant.UPDATE)
    public ResponseEntity<String> update(@RequestBody MusicEntity music) {
        MusicEntity musicEntity = this.musicService.update(music);

        if (musicEntity == null) {
            return new ResponseEntity<>("Music not Found", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Music Updated Successfully!!!", HttpStatus.OK);
    }

    @GetMapping(ApiConstant.ID)
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        MusicEntity MusicEntity = this.musicService.findById(id);
        if (MusicEntity == null) {
            return new ResponseEntity<>("artist not Found", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(MusicEntity, HttpStatus.OK);
    }

    @DeleteMapping(ApiConstant.ID)
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        boolean result = this.musicService.deleteById(id);
        if (!result) {
            return new ResponseEntity<>("artist not Found", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("artist Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALLBYPAGINATION)
    public ResponseEntity findAllByPagination(@RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {
        List<MusicEntity> MusicEntityList = this.musicService.findAllByPagination(page, size);
        if (MusicEntityList.isEmpty()) {
            return new ResponseEntity<>("List not available", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(MusicEntityList, HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALL)
    public ResponseEntity findAll() {
        List<MusicEntity> MusicEntityList = this.musicService.findAll();
        if (MusicEntityList.isEmpty()) {
            return new ResponseEntity<>("List not available", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(MusicEntityList, HttpStatus.OK);
    }
}
