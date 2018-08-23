package utils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shihai.li@hand-china.com on 2018/8/14.
 */
public class Utils {


    public void file() {

        FileSystemView fsv = FileSystemView.getFileSystemView();

        // 读取桌面路径的方法
        File com = fsv.getHomeDirectory();
        try {
            File filename = new File(com.getPath() + "\\testFile.txt");
            if (!filename.exists()) {
                filename.createNewFile();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        File f;
        File tempFile = new File( com.getPath() + "\\testFile.txt".trim());
        if (tempFile.exists()){
            f = tempFile;
        }else{
             f = new File(com.getPath() + "\\testFile.txt");
        }
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = sdf.format(new Date());
            fw.write(time);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //fw.close();
        }
    }
}
