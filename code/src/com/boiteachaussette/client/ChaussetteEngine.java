package com.boiteachaussette.client;


import com.boiteachaussette.net.ChaussetteClient;
import com.boiteachaussette.msg.MsgRcv;
import com.boiteachaussette.msg.StackMsg;
import com.boiteachaussette.msg.Msg;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Arrays;

public class ChaussetteEngine {
    Friend[] Friends;
    Client User;
    ChaussetteClient Client;
    
    public ChaussetteEngine(String id, String mdp)throws SocketException, IOException{
        this.Friends = new Friend[15];
        
        Client = new ChaussetteClient(id,mdp, "127.0.0.1", 4444);
        User = new Client(id, mdp);
    }
    public void setChaussetteClient(String id, String mdp){
        User.setId(id);
        User.setMdp(mdp);
    }
    public void setId(String id){
        User.setId(id);
    }
    public void setMdp(String mdp){
        User.setMdp(mdp);
    }
    public String getId(){
        return User.getId();
    }
    public String getMdp(){
        return User.getMdp();
    }
    
    public void addFriend(String id)throws SocketException, IOException{
        for(int i = 0; i < 14; i++){
            if(Friends[i]==null){
                Friends[i] = new Friend(id);
                send(Friends[i].getId(), "/RqstFrd");
                break;
            }
        }
    }
    
    public void delFriend(String id){
        for(int i =0; i < 14; i++){
            if(Friends[i].getId().equals(id)){
                Friends[i].del();
            }
        }
    }
    public String[] getFriends(){
        String[] FrndTbl = new String[15];
        for(int i = 0; i < 14; i++){
            if(Friends[i]!=null){
                FrndTbl[i] = Friends[i].getId();
            }
        }
        return FrndTbl;
    }
    public int indexFriend(String id){
        for(int i =0; i < 14; i++){
            if(Friends[i]!=null){
                if(Friends[i].getId().equals(id)){
                    return i;
                }
            }
        }
        return 15;
    }
    public void send(String to, String content)throws SocketException, IOException{
        Friends[indexFriend(to)].addMsg(new Msg(getMdp(), getId(),Friends[indexFriend(to)].getId(),Client.getCurrentTime(),content));
        Client.sendMessage(to, content);
    }
    public boolean getUpdate(int Code)throws SocketException, IOException, UnknownHostException, ParseException{
        return treat(Client.update(Code));
    }
    public Msg[] getMsg(String id){
        return Friends[indexFriend(id)].getMessages();
    }
    public Msg[] getMsg(int id){
        return Friends[id].getMessages();
    }
    public boolean treat(StackMsg lst) throws SocketException, IOException{
        while(!lst.isEmpty()){
            Msg msg= lst.pop();
            System.out.println("Action: \n    Treat    :  "+msg.toCSV());
            switch(msg.getType()){
                case "NOTIFY" -> {
                    return (msg.getCode()==0);
                    }
                case "FORWARD" -> {
                    this.registerMsg(msg);
                    }
            }
        }
        return true;
    }
    public void registerMsg(Msg m) throws SocketException, IOException{
        Friends[indexFriend(m.getFrom())].addMsg(m);
        System.out.println(Arrays.toString(Friends[indexFriend(m.getFrom())].getMessages()));
    }
    
}
        