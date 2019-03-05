package utils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by shihai.li@hand-china.com on 2018/8/14.
 */
public class Utils implements  Comparable{


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
        File tempFile = new File(com.getPath() + "\\testFile.txt".trim());
        if (tempFile.exists()) {
            f = tempFile;
        } else {
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


    // java 中的arrayList 和 linkedList

    public void testList (){

        List<Long> array = new ArrayList<>();

        List<Long> linked = new LinkedList<>();

        Collections.sort(array);

        Collections.emptyList();


        Collections.copy(array,linked);

        Collections.fill(array, 123L);



    }
    // 实现该接口中的方法来完成排序比较
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
