package com.group6;

import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GUI {
    private JTextField input;
    private JButton sendButton;
    private JPanel MyPannel;
    private static TTSInterface remote;
    private static Registry registry;
    private static JFrame frame = new JFrame("GUI");

    private String getFilePath() {
        return "C:\\Windows\\Temp\\voice.mp3";
    }

    public GUI() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                System.out.println(input.getText());
                try {
                    remote.showText(input.getText());
                    byte[] voice = remote.executeText(input.getText());
                    if (voice == null) {
                        JOptionPane.showMessageDialog(frame, "Can't detect the language");
                    } else {
                        FileOutputStream fos = new FileOutputStream(getFilePath());
                        fos.write(voice, 0, voice.length);
                        fos.flush();
                        fos.close();
                        MP3Player mp3Player = new MP3Player(new File(getFilePath()));
                        mp3Player.play();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {
        try {
            registry = LocateRegistry.getRegistry(6060);
            remote = (TTSInterface) registry.lookup("tts");
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        frame.setContentPane(new GUI().MyPannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
