package com.airshiplay.play.nutch;

import org.apache.nutch.api.NutchServer;

/**
 * Created by lig on 17/4/15.
 */
public class NutchMain {
    public static void main(String args[]){
        try {
           //String a="hadoop.home.dir=/Users/lig/Downloads/hbase-0.98.8-hadoop2";
            NutchServer.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
