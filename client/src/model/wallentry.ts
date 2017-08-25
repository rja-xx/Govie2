export class WallEntry {

    private id:string;
    private name:string;
    private avatarUrl:string;
    private timestamp:Date;
    private title:string;
    private info:string;
    private attendees:string[];
    private date:Date;
    private poster:string;
    private rater:string;
    private raterImg:string;
    private theater:string;
    private comment:string;
    private rating:number;


    constructor(id:string, name:string, avatarUrl:string, timestamp:any, title:string, info:string, attendees:string[], date:any, poster:string, rater:string, raterImg:string, theater:string, comment:string, rating:number) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.timestamp = timestamp ? new Date(timestamp.time) : new Date();
        this.title = title;
        this.info = info;
        this.attendees = attendees;
        this.date = date ? new Date(date.time) : new Date();
        this.poster = poster;
        this.rater = rater;
        this.raterImg = raterImg;
        this.theater = theater;
        this.comment = comment;
        this.rating = rating;
    }
}
