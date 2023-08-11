package com.example.project.api.controller;

import com.example.project.api.model.dto.BodyMovieDTO;
import com.example.project.api.model.dto.PosterDTO;
import com.example.project.api.model.themovie.BodyMovie;
import com.example.project.api.service.MovieService;
import com.example.project.api.service.exceptions.MovieNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("app/movie")
@CrossOrigin(maxAge = 3600)
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
    public PosterDTO getMovieOnPlayingNow() {
        return this.movieService.getMovieOnPlayingNow();
    }

    @Operation(summary = "Dado uma cidade, retorna os filmes que estão em cartaz no momento filtrados por gêneros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os filmes que estão em cartaz no momento, filtrados por gêneros, com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar retornar os filmes que estão em cartaz no momento, filtrados por gênero.")
    })
    @GetMapping("/on-playing/{city}")
    public List<BodyMovieDTO> getMoviePerCity(@PathVariable String city) {
        return this.movieService.getMovieOnPlayingNowPerCity(city);
    }

    @GetMapping("/db")
    public List<BodyMovieDTO> getAllMoviesOnDatabase() {
        return this.movieService.getMovies();
    }

    @GetMapping("/db/{id}")
    public BodyMovie getAllMoviesOnDatabase(@PathVariable Integer id) throws MovieNotFoundException {
        return this.movieService.getMovieById(id);
    }

    @PostMapping("/db")
    @ResponseStatus(HttpStatus.CREATED)
    public BodyMovieDTO AddMovie(@RequestBody @Valid BodyMovie movie) {
        return this.movieService.save(movie);
    }

    @PutMapping("/db/{id}")
    public BodyMovieDTO updateMovie(@PathVariable int id, @RequestBody BodyMovieDTO movie) throws MovieNotFoundException {
        return this.movieService.update(id, movie);
    }

    @DeleteMapping("/db/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) throws MovieNotFoundException {
        this.movieService.delete(id);
    }
}
