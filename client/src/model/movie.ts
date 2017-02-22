export class Movie {

    tmdbid:string;
    posterPath:string;
    backdropPath:string;
    name:string;

    constructor(tmdbid:string, posterPath:string, backdropPath:string, name:string) {
        this.tmdbid = tmdbid;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.name = name;
    }
}
