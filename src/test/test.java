package test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by shihai.li@hand-china.com on 2018/8/12.
 */
public class test {

    public void downLoadFile (){


        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com = fsv.getHomeDirectory();
        try {
            File filename = new File(com.getPath()+"\\test.txt");
            if (!filename.exists()) {
                filename.createNewFile();
            }
        }catch (IOException e1){
            e1.printStackTrace();
        }

        File f=new File(com.getPath()+"\\test.txt");
        FileWriter fw;

        try {
            fw = new FileWriter(f);
             String str = "Hello World!";
            fw.write(str);
            fw.close();
        } catch (IOException e) { e.printStackTrace();  }finally {
            //fw.close();
        }
    }




}
