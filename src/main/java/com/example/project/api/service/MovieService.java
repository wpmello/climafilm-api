package com.example.project.api.service;

import com.example.project.api.model.dto.BodyMovieDTO;
import com.example.project.api.model.dto.PosterDTO;
import com.example.project.api.model.dto.mapper.BodyMovieMapper;
import com.example.project.api.model.dto.mapper.PosterMapper;
import com.example.project.api.model.themovie.BodyMovie;
import com.example.project.api.model.themovie.Poster;
import com.example.project.api.model.weather.BodyWeather;
import com.example.project.api.repository.MovieRepository;
import com.example.project.api.service.exceptions.MovieNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {
    private final RestTemplate restTemplate;
    private MovieRepository movieRepository;
    private final BodyMovieMapper bodyMovieMapper = BodyMovieMapper.INSTANCE;
    private final PosterMapper posterMapper = PosterMapper.INSTANCE;

    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getUrlMovie() {
        String apiUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
        String apiKey = "43690bf9a399137442f8bb73b262f447";
        String language = "pt-BR";

        return apiUrl +
                apiKey +
                "&language=" +
                language;
    }

    private String getUrlWeather(String city) {
        String apiKey = "bb85471d2221957c640336916cec2bf7";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
        String units = "metric";

        return apiUrl +
                city +
                "&appid=" +
                apiKey +
                "&units=" +
                units;
    }

    private BodyMovie verifyIfExits(int id) throws MovieNotFoundException {
        return this.movieRepository
                .findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    private List<BodyMovieDTO> getMoviesByTemperatureAndGenre(Long temp) {
        List<BodyMovie> results = restTemplate
                .getForEntity(getUrlMovie(), Poster.class)
                .getBody()
                .getResults();

        Predicate<BodyMovie> genreFilter;

        if (temp > 40) {
            genreFilter = movie -> movie.getGenre_ids().contains(28);
        } else if (temp >= 36) {
            genreFilter = movie -> movie.getGenre_ids().contains(35);
        } else if (temp >= 20) {
            genreFilter = movie -> movie.getGenre_ids().contains(16);
        } else if (temp >= 0) {
            genreFilter = movie -> movie.getGenre_ids().contains(53);
        } else {
            genreFilter = movie -> movie.getGenre_ids().contains(99);
        }

        List<BodyMovieDTO> filteredMovies = results.stream()
                .filter(genreFilter)
                .map(bodyMovieMapper::toDTO)
                .collect(Collectors.toList());

        log.info("The temp returned was {}", temp);
        return filteredMovies;
    }

    public Long getTempCity(String city) {
        return restTemplate
                .exchange(getUrlWeather(city),
                        HttpMethod.GET,
                        null,
                        BodyWeather.class)
                .getBody()
                .getMain()
                .getTemp();
    }

    public PosterDTO getMovieOnPlayingNow() {
        Poster poster = restTemplate.getForObject(getUrlMovie(), Poster.class);
        return posterMapper.toDTO(poster);
    }

    public List<BodyMovieDTO> getMovieOnPlayingNowPerCity(String city) {
        Long temp = restTemplate
                .getForObject(getUrlWeather(city), BodyWeather.class)
                .getMain()
                .getTemp();
        return getMoviesByTemperatureAndGenre(temp);
    }

    public List<BodyMovieDTO> getMovies() {
        List<BodyMovie> bodyMovies = this.movieRepository.findAll();
        return bodyMovies.stream().map(bodyMovieMapper::toDTO).collect(Collectors.toList());
    }

    public BodyMovie getMovieById(int id) throws MovieNotFoundException {
        return verifyIfExits(id);
    }

    public BodyMovieDTO save(BodyMovie movie) {
        BodyMovie savedMovie = this.movieRepository.save(movie);
        return bodyMovieMapper.toDTO(savedMovie);
    }

    public BodyMovieDTO update(int id, BodyMovieDTO movie) throws MovieNotFoundException {
        BodyMovie bodyMovie = verifyIfExits(id);
        bodyMovie.setTitle(movie.title());
        BodyMovie savedMovie = this.movieRepository.save(bodyMovie);

        return bodyMovieMapper.toDTO(savedMovie);
    }

    public void delete(int id) throws MovieNotFoundException {
        verifyIfExits(id);
        this.movieRepository.deleteById(id);
    }
}
