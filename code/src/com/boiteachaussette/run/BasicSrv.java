package com.boiteachaussette.run;

import com.boiteachaussette.net.Chaussette;
import com.boiteachaussette.msg.MsgRcv;
import com.boiteachaussette.msg.Msg;



import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;



public class BasicSrv {

    public static void main(String[] args)  throws SocketException, IOException, ParseException{
        Chaussette plouf = new Chaussette(4444);
        MsgRcv currentMsg;
        while (true){
            currentMsg = plouf.listen();
            //System.out.println("Message Client: "+currentMsg.getMsg().toCSV());
            switch (currentMsg.getMsg().getType()){
                case "SEND" -> {

                }
                case "UPDATE" -> {
                    plouf.send(new Msg("plouf", plouf.getCurrentTime(), "/AcptFrd"), currentMsg.getIp(), currentMsg.getPort());
                    plouf.send(new Msg("plouf", plouf.getCurrentTime(), "Hello!"), currentMsg.getIp(), currentMsg.getPort());
                    plouf.send(new Msg("plouf", plouf.getCurrentTime(), "Prend du pain ce midi"), currentMsg.getIp(), currentMsg.getPort());
                    plouf.send(new Msg("plouf", plouf.getCurrentTime(), "Et une patisserie aussi!"), currentMsg.getIp(), currentMsg.getPort());
                    plouf.send(new Msg("plouf", plouf.getCurrentTime(), "Pif!"), currentMsg.getIp(), currentMsg.getPort());
                    plouf.send(new Msg(0), currentMsg.getIp(), currentMsg.getPort());
                }
            }
        }
    }
    
}
