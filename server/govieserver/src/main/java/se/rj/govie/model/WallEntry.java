package se.rj.govie.model;

import java.util.Date;
import java.util.List;

public class WallEntry extends FirebaseObject {

    private final List<String> attendees;

    private final Date date;

    private final String title;

    private final String poster;

    private final String rater;

    private final String raterImg;

    private final String theater;

    private final String comment;

    private final Integer rating;

    private final String id;

    public WallEntry(String id, List<String> attendees, Date date, String title, String poster, String rater, String raterImg,
                     String theater,
                     String comment,
                     Integer
                             rating) {
        this.id = id;
        this.attendees = attendees;
        this.date = date;
        this.title = title;
        this.poster = poster;
        this.rater = rater;
        this.raterImg = raterImg;
        this.theater = theater;
        this.comment = comment;
        this.rating = rating;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getTheater() {
        return theater;
    }

    public String getComment() {
        return comment;
    }

    public String getPoster() {
        return poster;
    }

    public Integer getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public String getRater() {
        return rater;
    }

    public String getRaterImg() {
        return raterImg;
    }
}
