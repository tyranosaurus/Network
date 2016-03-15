package com.estsoft.network.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		while( true )
		{
				try {
			
				
				System.out.print((">>"));
			
				String hostname = scanner.nextLine();
				if( "exit".equals(hostname))
				{
					scanner.close();
					break;
				}
				InetAddress[] inetAddresses = InetAddress.getAllByName(hostname);
			
				for ( int i = 0; i < inetAddresses.length; i++)
				{
					System.out.println(hostname + " : " +inetAddresses[i].getHostAddress());
				}
			
				
				
				
				} catch (UnknownHostException e) 
				{
				System.out.println("알수없는 도메인 입니다.");;
				}
		}
		scanner.close();

}
}