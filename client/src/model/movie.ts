export class Movie {

    id:string;
    posterPath:string;
    backdropPath:string;
    title:string;
    releaseDate:Date;

    constructor(id:string, posterPath:string, backdropPath:string, title:string, releaseDate:any) {
        this.id = id;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.title = title;
        this.releaseDate = releaseDate ? new Date(releaseDate.time) : new Date();
    }
}
