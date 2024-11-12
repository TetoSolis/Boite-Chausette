package com.boiteachaussette.net;

import com.boiteachaussette.msg.MsgRcv;
import com.boiteachaussette.msg.Msg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chaussette {
    DatagramSocket socket;
    DatagramPacket recu;


    public Chaussette(int portListen) throws SocketException, IOException{
        socket = new DatagramSocket(portListen) ;
    }
    public Chaussette() throws SocketException, IOException{
        socket = new DatagramSocket() ;
    }
    public Date stringToDate(String date) throws ParseException{
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return df.parse(date);
    }
    public String dateToString(Date date) throws ParseException{
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return  df.format(date);
    }
    public Date getCurrentTime(){
        return new Date();
    }
    
    public MsgRcv listen() throws SocketException, IOException, ParseException {
        byte[] buf = new byte[1000];
        recu = new DatagramPacket(buf, buf.length);
        socket.receive(recu);
        String dataRaw = new String(recu.getData(), 0, recu.getLength());
        System.out.println("Reçu:\n    Message :  " + dataRaw + "\n    IP      :  " + recu.getAddress() + ":" + recu.getPort());
        String[] msg = dataRaw.split(",");
        
        return switch (msg[0]) {
            case "FORWARD" -> new MsgRcv(new Msg(msg[1],this.stringToDate(msg[2]),msg[3]), recu.getAddress(), recu.getPort());
            case "SEND" -> new MsgRcv(new Msg(msg[1],msg[2],msg[3],this.stringToDate(msg[4]),msg[5]), recu.getAddress(), recu.getPort());
            case "UPDATE" -> new MsgRcv(new Msg(msg[1],msg[2],Integer.parseInt(msg[3])), recu.getAddress(), recu.getPort());
            case "NOTIFY" -> new MsgRcv(new Msg(Integer.parseInt(msg[1])), recu.getAddress(), recu.getPort());
            default -> null;
        };
    }

    public void send(Msg msg, InetAddress ipDest, int portDest) throws UnknownHostException, SocketException, IOException {
        String msgRaw = msg.toCSV();
        int msglen = msgRaw.length();
        byte [] message = new byte [msglen] ;
        msgRaw.getBytes(0, msglen, message, 0);
        System.out.println("Envoyé:\n    Message :  " + msgRaw + "\n    IP      :  " + ipDest + ":" + portDest);
        socket.send(new DatagramPacket(message, msglen,ipDest, portDest));
    }
}