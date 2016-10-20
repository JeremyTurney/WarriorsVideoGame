package Core;

import Entities.Warrior;
import Entities.Attack;
import Utilities.FileUtils;
import Utilities.Xml;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CodeTerminatorServer {

	public static void init(String[] args) {
				
		String serverName = null;
		int portNumber = -1;
		
		switch(args.length){
			case 1:
				portNumber = Constants.DefaultPort;
				serverName = args[0];
				break;
				
			case 2:
				serverName = args[0];
		        portNumber = Integer.parseInt(args[1]);
				break;
			
			case 3:
				Warrior template = new Warrior();
				
				template.Type = "Warrior Type";
				
				//name is able to be left blank, because the user will enter a name at runtime
				//template.Name = "Warrior Name";
				template.CurrentHealth = 100;
				template.MaxHealth = 100;
				template.Description = "Description goes here";
				template.Origin = "Origin goes here";
				
				template.Weak = new Attack();
				template.Weak.Name = "Weak Name";
				template.Weak.Strength = 1;
				
				template.Normal = new Attack();
				template.Normal.Name = "Normal Name";
				template.Normal.Strength = 5;
				
				template.Strong = new Attack();
				template.Strong.Name = "Strong Name";
				template.Strong.Strength = 10;
				
				template.Taunts = new ArrayList();
				template.Taunts.add("Taunt 1: yo mamma");
				template.Taunts.add("Taunt 2: I'm rubber you're glue");
				
				
				String xml = Utilities.Xml.SerializeObjectToXML(template);
				Utilities.FileUtils.WriteTextFile("template.wdat", xml);
				return;
				
			default:
				System.err.println("Usage: java Terminator <server name> [<port number>]");
		        System.exit(1);
				break;
		}
		
		System.out.println(String.format("Starting Code Terminator's Server: \r\nServer Name:  %s\r\nServer Port:  %d", serverName, portNumber));
		
        boolean listening = true;
        
		ConnectedWarriors = new HashMap<String, Warrior>();
		ConnectedClients = new ArrayList<CodeTerminatorServerThread>();
		
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
				
				try{
					CodeTerminatorServerThread th = new CodeTerminatorServerThread(serverSocket.accept(), serverName);
					
					lock.lock();
					try{
						ConnectedClients.add(th);
					}
					finally{
						lock.unlock();
					}
				
					th.start();
				}
				catch(Exception ex){
					System.out.println(ex.toString());
				}
				
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
	}
	
	private static List<String> _warriorFiles = null;
	public static List<String> GetWarriorFiles(){
		if(_warriorFiles == null)
			_warriorFiles = FileUtils.GetFilesWithExtension(".wdat");
		
		return _warriorFiles;
	}
	
	public static Warrior LoadWarriorFile(String fileName){
		String xml = FileUtils.ReadTextFile(fileName);
		return Xml.DeserializeXml(xml);
	}
	
	public static Lock lock = new ReentrantLock();
	
	public static Map<String, Warrior> ConnectedWarriors;
	public static List<CodeTerminatorServerThread> ConnectedClients; 
	
	public static void BroadcastMessage(CodeTerminatorSocketMessage msg){
		lock.lock();
		try {
			for(CodeTerminatorServerThread th : ConnectedClients){
				th.TransmitMessage(msg);
			}
		} finally {
			lock.unlock();
		}
	}
}
