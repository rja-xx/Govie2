package se.rj.govie.cron;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Movie;
import se.rj.govie.search.index.MovieIndex;
import se.rj.govie.search.repository.MovieRepository;

import java.util.List;

import static java.util.stream.IntStream.rangeClosed;

@Component
public class InTheaterCron {

    private static final int MAX_PAGES = 2;

    private static final int DAY_IN_MILLIS = 1000 * 360 * 24;

    private static final int WEEK_IN_MILLIS = 1000 * 360 * 24 * 7;

    private static final int SECOND_IN_MILLIS = 1000;

    private static Logger logger = Logger.getLogger(InTheaterCron.class);

    @Autowired
    private MovieIndex movieIndex;

    @Autowired
    private MovieRepository movieRepository;

    @Scheduled(initialDelay = SECOND_IN_MILLIS, fixedRate = DAY_IN_MILLIS)
    void addInTheatersToIndex() {
        rangeClosed(1, MAX_PAGES).forEach(page -> {
            List<Movie> inCinemas = movieRepository.listInCinemas(page);
            inCinemas.forEach(movieIndex::add);
            logger.info("Added movies " + inCinemas.size() + "to index");
            sleep(1000);
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error("Sleeping between fetching movies in theaters failed", e);
            throw new RuntimeException(e);
        }
    }

    @Scheduled(initialDelay = WEEK_IN_MILLIS, fixedRate = WEEK_IN_MILLIS)
    void clearInTheatersIndex() {
        movieIndex.clear();
        logger.info("Added movies to index");
    }
}
