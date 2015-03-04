package com.rmi.client;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import com.rmi.server.IChatServer;

public class ChatClient extends UnicastRemoteObject implements IChatClient , Runnable{

	private IChatServer chatServer;
	private String name = null;
	private static final long serialVersionUID = 1L;

	protected ChatClient(String name, IChatServer chatServer) throws RemoteException {
		this.name = name; 
		this.chatServer = chatServer;
		chatServer.registerChatClient(this);
	}

	public void retrieveMessage(String message) throws RemoteException {
		System.out.println(message);
		
	}

	@SuppressWarnings("resource")
	public void run() {
		Scanner scanner = new Scanner(System.in);
		String message;
		while(true){
			message = scanner.nextLine();
			try {
				chatServer.broadcastMessage(name + " : " + message);
			} 
			catch (RemoteException e) {
				
				e.printStackTrace();
			}
		}
	}


}
