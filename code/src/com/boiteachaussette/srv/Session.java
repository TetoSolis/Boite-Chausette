package com.boiteachaussette.srv;

import com.boiteachaussette.msg.MsgRcv;
import com.boiteachaussette.msg.Msg;
import com.boiteachaussette.msg.StackMsg;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class Session {
        private String id, mdp;
        
        private InetAddress ip;
        private int port;
        
        private StackMsg lstMsg, lstFriends;
        
        public Session(String id, String mdp, InetAddress ip, int port){
            this.id = id;
            this.mdp = mdp;
            this.ip = ip;
            this.port = port;
            this.lstFriends = new StackMsg(null);
            this.lstMsg = new StackMsg(null);
        }
        public void addMsg(Msg msg){
            lstMsg = new StackMsg(msg, lstMsg);
        }
        public void addFriend(Msg msg){
            lstFriends = new StackMsg(msg, lstFriends);
        }
        public void delFriend(String id){
            lstFriends.removeByFrom(id);
        }
        public Msg popFriend(){
            return lstFriends.pop();
        }
        public Msg popMsg(){
            return lstMsg.pop();
        }
        public void delMsg(){
            lstMsg.delMsg();
        }
        public Msg getMsg(){
            return lstMsg.getMsg();
        }
        public InetAddress getIp(){
            return this.ip;
        }
        public int getPort(){
            return this.port;
        }
        public String getId(){
            return this.id;
        }
        public String getMdp(){
            return this.mdp;
        }
        public StackMsg getListFriends(){
            return this.lstFriends;
        }
        public boolean isEmptyFriend(){
            return this.lstFriends.getMsg()==null;
        }
        public boolean isEmptyMsg(){
            return this.lstMsg.getMsg()==null;
        }
        public boolean nextEmpty(){
            return this.lstMsg.getNext()==null;
        }
        public boolean auth(String id, String mdp){
            return this.id.equals(id) && this.mdp.equals(mdp);
        }
        public void renew(InetAddress ip, int port){
            this.ip = ip; this.port = port;
        }
        public int getSizeMsg(){
            return this.lstMsg.getSize();
        }
        public int getSizeFriend(){
            return this.lstFriends.getSize();
        }
}
