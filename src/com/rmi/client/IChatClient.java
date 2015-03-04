package com.rmi.client;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatClient extends Remote {
	void retrieveMessage(String message) throws RemoteException;
	

}
