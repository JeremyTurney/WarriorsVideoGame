/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Utilities;

import Core.Constants;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;



/**
 *
 * @author Proph
 */
public final class Xml {
	
	public static String SerializeObjectToXML(Object obj){
		XStream x = new XStream(new DomDriver());
		String xml = x.toXML(obj);
		
		return xml;
	}
	
	public static <T> T DeserializeXml(String xml){
		XStream x = new XStream(new DomDriver());
		Object obj = x.fromXML(xml);
		return (T)obj;
	}
	
	public static String SerializeObjectNoLineBreaks(Object obj){
		String xml = SerializeObjectToXML(obj);
		
		if(Constants.Debug){
			System.out.println("Sending Message:  ");
			System.out.println(xml);
			System.out.println("");
		}
		
		return xml.replace("\n", "");
	}
}
