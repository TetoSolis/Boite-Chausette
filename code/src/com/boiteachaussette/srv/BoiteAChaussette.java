package com.boiteachaussette.srv;

import com.boiteachaussette.net.Chaussette;
import com.boiteachaussette.msg.MsgRcv;
import com.boiteachaussette.msg.Msg;
import com.boiteachaussette.msg.StackMsg;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.Objects;


public class BoiteAChaussette {
    
    Session[] sessions = new Session[15];
    Chaussette server;

    public BoiteAChaussette(int portListen) throws UnknownHostException, SocketException, IOException{
        server = new Chaussette(portListen);
    }
    
    public void listen() throws UnknownHostException, SocketException, IOException, ParseException{
        while (true){
            treat(server.listen());
        }
    }
    
    
    public void treat(MsgRcv msgrcv)throws UnknownHostException, SocketException, IOException{
        switch(msgrcv.getMsg().getType()){
            case "UPDATE" -> update(msgrcv);
            case "SEND" -> registerMsg(msgrcv);
        }
    }
    
    public void update(MsgRcv msgRcv)throws UnknownHostException, SocketException, IOException{
        Msg msg = msgRcv.getMsg();
        Session session=this.getSessionById(msg.getId());

        if (session!=null){
            if (msg.getMdp().equals(session.getMdp())){
                session.renew(msgRcv.getIp(), msgRcv.getPort());
                this.forwarding(session, msg.getCode());
            } else {
                this.notifing(1, msgRcv.getIp(), msgRcv.getPort());
            }
        } else {
            this.addSession(new Session(msg.getId(), msg.getMdp(), msgRcv.getIp(), msgRcv.getPort()));
            this.notifing( 0, this.getSessionById(msg.getId()));
        }
     }

    public void addSession(Session session){
        sessions[this.getEmptySessionIndex()] = session;
    }
    
    public int getEmptySessionIndex(){
        int i = 0;
        while (sessions[i]!=null){
            i=i+1;
        }
        return i;
    }
    public void registerMsg(MsgRcv msgrcv){
        Session dest = getSessionById(msgrcv.getMsg().getTo());
        Msg savedMsg = new Msg(msgrcv.getMsg().getFrom(), msgrcv.getMsg().getDate(), msgrcv.getMsg().getContent());
        if (dest!=null){
            if (this.commandCheck(msgrcv.getMsg())){
                dest.addFriend(savedMsg);
            } else {
                dest.addMsg(savedMsg);
            }
        }
    }
    
    public Session getSessionById(String id){
        for(Session session : this.sessions){
            if (session != null && id.equals(session.getId())) {
                    return session;
            }
        }
        return null;
    }
    
    
    public void forwarding(Session session, int code) throws SocketException, IOException{
        while (!session.isEmptyFriend() && code==1){
            server.send(session.popFriend(), session.getIp(), session.getPort());
        }
        while (!session.isEmptyMsg()){
            server.send(session.popMsg(), session.getIp(), session.getPort());
        }
        this.notifing(0, session);

    }
    
    /*
    public void forwarding(Msg m, boolean c) throws UnknownHostException, SocketException, IOException{
        for (Session session : this.sessions) {
            if(m.getId().equals(session.getId()) && !session.isEmptyMsg()){
                StackMsg n = session.getListFriends();
                for(int j = 0; j < session.getSizeMsg(); j++){
                    if ( !session.nextEmpty()){
                        server.send(session.popMsg(), session.getIp(), session.getPort());
                    }
                }
                for(int k = 0; k < session.getSizeFriend(); k++){
                    if(c==true && !n.isEmpty()){
                        server.send(n.getMsg(), session.getIp(), session.getPort());
                        n = n.getNext();
                    }
                }
                this.notifing(0, session);
                break;
            }
        }
    }
    */
    public void notifing( int Code, Session session) throws UnknownHostException, SocketException, IOException{
        server.send(new Msg(Code), session.getIp(), session.getPort());
    }
    public void notifing( int Code, InetAddress ip, int port) throws UnknownHostException, SocketException, IOException{
        server.send(new Msg(Code), ip, port);
    }
    public boolean commandCheck(Msg msg){
        return msg.getContent().equals("/RqstFrd");
    }

}
