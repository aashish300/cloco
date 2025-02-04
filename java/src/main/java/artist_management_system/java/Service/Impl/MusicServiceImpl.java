package artist_management_system.java.Service.Impl;

import artist_management_system.java.Model.MusicEntity;
import artist_management_system.java.Repository.IMusicRepository;
import artist_management_system.java.Service.IMusicService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MusicServiceImpl implements IMusicService {

    private final IMusicRepository musicRepository;

    @Autowired
    public MusicServiceImpl(IMusicRepository musicRepository) {
        super();
        this.musicRepository = musicRepository;
    }

    @Override
    public MusicEntity save(MusicEntity music) {
        boolean result = musicRepository.save(music);
        if (result) {
            return music;
        }
        return null;
    }

    @Override
    public MusicEntity findById(Integer id) {
        return this.musicRepository.findById(id);
    }

    @Override
    public MusicEntity update(MusicEntity music) {
        boolean result = musicRepository.update(music);
        if (result) {
            return music;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.musicRepository.deleteById(id);
    }

    @Override
    public List<MusicEntity> findAllByPagination(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return this.musicRepository.findAllByPagination(size, offset);
    }

    @Override
    public List<MusicEntity> findAll() {
        return this.musicRepository.findAll();
    }
}
