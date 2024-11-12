package com.boiteachaussette.msg;


import java.net.InetAddress;


public class MsgRcv{
    private final Msg msg;
    private final InetAddress ip;
    private final int port;
    

    public MsgRcv(Msg msg, InetAddress ip, int port){
        this.msg = msg;
        this.ip = ip;
        this.port = port;
    }


    public Msg getMsg() {
        return this.msg;
    }
    public int getPort(){
        return this.port;
    }
    public InetAddress getIp(){
        return this.ip;
    }
}
