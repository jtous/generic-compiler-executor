package org.ow2.mind.ctools.gmake;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.LinkerExecutor;
import org.ow2.mind.ctools.LinkerExecutorLauncher;

public class GmakeLinkerExecutor extends org.ow2.mind.ctools.AbstractExecutor implements LinkerExecutor {
	public void link(CommandLine cmdLine) {
		
		String target=LinkerExecutorLauncher.outputFileOpt.getValue(cmdLine);
		String dependencies=LinkerExecutorLauncher.inputFileOpt.getValue(cmdLine);
		String compiler="$(LD)";
		if (LinkerExecutorLauncher.executableOpt.getValue(cmdLine)!=null) {
			compiler = LinkerExecutorLauncher.executableOpt.getValue(cmdLine);
		}
		String cflags="";
		if (LinkerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			cflags=LinkerExecutorLauncher.flagsOpt.getValue(cmdLine);
		if (LinkerExecutorLauncher.scriptOpt.getValue(cmdLine)!=null) {
			cflags= cflags + " -T"+LinkerExecutorLauncher.scriptOpt.getValue(cmdLine);
		}
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter("tmp.make",true));

		output.append(target + " : " +  dependencies);
		output.newLine();
		output.append("\t" + compiler + " $^ -o $@ " + cflags);
		output.newLine();
		output.newLine();
		output.append("all : " + target);
		output.newLine();
		output.newLine();
		output.close();

		File oldfile =new File("tmp.make");
		File newfile =new File("generated.make");
 
		if (newfile.exists())
			newfile.delete();
		oldfile.renameTo(newfile);
			
		
//		System.out.println("#makefile#" + target + " : " +  dependencies);
//		System.out.println("#makefile#\t" + compiler + " $^ -o $@ " + cflags);
//		System.out.println();
//		System.out.println("#makefile#all : " + target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);	
	}
}
