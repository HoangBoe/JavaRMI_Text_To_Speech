package com.hoang;


import com.detectlanguage.errors.APIError;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TTSInterface extends Remote {

    void showText(String text) throws RemoteException;

    byte[] executeText(String text) throws RemoteException, APIError;

}
