package com.vaca.ping_android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NetDelay {

    public static void NetTest(String url) throws IOException {
        String lost = new String();
        String delay = new String();
        Process p = Runtime.getRuntime().exec("ping -c 4 " + url);
        BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String str = new String();
        while((str=buf.readLine())!=null){
            if(str.contains("packet loss")){
                int i= str.indexOf("received");
                int j= str.indexOf("%");
                System.out.println("丢包率:"+str.substring(i+10, j+1));
//					System.out.println("丢包率:"+str.substring(j-3, j+1));
                lost = str.substring(i+10, j+1);
            }
            if(str.contains("avg")){
                int i=str.indexOf("/", 20);
                int j=str.indexOf(".", i);
                System.out.println("延迟:"+str.substring(i+1, j));
                delay =str.substring(i+1, j);
                delay = delay+"ms";
            }

        }
    }


}
