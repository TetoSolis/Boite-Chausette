package com.boiteachaussette.run;


import com.boiteachaussette.net.ChaussetteClient;
import com.boiteachaussette.msg.MsgRcv;
import com.boiteachaussette.msg.StackMsg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;

public class BasicClient {
    public static void main(String[] args) throws SocketException, IOException, UnknownHostException, ParseException{
        ChaussetteClient pif = new ChaussetteClient("pif","paf", "127.0.0.1", 4444);
        ChaussetteClient plouf = new ChaussetteClient("plouf","paf", "127.0.0.1", 4444);
//        ChaussetteClient paf = new ChaussetteClient("paf","mauvaismdp", "127.0.0.1", 4444);
        
        
        StackMsg lstPif = pif.update(1);
        StackMsg lstPlouf = plouf.update(1);
 
        pif.sendMessage("plouf", "/RqstFrd");
        plouf.sendMessage("pif", "/RqstFrd");

        pif.update(1);
        plouf.update(1);

        pif.sendMessage("plouf", "Hello!");
        plouf.update(0);

//        paf.update(0);
//        plouf.update(0);
//        paf.sendMessage("plouf", "Plouf!");
//        plouf.update(0);
        
        
        //StackMsg lstPaf = paf.update(0);
        //StackMsg lstPlouf = plouf.update(0);
        
        //pif.update(0);

        //pif.sendMessage("plouf", "/RqstFrd");
        //plouf.sendMessage("pif", "/RqstFrd");

        
        //pif.sendMessage("plouf", "Plouf!");

        //plouf.update(0);
        //pif.update(0);
        //lstPlouf = plouf.update(1);
        //System.out.println("Message: "+lstPlouf.getMsg().getContent());

        
        
        
    }
}
