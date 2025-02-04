package artist_management_system.java.Repository;

import artist_management_system.java.Model.MusicEntity;
import artist_management_system.java.Repository.BaseRepository.IBaseRepository;

import java.util.List;

public interface IMusicRepository extends IBaseRepository<MusicEntity> {

    boolean save(MusicEntity music);

    MusicEntity findById(Integer id);

    boolean update(MusicEntity music);

    boolean deleteById(Integer id);

    List<MusicEntity> findAllByPagination(int limit, int offset);

    List<MusicEntity> findAll();
}
