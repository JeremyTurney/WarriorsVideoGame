/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Proph
 */
public final class FileUtils {
	public static void WriteTextFile(String fileName, String text){
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			
			writer.write(text);
			writer.close();			
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static String ReadTextFile(String fileName){
			
		try {
			List<String> lines = Files.readAllLines(Paths.get(fileName));
			
			StringBuilder sb = new StringBuilder();
			
			for(String line : lines){
				sb.append(line);
			}
			
			return sb.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public static List<String> GetFilesWithExtension(String ext){
		List<String> list = new ArrayList<>();

		Path  curr = Paths.get("");
		
		File[] files = new File(curr.toUri()).listFiles();
		
		for(File f : files){
			if(f.getName().toLowerCase().endsWith(ext))
			list.add(f.getName());
		}
		return list;

	}
}
