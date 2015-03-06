package com.rmi.provider;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Provider extends UnicastRemoteObject implements IProvider {
	

	private static final long serialVersionUID = 1L;
	//private members
	private Vector chatrooms;
        private Vector info; 
	private String name;
	String [] Users=new String[50];
	public String hold_last_message;

	//constructor
	public Provider(String s) throws RemoteException {
		super(); 
		chatrooms = new Vector();
            info = new Vector();
            name = s; 
	}

	//Register a chat room 
	public int register(String name, String information) throws RemoteException 
	{
            System.out.println("Registering Chat Room: " + name);
	    chatrooms.add(name);
            info.add(information);
	    return 1;
	}

	//unregister a chat room
	public int unregister(String name) throws RemoteException 
	{
                int index = chatrooms.indexOf(name); 
		System.out.println("Unregistering Chat Room: " + name);
		chatrooms.remove(name);
		info.remove(index); 
		return 1;
	}
      //get information regarding a Chat room
      public String getinfo(String name) throws RemoteException
	{
		String i;
                int index = chatrooms.indexOf(name);  		
		i = (String) info.get(index);
		return i;
	}

	//Return a list of information of the ChatRooms
	public Vector getChatRooms() {
		return chatrooms;
            	}

//Return a informations of the registered ChatRooms
	public Vector getInformation() {
		 return info; 
	}
//user sign in a chat room

public int signIn(String s,int i) throws java.rmi.RemoteException{
        boolean free_spot=false;
        int j=0;
        if(i==-1){
            while((!free_spot)&(j<50)){
                if(Users[j]==null){
                    Users[j]=s;
                    free_spot=true;
                    return j;
                }
                else
                j++;
            }
            return -1;//Connection refused
        }
        else{
            Users[i]=null;
            return -1;
        }
    }
//broadcast the message

	public void broadcast(String s,int i) throws java.rmi.RemoteException
	    {hold_last_message=hold_last_message+"\n"+Users[i]+": "+s;
	    }

//get the broadcast message

	public String getbroadcast() throws java.rmi.RemoteException
	{return hold_last_message;}

//reset hold_last_message

       public void reset() throws java.rmi.RemoteException
        {hold_last_message="";}

//Main Program...
public static void main(String[] args) {
		
		//int i; 
                System.out.println("Starting RMI Chat Server");
		
		try {
			
			// Load the service
			Provider server = new Provider("ChatServer");
			
			//Examine the service, to see where it is stored
			RemoteRef location = server.getRef();
			System.out.println(location.remoteToString());
		 	
			// Chech to see if a registry was specified
			String registry = "localhost";
			if(args.length >=1)
			{
				registry = args[0];
			}
			
		// Registration format //registry_hostname :port /service
		// :port if optional
	String registration = "rmi://" + registry + ":4099/ChatRegistry";
			
		// Register with service so that clients can find us
			Naming.rebind(registration, server);
			
		}catch(RemoteException re){
			System.err.println("Remote Error - " + re);
		}catch(Exception e){
			System.err.println("Error - " + e);
		}
	}


}
