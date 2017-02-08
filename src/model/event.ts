import { User} from './user';

export class Event {

  user:User;
  timestamp:Date;
  title:string;
  info:string;

  constructor(user:User, timestamp:Date, title:string, info:string) {
    this.user = user;
    this.timestamp = timestamp;
    this.title = title;
    this.info = info;
  }


}
