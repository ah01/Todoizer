package cz.horcica.todoizer;

import org.w3c.tidy.servlet.util.HTMLEncode;

/**
 * HTML Helper
 * 
 * @author Adam Horcica
 */
public class HTML {
	
	public static String strip(String text){
		return HTMLEncode.encode(text);
	}
	
}
