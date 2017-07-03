package se.rj.govie.model.builder;

import se.rj.govie.model.WallEntry;

import java.util.Date;
import java.util.List;

public class WallEntryBuilder {
    private String id;

    private List<String> attendees;

    private Date date;

    private String title;

    private String poster;

    private String rater;

    private String raterImg;

    private String theater;

    private String comment;

    private Integer rating;

    public WallEntryBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public WallEntryBuilder setAttendees(List<String> attendees) {
        this.attendees = attendees;
        return this;
    }

    public WallEntryBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public WallEntryBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public WallEntryBuilder setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public WallEntryBuilder setRater(String rater) {
        this.rater = rater;
        return this;
    }

    public WallEntryBuilder setRaterImg(String raterImg) {
        this.raterImg = raterImg;
        return this;
    }

    public WallEntryBuilder setTheater(String theater) {
        this.theater = theater;
        return this;
    }

    public WallEntryBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public WallEntryBuilder setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public WallEntry build() {
        return new WallEntry(id, attendees, date, title, poster, rater, raterImg, theater, comment, rating);
    }
}