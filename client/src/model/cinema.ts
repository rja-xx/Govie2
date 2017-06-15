import { User} from './user';

export class Cinema {

    private _id:string;
    private _address:string;
    private _name:string;


    constructor(id:string, address:string, name:string) {
        this._id = id;
        this._address = address;
        this._name = name;
    }


    get id():string {
        return this._id;
    }

    get address():string {
        return this._address;
    }

    get name():string {
        return this._name;
    }
}
