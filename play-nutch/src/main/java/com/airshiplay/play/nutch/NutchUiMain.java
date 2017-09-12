package com.airshiplay.play.nutch;

import org.apache.nutch.webui.NutchUiServer;

/**
 * Created by lig on 17/4/14.
 */
public class NutchUiMain {
    public static void main(String args[]){
        try {
            NutchUiServer.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
