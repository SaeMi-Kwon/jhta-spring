package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("movie/detail")
    public String detail(@RequestParam("mnum")long mnum, Model model){
        MovieDto dto=movieService.getMovieId(mnum);
        model.addAttribute("movie",dto);
        return "movie/detail";
        //return "movie/detail2";
    }


}
