import { User} from './user';

export class Cinema {

    private _reference:string;
    private _address:string;
    private _name:string;


    constructor(reference:string, address:string, name:string) {
        this._reference = reference;
        this._address = address;
        this._name = name;
    }


    get reference():string {
        return this._reference;
    }

    get address():string {
        return this._address;
    }

    get name():string {
        return this._name;
    }
}
