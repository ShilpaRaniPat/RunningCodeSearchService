import {Injectable} from "@angular/core";

var SockJs = require("sockjs-client");
var Stomp = require("stompjs");


@Injectable()
export class WebSocketService {

    // Open connection with the back-end socket
    public connect() {
        let socket = new SockJs(`http://52.66.134.21:8010/socket`);

        let stompClient = Stomp.over(socket);

        return stompClient;
    }
}
