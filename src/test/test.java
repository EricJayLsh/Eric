package test;

import db.DBConnect;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shihai.li@hand-china.com on 2018/8/12.
 */
public class test {

    private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String userName = "HDM_DEV";
    private static final String password = "HDM_DEV";
    private static final DBConnect dbConnect = new DBConnect();

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

    /**
     * Description：数据库连接类测试方法
     * 注意使用前先要将项目中导入 jdbc 相关数据库连接的jar包
     * 注意需要关闭数据库连接，释放资源
     * Author:@shihai.li@hand-china.com
     */
    public void dbTest() {
        try {
            String tableName = "sys_user";
            dbConnect.DBConnection("oracle", url, userName, password);
            ResultSet rs = dbConnect.selectAll(tableName);
            while(rs.next()){
                System.out.println(rs.getString("user_id") +"=="+rs.getString("user_name"));
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
            dbConnect.close();
        }
    }

   public void select(){
       try {
           String tableName = "sys_user";
           dbConnect.DBConnection("oracle", url, userName, password);
           ResultSet rs = dbConnect.select("10001");
           while(rs.next()){
               System.out.println(rs.getString("user_id") +"=="+rs.getString("user_name"));
           }
       } catch (ClassNotFoundException e1) {
           e1.printStackTrace();
       } catch (SQLException e2) {
           e2.printStackTrace();
       } finally {
           dbConnect.close();
       }
   }

    public void delete(){
        try {
            String tableName = "sys_user";
            dbConnect.DBConnection("oracle", url, userName, password);
            boolean rs = dbConnect.delete(tableName,"10004");
            System.out.println(rs);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
            dbConnect.close();
        }
    }

    /**
     * Description：Java 调用操作系统命令
     * Author:@shihai.li@hand-china.com
     */
    public void operatingSystem (){

        // 将 操作系统命令写入批处理文件中
        String path = "E:\\public.bat";
        Runtime run = Runtime.getRuntime();
        try {
            // run.exec("cmd /k shutdown -s -t 3600");
            Process process = run.exec("cmd.exe /k start " + path);
            InputStream in = process.getInputStream();
            while (in.read() != -1) {
                System.out.println(in.read());
            }
            in.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
