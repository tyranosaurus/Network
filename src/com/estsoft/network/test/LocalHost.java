package com.estsoft.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost 
{
	public static void main(String[] args)
	{
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			String hostAddress = inetAddress.getHostAddress();
			String hostname = inetAddress.getHostName();
			
			System.out.println(hostAddress);  // IP어드레스가 나옴 
			System.out.println(hostname);  // 컴퓨터 이름이 나옴
			byte[] addresses = inetAddress.getAddress(); // 주소가 바이트 단위로 나온다.
			
			
			for ( int i = 0; i < addresses.length; i++)
			{
				int address = addresses[i] & 0x000000ff;
				System.out.print(address);
				if ( i < addresses.length - 1)
				{
					System.out.print(".");
				}
			}
			
			
			
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
}
