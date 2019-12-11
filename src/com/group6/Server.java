package com.group6;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(6060);
            TTSInterface tts = new TTSController();
            registry.rebind("tts", tts);
            System.out.println("Server is ready to connect!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
