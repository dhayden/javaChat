package com.rmi.server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import com.rmi.client.IChatClient;

public class ChatServer extends UnicastRemoteObject implements IChatServer {

	private static final long serialVersionUID = 1L;
	private ArrayList<IChatClient> chatClients;
	
	protected ChatServer() throws RemoteException {
		chatClients = new ArrayList<IChatClient>();
	}

	public synchronized void registerChatClient(IChatClient chatClient)
			throws RemoteException {
		this.chatClients.add(chatClient);
	}

	public synchronized void broadcastMessage(String message) throws RemoteException {
		int i = 0;
		
		while(i < chatClients.size())
		{
			chatClients.get(i++).retrieveMessage(message);
		}
		
	}

}
