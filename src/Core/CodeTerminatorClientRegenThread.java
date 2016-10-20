/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author Proph
 */
public class CodeTerminatorClientRegenThread extends Thread {
	
	private CodeTerminatorProtocol _proto;
	private boolean _kill = false;
	
	public CodeTerminatorClientRegenThread(CodeTerminatorProtocol proto){
		_proto = proto;
	}
	
	@Override
	public void interrupt(){
		_kill = true;
	}
	
	@Override
	public void run(){
		while(!_kill && !_proto.Disconnect){
			try{
				//every 10 seconds
				Thread.sleep(10000);
			}
			catch(Exception ex){
				if(_kill || _proto.Disconnect)
					return;
			}

			RegenStats();
		}
	}
	
	private void RegenStats(){
		if(_kill)
			return;
		
		if(_proto.Player.CurrentHealth <= 0)
			return;
		
		boolean redraw = false;
		boolean updateServer = false;
		
		if(_proto.Player.CurrentHealth < _proto.Player.MaxHealth){
			if(_proto.Player.HasTarget){
				//then we're engaged, regen is slower for health	
				_proto.Player.CurrentHealth += 2;
			}
			else{
				//else health regen is normal
				_proto.Player.CurrentHealth += 8;
			}

			if(_proto.Player.CurrentHealth > _proto.Player.MaxHealth)
				_proto.Player.CurrentHealth = _proto.Player.MaxHealth;
			
			updateServer = true;
		}
		
		if(_proto.Player.CurrentStamina < _proto.Player.MaxStamina){
			_proto.Player.CurrentStamina += 2;
			
			if(_proto.Player.CurrentStamina > _proto.Player.MaxStamina)
				_proto.Player.CurrentStamina = _proto.Player.MaxStamina;
			
			redraw = true;
		}
		
		
		if(updateServer || redraw){
			_proto.RenderPlayerScreen();
			_proto.RenderActionQuery();
		}
		
		if(updateServer)
			_proto.SendServerMyStatusChange();
	}
}
