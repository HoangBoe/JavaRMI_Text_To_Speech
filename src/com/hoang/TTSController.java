package com.hoang;


import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.voicerss.tts.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TTSController extends UnicastRemoteObject implements TTSInterface {


    protected TTSController() throws RemoteException {
    }


    @Override
    public void showText(String text) throws RemoteException {
        System.out.println(text);
    }

    @Override
    public byte[] executeText(String text) throws RemoteException, APIError {
        VoiceProvider tts = new VoiceProvider("d053ea3b17f4445a89e2b0571d519f42");
        DetectLanguage.apiKey = "509978fd97343d964fcd5ccfb8fce0e0";
        List<Result> results = DetectLanguage.detect(text);

        Result result = results.get(0);

//        if (result != DetectLanguage.detect(text)) {
//            result.language = Languages.English_UnitedStates;
//        }

        System.out.println("Language: " + result.language);
        //System.out.println("Confidence: " + result.confidence);

        Map<String, String> map = new HashMap<>();
        map.put("en", Languages.English_UnitedStates);
        map.put("ca", Languages.Catalan);
        map.put("zh", Languages.Chinese_China);
        map.put("da", Languages.Danish);
        map.put("nl", Languages.Dutch);
        map.put("fi", Languages.Finnish);
        map.put("fr", Languages.French_France);
        map.put("de", Languages.German);
        map.put("it", Languages.Italian);

        VoiceParameters params = new VoiceParameters(text, map.get(result.language));
        params.setCodec(AudioCodec.MP3);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);

        try {
            return tts.speech(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
