package com.demo.artist_management_system.Repository.BaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IBaseRepository<T, ID extends Serializable>{

    <S extends T> S save(S entity);

    <S extends T> S update (S entity);

    T findById(ID id);

    Page<T> findAll(Pageable pageable);

    boolean deleteById(ID id);
}
