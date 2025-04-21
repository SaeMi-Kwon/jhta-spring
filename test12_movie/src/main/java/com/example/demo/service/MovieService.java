package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository mr;

    //저장
    public MovieDto insert(MovieDto dto){
        Movie movie = mr.save(dto.toEntity());
        return new MovieDto(movie);
    }

    //영화번호로 조회
    public MovieDto getMovieId(Long mnum){
        Optional<Movie> movie = mr.findById(mnum);
        if(movie.isPresent()){
            Movie m = movie.get();
            return new MovieDto(m);
        }
        return null;
    }

    //전체 조회(pageing없이)
    public List<MovieDto> MovieList(){
        List<Movie> list = mr.findAll();

        //list.stream().map(movie -> new MovieDto(movie))  ==> Stream<MovieDto>
        return list.stream().map(movie -> new MovieDto(movie)).toList();
    }


}
