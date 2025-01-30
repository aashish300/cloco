package artist_management_system.java.Repository.BaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.util.List;


//serializable converts an object into a byte stream so it can be persisted to a file and sent over a network or stored in a database
public interface IBaseRepository<T> {

    List<T> query(String sql, Object[] args, RowMapper<T> rowMapper);

    int update(String sql, Object[] args);
}
