package com.boiteachaussette.net;

import com.boiteachaussette.msg.MsgRcv;
import com.boiteachaussette.msg.Msg;


import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import com.boiteachaussette.msg.StackMsg;
import java.text.ParseException;

public class ChaussetteClient extends Chaussette{
    private final int portSrv;
    private final String id, mdp;
    private final InetAddress ipSrv;
    
    public ChaussetteClient(String id, String mdp, String ipSrv, int portSrv) throws UnknownHostException, SocketException, IOException{
        super();
        this.id = id;
        this.mdp = mdp;
        this.ipSrv = InetAddress.getByName(ipSrv);
        this.portSrv = portSrv;
    }


    public StackMsg update(int code) throws UnknownHostException, SocketException, IOException, ParseException {
        MsgRcv msg;
        StackMsg lstMsg = null;
        super.send(new Msg(this.mdp,this.id, code), ipSrv, portSrv);
        do {
            msg = super.listen();
            lstMsg = new StackMsg(msg.getMsg(),lstMsg);
        } while ("FORWARD".equals(msg.getMsg().getType()));
        return lstMsg.reverse();
    }

    public void sendMessage(String to, String content) throws UnknownHostException, SocketException, IOException {
        Msg msg = new Msg(this.mdp,this.id, to, this.getCurrentTime(), content);
        super.send(msg, ipSrv, portSrv);
    }
}