package com.boiteachaussette.client;

import com.boiteachaussette.msg.Msg;


public class Friend {
    String Id;
    Msg[] messages = new Msg [10];
    
    
    public Friend (String id){
        Id = id;
    }
            
    public void addMsg(Msg msg){
        for(int i =0; i < 9; i++){
            if(messages[i]==null){
                messages[i] = msg;
                break;
            }
        }
    }
    public void setId(String Id){
        this.Id = Id;
    }
    public String getId(){
        return Id;
    }
    public Msg[] getMessages(){
        return messages;
    }
    
    public void del(){
        Id = null;
        for(int i = 0; i < 9; i++){
            messages[i] = null;
        }
    }
}
