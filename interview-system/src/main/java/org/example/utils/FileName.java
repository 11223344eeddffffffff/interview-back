package org.example.utils;

import java.util.UUID;

public class FileName {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String getNewFileName(String fileOriginName){

        return FileName.getUUID() + FileName.getSuffix(fileOriginName);
    }
}
