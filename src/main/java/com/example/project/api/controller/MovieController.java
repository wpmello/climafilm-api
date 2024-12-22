package com.example.project.api.controller;

import com.example.project.api.model.themovie.BodyMovie;
import com.example.project.api.model.themovie.MovieDetail;
import com.example.project.api.model.themovie.Poster;
import com.example.project.api.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/movie")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private MovieService movieService;

    @Operation(summary = "Retorna temperatura atual dado uma cidade.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna temperatura atual com sucesso."),
            @ApiResponse(responseCode = "404", description = "Temperatura atual não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar retornar temperatura atual.")
    })
    @GetMapping("/{city}")
    public Long getTempCity(@PathVariable String city) {
        return this.movieService.getTempCity(city);
    }

    @Operation(summary = "Retorna os filmes que estão em cartaz no momento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os filmes que estão em cartaz no momento com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar retornar os filmes que estão em cartaz no momento.")
    })
    @GetMapping("/on-playing")
    public Poster getMovieOnPlayingNow() {
        return this.movieService.getMovieOnPlayingNow();
    }

    @Operation(summary = "Retorna os filmes mais populares.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os filmes mais populares com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar retornar os filmes mais populares.")
    })
    @GetMapping("/popular")
    public Poster getPopularMovies() {
        return this.movieService.getPopularMovies();
    }

    @Operation(summary = "Retorna os filmes mais votados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os filmes mais votados com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar retornar os filmes mais votados.")
    })
    @GetMapping("/top-rated")
    public Poster getTopRatedMovies() {
        return this.movieService.getTopRatedMovies();
    }

    @Operation(summary = "Retorna os filmes que vão ser lançados em breve.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os filmes que vão ser lançados em breve com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar retornar os filmes que vão ser lançados em breve.")
    })
    @GetMapping("/upcoming")
    public Poster getUpcomingMovies() {
        return this.movieService.getUpcomingMovies();
    }

    @Operation(summary = "Retorna um filme com seus detalhes dado um ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o filme escolhido e fornece seus detalhes com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar retornar o filme escolhido.")
    })
    @GetMapping("movie-detail/{id}")
    public MovieDetail getMovieDetail(@PathVariable int id) {
        return this.movieService.getMovieByIdAPI(id);
    }

    @Operation(summary = "Dado uma cidade, retorna os filmes que estão em cartaz no momento filtrados por gêneros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os filmes que estão em cartaz no momento, filtrados por gêneros, com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar retornar os filmes que estão em cartaz no momento, filtrados por gênero.")
    })
    @GetMapping("/on-playing/{city}")
    public List<BodyMovie> getMoviePerCity(@PathVariable String city) {
        return this.movieService.getMovieOnPlayingNowPerCity(city);
    }
}
