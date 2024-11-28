package org.example.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static final String IMAGE_LOCATION = "images/";

    public static boolean saveImage(MultipartFile multipartFile, String fileName) throws IOException {
        String storageName = IMAGE_LOCATION + fileName;
        File file = new File(storageName);
        if (!file.exists()){
            if (!file.createNewFile()) {
                System.out.println("文件创建失败");
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        return false;
    }
}
