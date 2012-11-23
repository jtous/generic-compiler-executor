package org.ow2.mind.ctools.dummy;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.AssemblerExecutor;
import org.ow2.mind.ctools.AssemblerExecutorLauncher;

public class DummyAssemblerExecutor implements AssemblerExecutor {
	
	public void assemble(CommandLine cmdLine) {
		StringBuilder str = new StringBuilder();
		if (AssemblerExecutorLauncher.executableOpt.getValue(cmdLine)!=null) {
			str.append("Using " + AssemblerExecutorLauncher.executableOpt.getValue(cmdLine) + " : ");
		} else {
			AssemblerExecutorLauncher.printExecutableHelp(System.out);			
		}
		str.append("Assembling " + AssemblerExecutorLauncher.inputFileOpt.getValue(cmdLine) + " to " + AssemblerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		if (AssemblerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			str.append(", using flags : \"" + AssemblerExecutorLauncher.flagsOpt.getValue(cmdLine) + "\"");
		if (AssemblerExecutorLauncher.defineOpt.getValue(cmdLine)!=null)
			str.append(", using defined values : \"" + AssemblerExecutorLauncher.defineOpt.getValue(cmdLine) + "\"");
		if (AssemblerExecutorLauncher.includeDirOpt.getValue(cmdLine)!=null)
			str.append(", using include directories : \"" + AssemblerExecutorLauncher.includeDirOpt.getValue(cmdLine) + "\"");
		if (AssemblerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null)
			str.append(", including files : \"" + AssemblerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine) + "\"");
		System.out.println(str + ".");	
	}
}
