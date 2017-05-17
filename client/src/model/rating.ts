export class Rating {

    private _uid:string;
    private _movieId:string;
    private _cinemaId:string;
    private _friends:string[];
    private _comment:string;
    private _rating:number;
    private _facebook:boolean;
    private _twitter:boolean;


    constructor(uid:string, movieId:string, cinemaId:string, friends:string[], comment:string, rating:number, facebook:boolean, twitter:boolean) {
        this._uid = uid;
        this._movieId = movieId;
        this._cinemaId = cinemaId;
        this._friends = friends;
        this._comment = comment;
        this._rating = rating;
        this._facebook = facebook;
        this._twitter = twitter;
    }

    get uid():string {
        return this._uid;
    }

    get movieId():string {
        return this._movieId;
    }

    get cinemaId():string {
        return this._cinemaId;
    }

    get friends():string[] {
        return this._friends;
    }

    get comment():string {
        return this._comment;
    }

    get rating():number {
        return this._rating;
    }

    get facebook():boolean {
        return this._facebook;
    }

    get twitter():boolean {
        return this._twitter;
    }
}
