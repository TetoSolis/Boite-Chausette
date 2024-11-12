
package com.boiteachaussette.run;
import com.boiteachaussette.srv.BoiteAChaussette;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;

public class Server {
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException, ParseException{
       BoiteAChaussette Serveur = new BoiteAChaussette(4444);
       Serveur.listen();
    }
}