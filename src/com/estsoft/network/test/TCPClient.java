package com.estsoft.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
   private static final String SERVER_IP = "192.168.1.10";
   private static final int SERVER_PORT = 5050;

   public static void main(String[] args) {
      Socket socket = null;
      try {
         //1. 소켓 생성      
         socket=new Socket();
   
         //2. 서버 연결
         socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
         
         //3. IOStream 받아오기
         InputStream inputStream = socket.getInputStream();
         OutputStream outputStream = socket.getOutputStream();
         
         //4. 읽기/쓰기
         String data = "Hello World";
         outputStream.write(data.getBytes("UTF-8"));
         byte[] buffer = new byte[256];
         
         int readByteCount = inputStream.read(buffer);
         if(readByteCount <= -1){
            //socket 연결 끊어짐
            System.out.println("[client] closed by server");
            return;
         }
         data = new String(buffer, 0, readByteCount, "UTF-8");
         System.out.println("[client] received : "+data);
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if(socket != null && socket.isClosed()==false){
            try {
               socket.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }

   }

}