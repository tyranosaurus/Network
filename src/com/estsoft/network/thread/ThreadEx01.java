package com.estsoft.network.thread;

public class ThreadEx01 
{
	public static void main(String[] args)
	{
		Thread thread01 = new DigitThread();
		Thread thread02 = new AlphabetThread();
		Thread thread03 = new AlphabetThread();
		Thread thread04 = new Thread(new UpperClassAlphabetRunnableelmpl());
		
		thread01.start();
		thread02.start();
		thread03.start();
		thread04.start();
		
	}
	
	
}
