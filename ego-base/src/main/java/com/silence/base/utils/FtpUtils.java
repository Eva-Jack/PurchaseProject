package com.silence.base.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

public class FtpUtils {
    /**
     *
     * @param hostname FTP服务器的主机名（IP）
     * @param port FTP服务器的端口
     * @param username FTP服务器登录的用户 （vsftp使用的是Linux的系统用户名密码）
     * @param password FTP服务器登录的密码
     * @param basePath 基础路径（访问路径的公共部分）
     * @param filePath 动态路径（根据日期生成的动态路径）
     * @param remoteFileName 上传的文件的文件名
     * @param in 文件流
     * @return
     */
    public static boolean upload(String hostname, int port, String username, String password, String basePath,
                                 String filePath, String remoteFileName, InputStream in){
        try {
            //需求:上传的文件需要按日期生成存放的路径，如：
            //2020-02-26  到时存放的目录为 /home/ftpuser/ego/images/2020/02/26/文件名

            //第一步：获得连接对象
            FTPClient client=new FTPClient();
            client.connect(hostname,port);

            //第二步：登录校验
            boolean login = client.login(username, password);
            if (login) {
                //第三步：设置上传参数
                //使用二进制的方式上传
                client.setFileType(FTP.BINARY_FILE_TYPE);
                //使用被动模式
                client.enterLocalPassiveMode();
                //指定上传的目录
                boolean flag= client.changeWorkingDirectory(basePath + filePath);
                if (flag==false) {
                    //问题：如果filePath（/2020/02/26/）现在Linux里面还没该目录，需先创建
                    //思路，一级一级的创建
                    String tempPath=basePath;
                    String[] split = filePath.split("/");
                    for (String path: split) {
                        tempPath=tempPath+"/"+path;
                        client.makeDirectory(tempPath);
                    }
                    //生成路径后，再重新设置一次
                    client.changeWorkingDirectory(basePath + filePath);
                }
                //第四步：上传
                client.storeFile(remoteFileName,in);
            }
            //第五步：断开连接
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传失败");
            return false;
        }


        return true;
    }

    public static void main(String[] args) {
        try {
            //动态日期
            Calendar calendar = Calendar.getInstance();
            int year=calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            System.out.println("年："+year+"月:"+(month+1)+",日："+day);
            System.out.println("/"+year+"/"+String.format("%02d",month+1)+"/"+day);


            String filePath="/"+year+"/"+String.format("%02d",month+1)+"/"+day;
            String fileName= UUID.randomUUID().toString()+".png";
            FileInputStream in=new FileInputStream("D:\\dir\\bd_logo1.png");
            boolean upload = FtpUtils.upload("192.168.68.13", 21, "ftpuser", "ftpuser", "/home/ftpuser/ego/images", filePath, fileName, in);
            System.out.println(upload);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
