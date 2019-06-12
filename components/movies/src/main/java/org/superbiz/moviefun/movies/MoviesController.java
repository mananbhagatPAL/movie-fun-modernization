package org.superbiz.moviefun.movies;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private MoviesRepository moviesRepository;

    public  MoviesController(MoviesRepository repository) {
        this.moviesRepository = repository;
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        moviesRepository.addMovie(movie);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovieId(@PathVariable Long movieId) {
        moviesRepository.deleteMovieId(movieId);
    }

    @GetMapping
    public List<Movie> find(@RequestParam(name = "field", required = false) String field,
                            @RequestParam(name = "key", required = false) String key,
                            @RequestParam(name = "start", required = false) Integer start,
                            @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (field != null && key != null) {
            return moviesRepository.findRange(field, key, start, pageSize);
        } else if (start != null && pageSize != null) {
            return moviesRepository.findAll(start, pageSize);
        }
        return moviesRepository.getMovies();
    }

    @GetMapping("/count")
    public int count(@RequestParam(name = "field", required = false) String field,
                     @RequestParam(name = "key", required = false) String key) {
        if (field != null && key != null) {
            return moviesRepository.count(field, key);

        }
        return moviesRepository.countAll();
    }
}
