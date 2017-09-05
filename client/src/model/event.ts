export class Event {

    name:string;
    avatarUrl:string;
    timestamp:Date;
    title:string;
    info:string;

    constructor(name:string, avatarUrl:string, timestamp:any, title:string, info:string) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.timestamp = timestamp ? new Date(timestamp.time) : new Date();
        this.title = title;
        this.info = info;
    }


}
