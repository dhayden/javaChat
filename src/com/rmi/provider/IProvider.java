package com.rmi.provider;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

//Interface for the Provider 

public interface IProvider extends Remote {
	
	public int register(String name, String info) throws RemoteException;
	public int unregister(String name)throws RemoteException;
	public String getinfo(String name) throws RemoteException;
    public Vector getChatRooms() throws RemoteException;  
	public Vector getInformation() throws RemoteException;
	int  signIn(String s, int i) throws java.rmi.RemoteException;
	void broadcast(String s,int i ) throws java.rmi.RemoteException;
	String getbroadcast() throws java.rmi.RemoteException;
    void reset() throws java.rmi.RemoteException; 

}
