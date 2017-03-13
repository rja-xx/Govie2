export class Profile {

    private _uid:string;
    private _avatarUrl:string;
    private _backdropUrl:string;
    private _name:string;
    private _handle:string;
    private _followers:number;
    private _follows:number;
    private _rates:number;
    private _cinemas:number;


    constructor(uid:string, avatarUrl:string, backdropUrl:string, name:string, handle:string, followers:number, follows:number, rates:number, cinemas:number) {
        this._uid = uid;
        this._avatarUrl = avatarUrl;
        this._backdropUrl = backdropUrl;
        this._name = name;
        this._handle = handle;
        this._followers = followers;
        this._follows = follows;
        this._rates = rates;
        this._cinemas = cinemas;
    }


    get uid():string {
        return this._uid;
    }

    set uid(value:string) {
        this._uid = value;
    }

    get avatarUrl():string {
        return this._avatarUrl;
    }

    set avatarUrl(value:string) {
        this._avatarUrl = value;
    }

    get backdropUrl():string {
        return this._backdropUrl;
    }

    set backdropUrl(value:string) {
        this._backdropUrl = value;
    }

    get name():string {
        return this._name;
    }

    set name(value:string) {
        this._name = value;
    }

    get handle():string {
        return this._handle;
    }

    set handle(value:string) {
        this._handle = value;
    }

    get followers():number {
        return this._followers;
    }

    set followers(value:number) {
        this._followers = value;
    }

    get follows():number {
        return this._follows;
    }

    set follows(value:number) {
        this._follows = value;
    }

    get rates():number {
        return this._rates;
    }

    set rates(value:number) {
        this._rates = value;
    }

    get cinemas():number {
        return this._cinemas;
    }

    set cinemas(value:number) {
        this._cinemas = value;
    }
}
