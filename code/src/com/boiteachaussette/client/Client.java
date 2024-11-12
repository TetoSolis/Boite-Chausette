package com.boiteachaussette.client;

public class Client {
    String Id;
    String Mdp;
    
    public Client(String id, String mdp){
        Id = id;
        Mdp = mdp;
    }
    public void setId(String id){
        Id = id;
    }
    public void setMdp(String mdp){
        Mdp = mdp;
    }
    public String getId(){
        return Id;
    }
    public String getMdp(){
        return Mdp;
    }
}
