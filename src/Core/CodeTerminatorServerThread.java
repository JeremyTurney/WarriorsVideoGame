package Core;

import Core.CodeTerminatorSocketMessage.MessageTypes;
import Entities.Warrior;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class CodeTerminatorServerThread extends Thread {
	private Socket _socket = null;
	private CodeTerminatorProtocol _proto;
	
	public String ConnectedClientsName;
	
	public CodeTerminatorServerThread(Socket socket, String realmName){
		super("CodeTerminatorServerThread");
		
		_socket = socket;
		_proto = new CodeTerminatorProtocol(realmName, this);
	}
	
	public void run(){
		try(
			PrintWriter out = new PrintWriter(_socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
		)
		{
			_proto.AttachSocketWriter(out);
			
			System.out.println(String.format("Client Host Address:  %s", _socket.getInetAddress().getHostAddress()));
			
			String input;
			CodeTerminatorSocketMessage msg = new CodeTerminatorSocketMessage();
			_proto.ProcessRequest(msg);
						
			while((input = in.readLine()) != null){
				//*
				if(Constants.Debug){
					System.out.println("Message Received:  ");
					System.out.println(input);
					System.out.println();
				}
				//*/
				
				_proto.ProcessRequest(input);
				
				if(_proto.Disconnect)
					break;		
			}
			
			System.out.println(String.format("Client Host [%s] Socket Disconnecting.", _socket.getInetAddress().getHostAddress()));
			
		} catch (IOException e) {
            e.printStackTrace();
        }
		finally{
			System.out.println(String.format("Client [%s] Disconnected, removing player from game...", ConnectedClientsName));
			Warrior client = CodeTerminatorServer.ConnectedWarriors.remove(ConnectedClientsName);
			
			CodeTerminatorSocketMessage msg = new CodeTerminatorSocketMessage();
			msg.MessageType = client.CurrentHealth == 0 ? MessageTypes.Broadcast_Warrior_Died : MessageTypes.Broadcast_Warrior_Left;
			msg.ServerWarriors = CodeTerminatorServer.ConnectedWarriors;
			msg.TargetWarrior = client;
			BroadcastMessage(msg);
		}
		
		CodeTerminatorServer.lock.lock();
		try {
			CodeTerminatorServer.ConnectedClients.remove(this);
		} finally {
			CodeTerminatorServer.lock.unlock();
		}
		
		try{
			_socket.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void BroadcastMessage(CodeTerminatorSocketMessage msg){
		CodeTerminatorServer.BroadcastMessage(msg);
	}
	
	public void TransmitMessage(CodeTerminatorSocketMessage msg){
		_proto.SendSocketMessage(msg);
	}
}
