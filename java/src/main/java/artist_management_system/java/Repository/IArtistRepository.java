package artist_management_system.java.Repository;

import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Repository.BaseRepository.IBaseRepository;

import java.util.List;

public interface IArtistRepository extends IBaseRepository<ArtistEntity> {

    boolean save(ArtistEntity artist);

    ArtistEntity findById(Integer id);

    boolean update(ArtistEntity artist);

    boolean deleteById(Integer id);

    List<ArtistEntity> findAllByPagination(int limit, int offset);

    List<ArtistEntity> findAll();
}
