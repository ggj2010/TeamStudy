package com.team.gaoguangjin.javabase.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServiceServer {
	public static void main(String[] args) throws UnknownHostException, IOException {
		ServerSocket server = new ServerSocket(34);
		Socket client = server.accept();
		while (true) {
			PrintStream ps = new PrintStream(client.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
			System.out.println(br.readLine());
			ps.write("this is message \r\n".getBytes());
		}
	}
}
