package de.Bethibande.Networking.API;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class SendPacket {

    private static Gson g = new Gson();

    public static void sendMessage(Socket s, String message) {
        try {
            PrintWriter w = new PrintWriter(s.getOutputStream());

            System.out.println("write " + message);
            w.println(message + " \n");
            w.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendPacket(Socket s, Packet p) {
        JsonElement jsonElement = g.toJsonTree(p);
        jsonElement.getAsJsonObject().addProperty("className", p.getClass().toString().split(" ")[1]);
        sendMessage(s, g.toJson(jsonElement));
    }

}
