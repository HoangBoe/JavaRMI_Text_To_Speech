package com.hoang;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(6060);
            registry.lookup("tts");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
