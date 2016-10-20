/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author Proph
 */
public final class Console {
	
	public static void ClearConsole(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 50; i++)
			sb.append("\r\n");
		
		System.out.println(sb.toString());
	}
}
