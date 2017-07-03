package se.rj.govie.wall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Movie;
import se.rj.govie.model.User;
import se.rj.govie.model.WallEntry;
import se.rj.govie.model.builder.WallEntryBuilder;
import se.rj.govie.request.RateRequest;
import se.rj.govie.search.index.CinemaIndex;
import se.rj.govie.search.index.MovieIndex;
import se.rj.govie.search.index.UserIndex;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class WallEntryFactory {

    @Autowired
    private CinemaIndex cinemaIndex;

    @Autowired
    private MovieIndex movieIndex;

    @Autowired
    private UserIndex userIndex;

    public WallEntry fromRateRequest(RateRequest rateRequest) {
        List<User> attendees = userIndex.searchByIds(rateRequest.getFriends());
        User user = userIndex.searchByIds(Collections.singletonList(rateRequest.getUid())).get(0);
        Movie movie = movieIndex.findById(rateRequest.getMovieId());
        WallEntryBuilder wallEntryBuilder = new WallEntryBuilder();
        wallEntryBuilder.setId(UUID.randomUUID().toString())
                        .setAttendees(attendees.stream().map(User::getName).collect(Collectors.toList()))
                        .setDate(new Date())
                        .setTitle(movie.getTitle())
                        .setPoster(movie.getPosterPath())
                        .setRater(user.getName())
                        .setRaterImg(user.getAvatarUrl())
                        .setTheater(cinemaIndex.findById(rateRequest.getCinemaId()).getName())
                        .setComment(rateRequest.getComment())
                        .setRating(rateRequest.getRating());
        return wallEntryBuilder.build();
    }
}
