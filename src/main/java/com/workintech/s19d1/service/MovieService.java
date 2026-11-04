package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie save(Movie movie);
    void delete(Movie movie);
}
