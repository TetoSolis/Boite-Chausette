package com.boiteachaussette.msg;
import java.text.SimpleDateFormat;
import java.util.*;

public class Msg {
    
    private String 
            type,
            mdp,
            from,
            to,
            id,
            content;
    private Date date;
    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private int code;
    
    public Msg (String mdp, String from, String to, Date date, String content){
        this.type = "SEND";
        this.mdp = mdp;
        this.from = from;
        this.to = to;
        this.date = date;
        this.content = content;
    };
    public Msg (String from, Date date, String content){
        this.type = "FORWARD";
        this.from = from;
        this.date = date;
        this.content = content;
    };
    public Msg (int code){
        this.type = "NOTIFY";
        this.code = code;
    };
    public Msg (String mdp, String id, int code){
        this.type = "UPDATE";
        this.mdp = mdp;
        this.id = id;
        this.code = code;
    };
    
    public Msg copy(){
        return switch (this.getType()){
            case "FORWARD" -> new Msg(this.getFrom() , this.getDate() , this.getContent());
            case "SEND" -> new Msg( this.getMdp(), this.getFrom(), this.getTo(), this.getDate(), this.getContent());
            case "UPDATE" -> new Msg(this.getMdp(), this.getFrom(), this.getCode());
            case "NOTIFY" -> new Msg(this.getCode());
            default -> null;
        };
    }
    public String getMdp(){
        return this.mdp;
    }; 
    
    public String getFrom(){
        return this.from;
    }; 
    public String getTo(){
        return this.to;
    };
    public Date getDate(){
        return this.date;
    };
    public String getDatePretty(){
        return dateFormat.format(this.date);
    };  
    public String getContent(){
        return this.content;
    };
    public String getType(){
        return this.type;
    }; 
    public int getCode(){
        return this.code;
    }; 
    public String getId(){
        return this.id;
    }; 
    
    
    public String toCSV(){
          return switch (this.type) {
            case "FORWARD" -> this.getType() + "," + this.getFrom() + "," + this.getDatePretty() + "," + this.getContent();
            case "SEND" -> this.getType() + "," + this.getMdp() + "," + this.getFrom() + "," + this.getTo() + "," + this.getDatePretty() + "," + this.getContent();
            case "UPDATE" -> this.getType() + "," + this.getMdp() + "," + this.getId() + "," + this.getCode();
            case "NOTIFY" -> this.getType() + "," + this.getCode();
            default -> null;
        };
    };
}