package artist_management_system.java.Repository.BaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;


//serializable converts an object into a byte stream so it can be persisted to a file and sent over a network or stored in a database
public interface IBaseRepository<T, ID extends Serializable> {

    <S extends T> S save(S entity);

    <S extends T> S update(S entity);

    T findById(ID id);

    Page<T> findAll(Pageable pageable);

    boolean deleteById(ID id);
}
