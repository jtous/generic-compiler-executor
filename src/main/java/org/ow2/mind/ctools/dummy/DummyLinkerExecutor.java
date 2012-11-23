package org.ow2.mind.ctools.dummy;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.LinkerExecutor;
import org.ow2.mind.ctools.LinkerExecutorLauncher;

public class DummyLinkerExecutor implements LinkerExecutor {
	
	public void link(CommandLine cmdLine) {
		StringBuilder str = new StringBuilder();
		if (LinkerExecutorLauncher.executableOpt.getValue(cmdLine)!=null) {
			str.append("Using " + LinkerExecutorLauncher.executableOpt.getValue(cmdLine) + " : ");
		} else {
			LinkerExecutorLauncher.printExecutableHelp(System.out);			
		}
		str.append("Linking ");
		for (String in : LinkerExecutorLauncher.inputFileOpt.getValue(cmdLine).split(" ")) {
		str.append(in + " ");
		}
		str.append(" to " + LinkerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		if (LinkerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			str.append(", using flags : \"" + LinkerExecutorLauncher.flagsOpt.getValue(cmdLine) + "\"");
		if (LinkerExecutorLauncher.scriptOpt.getValue(cmdLine)!=null)
			str.append(", using script : \"" + LinkerExecutorLauncher.scriptOpt.getValue(cmdLine) + "\"");
		System.out.println(str + ".");
	}
}
