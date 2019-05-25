package com.lanxum.jd.mobile.print.common.utils;

import java.io.*;
import java.net.Socket;

/**
 * Created by JiangJunpeng on 2019/3/19.<br>
 */
public class SocketSend extends Socket {
    private  final String SERVER_IP="10.2.255.34";
    private final int SERVER_PORT=8090;
    private Socket client;
    private FileInputStream fis;
    private DataOutputStream dos;
    private PrintStream out;

    //创建客户端，并指定接收的服务端IP和端口号
    public SocketSend() throws IOException {
        this.client=new Socket(SERVER_IP,SERVER_PORT);
        System.out.println("成功连接服务端..."+SERVER_IP);
    }

    //向服务端传输文件
    public String sendFile(String url, String str) throws IOException {
        File file=new File(url);
        try {
            fis = new FileInputStream(file);
            //向服务端发送数据
            out = new PrintStream(client.getOutputStream());
            out.println(str);
            //client.getOutputStream()返回此套接字的输出流
            dos = new DataOutputStream(client.getOutputStream());
            //文件名、大小等属性
            dos.writeUTF(file.getName());
            dos.flush();
            dos.writeLong(file.length());
            dos.flush();
            // 开始传输文件
            System.out.println("======== 开始传输文件 ========");
            byte[] bytes = new byte[1024];
            int length = 0;

            while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                dos.write(bytes, 0, length);
                dos.flush();
            }
            System.out.println("======== 文件传输成功 ========");
//            if("1".equals(str)){
//                client.shutdownOutput();
//                //接收来自服务端的信息
//                InputStream  is = client.getInputStream();
//                byte[] b1 = new byte[1024];
//                int length1;
//                String str1 = "";
//                while((length1 = is.read(b1)) != -1){
//                    str1 = new String(b1,0,length1);
//                    System.out.println(str1);
//                }
//                return str1;
//            }
            return "success";
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("客户端文件传输异常·12345·······························································");
            return "error";
        }finally{
            fis.close();
            dos.close();
            out.close();
        }
    }
}
