package Entities;

import java.util.ArrayList;
import java.util.List;

public class Warrior {
	
	//The name the player enters for his character
	public String Name;
	public boolean HasTarget = false;
	
	///Type is the "class" name for this type of warrior, 
	//description and origin are the same for all warriors of this 'type'
	public String Type;
	public String Description;
	public String Origin;
	
	//All warriors implement the same basic 3 types of attacks
	public Attack Weak;
	public Attack Normal;
	public Attack Strong;
	
	//character attributes
	public int CurrentHealth;
	public int MaxHealth;
	
	public int CurrentStamina;
	public int MaxStamina;
	
	public List<String> Taunts;
	
	public boolean IsZombie = false;
	
	public void Zombify(){
		Type = String.format("Zombie %s", Type);
		Description = "MMMmmmm..... BRAINZZ!!!";
		Origin = "The fleshy remains of your slain victims.";
		
		Weak = new Attack();
		Weak.Name = "Grab";
		Weak.Strength = 4;
		Weak.StaminaCost = 2;
		
		Normal = new Attack();
		Normal.Name = "Scratch";
		Normal.Strength = 8;
		Normal.StaminaCost = 4;
		
		Strong = new Attack();
		Strong.Name = "Bite";
		Strong.Strength = 16;
		Strong.StaminaCost = 8;
		
		CurrentHealth = 80;
		MaxHealth = 80;
		
		CurrentStamina = 0;
		MaxStamina = 10;
		
		Taunts = new ArrayList<String>();
		Taunts.add("MMmm....  Brains....");
		Taunts.add("........        uuuuuugggggggg");
		
		IsZombie = true;
	}
}
