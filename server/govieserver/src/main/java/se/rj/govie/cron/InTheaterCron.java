package se.rj.govie.cron;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Movie;
import se.rj.govie.search.index.MovieIndex;
import se.rj.govie.search.repository.MovieRepository;

import java.util.List;

@Component
public class InTheaterCron {

    public static final int MAX_PAGES = 2;

    private static Logger logger = Logger.getLogger(InTheaterCron.class);

    @Autowired
    private MovieIndex movieIndex;

    @Autowired
    private MovieRepository movieRepository;

    @Scheduled(initialDelay = 1000, fixedRate = 1000 * 360 * 24)
    void addInTheatersToIndex() {
        List<Movie> inCinemas = movieRepository.listInCinemas(MAX_PAGES);
        inCinemas.stream().forEach(movieIndex::add);
        logger.info("Added movies to index");
    }

    @Scheduled(initialDelay = 1000 * 360 * 24*7, fixedRate = 1000 * 360 * 24*7)
    void clearInTheatersIndex() {
        movieIndex.clear();
        logger.info("Added movies to index");
    }
}
