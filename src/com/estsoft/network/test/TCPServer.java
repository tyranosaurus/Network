package com.estsoft.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
   private final static int PORT = 5050;

   public static void main(String[] args) {
      ServerSocket serverSocket = null;
      try {
         // 1. 서버 소켓 생성
         serverSocket = new ServerSocket();
   
         // 2. Binding
         InetAddress inetAddress = InetAddress.getLocalHost();
         String localhostAddress = inetAddress.getHostAddress();
         
         serverSocket.bind(new InetSocketAddress(localhostAddress, PORT));
         
         System.out.println("[server] binding "+ localhostAddress + " : " +PORT);
         
         // 3. accept 연결 요청 기다림
         Socket socket = serverSocket.accept();   //blocking.프로그램이 여기서 잠시 멈춰있음.
         
         // 4. 연결 성공
         InetSocketAddress remoteAddress = 
               (InetSocketAddress)socket.getRemoteSocketAddress();
         //String remoteHostAddress = remoteAddress.getAddress().getHostAddress();
         String remoteHostAddress = remoteAddress.getHostName();
         int remoteHostPort = remoteAddress.getPort();
         System.out.println("[server] connected from " + 
               remoteHostAddress +" : "+remoteHostPort);
         // 5. IOStream 받아 오기
         try{
            InputStream inputStream = socket.getInputStream();
            OutputStream outputstream = socket.getOutputStream();
            while(true){
               // 6. 데이터 읽기
               byte[] buffer = new byte[256];
               int readByteCount = inputStream.read(buffer);      //blocking. 
               if(readByteCount <= -1){
                  //정상 종료
                  System.out.println("[server] closed by client");
                  break;
               }
               String data = new String(buffer,0,readByteCount,"UTF-8");
               System.out.println("[server] received data : " + data);
               // 7. 쓰기
               outputstream.write(data.getBytes("UTF-8"));
            }
         } catch(SocketException ex){
            
            //비정상 종료
            System.out.println("[server] 비정상적으로 클라이언트가 종료 되었습니다.");
         
         } catch(IOException ex) {
            ex.printStackTrace();
         
         } finally{
         
            // 8. 자원 정리
            if(socket!=null && socket.isClosed()==false){
               socket.close();
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         // 서버 socket 닫기
         try {
            if(serverSocket != null && serverSocket.isClosed() == false){
               serverSocket.close();
            }
         } catch (IOException ex){
            ex.printStackTrace();
         }
      }
   }

}