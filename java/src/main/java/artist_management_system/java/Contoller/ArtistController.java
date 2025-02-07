package artist_management_system.java.Contoller;

import artist_management_system.java.ApiConstant.ApiConstant;
import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Service.IArtistService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiConstant.ARTIST)
public class ArtistController {

    private final IArtistService artistService;

    @Autowired
    public ArtistController(IArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping(ApiConstant.SAVE)
    @PreAuthorize("hasAnyAuthority('super_admin', 'artist_manager')")
    public ResponseEntity<Map<String ,Object>> save(@RequestBody ArtistEntity artist) {
        ArtistEntity artistEntity = this.artistService.save(artist);
        if (artistEntity == null) {
            return new ResponseEntity<>(Map.of("message","Artist not registered"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("message","Artist registered successfully!!!"), HttpStatus.CREATED);
    }

    @PutMapping(ApiConstant.UPDATE)
    @PreAuthorize("hasAnyAuthority('super_admin', 'artist_manager')")
    public ResponseEntity<Map<String, Object>> update(@RequestBody ArtistEntity artist) {
        ArtistEntity artistEntity = this.artistService.update(artist);

        if (artistEntity == null) {
            return new ResponseEntity<>(Map.of("message","Artist not Found"), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(Map.of("message","Artist Updated Successfully!!!"), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.ID)
    @PreAuthorize("hasAnyAuthority('super_admin', 'artist_manager', 'artist')")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Integer id) {
        ArtistEntity ArtistEntity = this.artistService.findById(id);
        if (ArtistEntity == null) {
            return new ResponseEntity<>(Map.of("message","artist not SFound"), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(Map.of("message", "Successfully fetched!!!", "data", ArtistEntity), HttpStatus.OK);
    }

    @DeleteMapping(ApiConstant.ID)
    @PreAuthorize("hasAnyAuthority('super_admin', 'artist_manager')")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable("id") Integer id) {
        boolean result = this.artistService.deleteById(id);
        if (!result) {
            return new ResponseEntity<>(Map.of("message","artist not Found"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("message","artist Deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALLBYPAGINATION)
    @PreAuthorize("hasAnyAuthority('super_admin', 'artist_manager')")
    public ResponseEntity<Map<String, Object>> findAllByPagination(@RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {
        List<ArtistEntity> ArtistEntityList = this.artistService.findAllByPagination(page, size);
        int count = this.artistService.findAll().size();
        if (ArtistEntityList.isEmpty()) {
            return new ResponseEntity<>(Map.of("message","List not available"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("message","Successfully Fetched!!!", "list", ArtistEntityList, "totalCount", count), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FINDALL)
    @PreAuthorize("hasAnyAuthority('super_admin', 'artist_manager')")
    public ResponseEntity<Map<String, Object>> findAll() {
        List<ArtistEntity> artistEntityList = this.artistService.findAll();
        if (artistEntityList.isEmpty()) {
            return new ResponseEntity<>(Map.of("message","List not available"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("message","Successfully Fetched!!!", "list", artistEntityList), HttpStatus.OK);

    }

    @PostMapping(ApiConstant.CSV_UPLOAD)
    @PreAuthorize("hasAnyAuthority('super_admin', 'artist_manager')")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestBody List<ArtistEntity> artistEntityList) {
        try {
            List<ArtistEntity> result = this.artistService.saveAll(artistEntityList);
            if (result != null && !result.isEmpty()) {
                return new ResponseEntity<>(Map.of("message","The file is uploaded successfully!!!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(Map.of("message","Please upload an csv file!!!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message","The file is not upload successfully!!!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(ApiConstant.CSV_EXPORT)
    @PreAuthorize("hasAnyAuthority('super_admin', 'artist_manager')")
    public ResponseEntity<Map<String, Object>> exportFile(HttpServletResponse response) {
        try {
            this.artistService.exportCSV(response);
            return new ResponseEntity<>(Map.of("message","The file is exported successfully!!!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message","The file not exported!!!"), HttpStatus.BAD_REQUEST);
        }
    }
}
