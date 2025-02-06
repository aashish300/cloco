package artist_management_system.java.Service;

import artist_management_system.java.Model.MusicEntity;

import java.util.List;

public interface IMusicService {

    MusicEntity save(MusicEntity music);

    MusicEntity findById(Integer id);

    MusicEntity update(MusicEntity music);

    boolean deleteById(Integer id);

    List<MusicEntity> findAllByPagination(Integer page, Integer size);

    List<MusicEntity> findAll();

    List<MusicEntity> findMusicByArtistId(Integer artistId);

}
