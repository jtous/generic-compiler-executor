package org.ow2.mind.ctools.ccs;

import java.util.ArrayList;
import java.util.List;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.LinkerExecutor;
import org.ow2.mind.ctools.LinkerExecutorLauncher;

public class CcsLinkerExecutor extends org.ow2.mind.ctools.AbstractExecutor implements LinkerExecutor {
	public void link(CommandLine cmdLine) {
		List<String> cmd = new ArrayList<String>();
		if (LinkerExecutorLauncher.executableOpt.getValue(cmdLine)==null) {
			cmd.add("lnk470");
		} else {
			cmd.add(LinkerExecutorLauncher.executableOpt.getValue(cmdLine));
		}
		if (LinkerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null) {
			for (String f : LinkerExecutorLauncher.flagsOpt.getValue(cmdLine).split(" ")){
				cmd.add( f );
			}
		}
		for (String in : LinkerExecutorLauncher.inputFileOpt.getValue(cmdLine).split(" ") ){
			cmd.add( in );
		}
		if (LinkerExecutorLauncher.scriptOpt.getValue(cmdLine)!=null) {
			cmd.add("-T");
			cmd.add( LinkerExecutorLauncher.scriptOpt.getValue(cmdLine) );
		}
		cmd.add("-o");
		cmd.add(LinkerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		String[] strs = (String[]) cmd.toArray(new String[0]);
		for (String s : strs)
			System.out.print(s + " ");
		exec(strs);	
	}
}
