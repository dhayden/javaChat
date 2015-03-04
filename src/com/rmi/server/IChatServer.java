package com.rmi.server;
import java.rmi.Remote;
import java.rmi.RemoteException;
import com.rmi.client.IChatClient;

public interface IChatServer extends Remote {
	void registerChatClient(IChatClient chatClient) throws RemoteException;
	void broadcastMessage(String message) throws RemoteException;
}
