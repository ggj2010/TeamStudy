package com.test;

public class Synchronized {
	public static void main(String[] args) {
		new Synchronized();
	}
	
	Synchronized() {
		Synchronized a = this;
		Synchronized b = this;
		synchronized (a) {
			try {
				b.wait();
				System.out.println("1");
			} catch (InterruptedException e) {
				System.out.println("2");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("3");
				e.printStackTrace();
			}
			finally {
				System.out.println("finally");
			}
		}
		System.out.println("alldone");
	}
}
