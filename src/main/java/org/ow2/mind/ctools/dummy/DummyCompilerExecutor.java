package org.ow2.mind.ctools.dummy;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.CompilerExecutor;
import org.ow2.mind.ctools.CompilerExecutorLauncher;

public class DummyCompilerExecutor implements CompilerExecutor {
	
	public void preprocess(CommandLine cmdLine) {
		StringBuilder str = new StringBuilder();
		if (CompilerExecutorLauncher.executableOpt.getValue(cmdLine)!=null) {
			str.append("Using " + CompilerExecutorLauncher.executableOpt.getValue(cmdLine) + " : ");
		} else {
			CompilerExecutorLauncher.printExecutableHelp(System.out);			
		}
		str.append("Preprocessing " + CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine) + " to " + CompilerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		if (CompilerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			str.append(", using flags : \"" + CompilerExecutorLauncher.flagsOpt.getValue(cmdLine) + "\"");
		if (CompilerExecutorLauncher.defineOpt.getValue(cmdLine)!=null)
			str.append(", using defined values : \"" + CompilerExecutorLauncher.defineOpt.getValue(cmdLine) + "\"");
		if (CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine)!=null)
			str.append(", using include directories : \"" + CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine) + "\"");
		if (CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null)
			str.append(", including files : \"" + CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine) + "\"");
		if (CompilerExecutorLauncher.dependencyFileOpt.getValue(cmdLine)!=null) {
			str.append(", dependency dumped in : \"" + CompilerExecutorLauncher.dependencyFileOpt.getValue(cmdLine) + "\"");		
		}
		System.out.println(str + ".");	
	}

	public void compile(CommandLine cmdLine) {
		StringBuilder str = new StringBuilder();		
		if (CompilerExecutorLauncher.executableOpt.getValue(cmdLine)!=null) {
			str.append("Using " + CompilerExecutorLauncher.executableOpt.getValue(cmdLine) + " : ");
		} else {
			CompilerExecutorLauncher.printExecutableHelp(System.out);			
		}
		str.append("Compiling " + CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine) + " to " + CompilerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		if (CompilerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			str.append(", using flags : \"" + CompilerExecutorLauncher.flagsOpt.getValue(cmdLine) + "\"");
		if (CompilerExecutorLauncher.defineOpt.getValue(cmdLine)!=null)
			str.append(", using defined values : \"" + CompilerExecutorLauncher.defineOpt.getValue(cmdLine) + "\"");
		if (CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine)!=null)
			str.append(", using include directories : \"" + CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine) + "\"");
		if (CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null)
			str.append(", including files : \"" + CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine) + "\"");
		System.out.println(str + ".");
	}
}
