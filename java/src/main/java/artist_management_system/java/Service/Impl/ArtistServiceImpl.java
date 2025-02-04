package artist_management_system.java.Service.Impl;

import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Repository.IArtistRepository;
import artist_management_system.java.Service.IArtistService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ArtistServiceImpl implements IArtistService {

    private final IArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public ArtistEntity save(ArtistEntity artist) {
        boolean result = artistRepository.save(artist);
        if (result) {
            return artist;
        }
        return null;
    }

    @Override
    public ArtistEntity findById(Integer id) {
        return this.artistRepository.findById(id);
    }

    @Override
    public ArtistEntity update(ArtistEntity artist) {
        boolean result = artistRepository.update(artist);
        if (result) {
            return artist;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.artistRepository.deleteById(id);
    }

    @Override
    public List<ArtistEntity> findAllByPagination(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return this.artistRepository.findAllByPagination(size, offset);
    }

    @Override
    public List<ArtistEntity> findAll() {
        return this.artistRepository.findAll();
    }
}
