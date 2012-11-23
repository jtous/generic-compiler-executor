package org.ow2.mind.ctools.gnu;

import java.util.ArrayList;
import java.util.List;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.LinkerExecutor;
import org.ow2.mind.ctools.LinkerExecutorLauncher;

public class GnuLinkerExecutor extends org.ow2.mind.ctools.AbstractExecutor implements LinkerExecutor {
	public void link(CommandLine cmdLine) {
		List<String> cmd = new ArrayList<String>();
		if (LinkerExecutorLauncher.executableOpt.getValue(cmdLine)==null) {
			cmd.add("gcc");
		} else {
			cmd.add(LinkerExecutorLauncher.executableOpt.getValue(cmdLine));
		}
		for (String in : LinkerExecutorLauncher.inputFileOpt.getValue(cmdLine).split(" ") ){
			cmd.add( in );
		}
		if (LinkerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			cmd.add( LinkerExecutorLauncher.flagsOpt.getValue(cmdLine) );
		if (LinkerExecutorLauncher.scriptOpt.getValue(cmdLine)!=null) {
			cmd.add("-T");
			cmd.add( LinkerExecutorLauncher.scriptOpt.getValue(cmdLine) );
		}
		cmd.add("-o");
		cmd.add(LinkerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		String[] strs = (String[]) cmd.toArray(new String[0]);
		System.exit(exec(strs));	
	}
}
