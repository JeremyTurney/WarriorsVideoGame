package Core;

import Entities.Attack;
import Entities.Warrior;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeTerminatorSocketMessage {
	
	public enum MessageTypes{
		New,					//initial message creation
		Connected,				//server response that there is a connection
		New_Warrior,			//client request with a new warrior
		New_Warrior_Ack,		//server response that new warrior was accepted
		New_Warrior_Bad_Name,	//server response that new warrior was rejected
		
		Target_Change,			//client request to set/change target warrior
		Target_Change_Ack,		//server response to target change
		
		Taunt_Target,
		Taunt_Target_Ack,		//server telling all clients that a player has taunted another player
		Attack_Target,			//client request to attack a target
		Attack_Outcome,			//server response to results from an attack
		
		Updated_Warrior_List,	//server broadcast when a new player is connected, 
								//and the stat changes for all connected clients
		
		Update_Server_MyStats,	//client notifying the server that my Warrior details have changed 
		
		
		Broadcast_New_Warrior,	//server telling all clients that a new player has joined
		Broadcast_Warrior_Left, //server telling all clients that a player has left
		Broadcast_Warrior_Died, //server telling all clients that a player has died
		
		
		
	}
	
	public MessageTypes MessageType;
	public List<String> Arguments;
	public Warrior ClientWarrior;
	public Warrior TargetWarrior;
	public Attack TargetAttack;
	public Map<String, Warrior> ServerWarriors;
		
	public CodeTerminatorSocketMessage(){
		FlushArguments();
		MessageType = MessageTypes.New;
	}
	
	public void FlushArguments(){
		Arguments = new ArrayList<String>();
		ServerWarriors = new HashMap<String, Warrior>();
		ClientWarrior = null;
		TargetWarrior = null;
		TargetAttack = null;
	}
}
