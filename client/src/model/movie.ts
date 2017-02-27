export class Movie {

    id:string;
    posterPath:string;
    backdropPath:string;
    title:string;
    releaseDate:Date;

    constructor(id:string, posterPath:string, backdropPath:string, title:string, releaseDate:Date) {
        this.id = id;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
