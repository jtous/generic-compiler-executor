package org.ow2.mind.ctools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AbstractExecutor {
	protected int exec(String[] strs) {
		try {
			String s = null;
			Process p = Runtime.getRuntime().exec(strs);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			// read the output from the command
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}
			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) {
				System.err.println(s);
			}
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				System.exit(-1);				
			}
			return p.exitValue();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		System.exit(-1);
		return -1; //should never be reached
	}
}
