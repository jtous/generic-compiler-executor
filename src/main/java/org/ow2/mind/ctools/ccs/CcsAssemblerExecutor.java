package org.ow2.mind.ctools.ccs;

import java.util.ArrayList;
import java.util.List;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.AssemblerExecutor;
import org.ow2.mind.ctools.AssemblerExecutorLauncher;

public class CcsAssemblerExecutor extends org.ow2.mind.ctools.AbstractExecutor  implements AssemblerExecutor {
	public void assemble(CommandLine cmdLine) {
		List<String> cmd = new ArrayList<String>();
		if (AssemblerExecutorLauncher.executableOpt.getValue(cmdLine)==null) {
			cmd.add("cl470");
		} else {
			cmd.add(AssemblerExecutorLauncher.executableOpt.getValue(cmdLine));
		}
		cmd.add("-c");
		cmd.add(AssemblerExecutorLauncher.inputFileOpt.getValue(cmdLine));
		if (AssemblerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			cmd.add( AssemblerExecutorLauncher.flagsOpt.getValue(cmdLine) );
		if (AssemblerExecutorLauncher.defineOpt.getValue(cmdLine)!=null) {
			for (String def : AssemblerExecutorLauncher.defineOpt.getValue(cmdLine).split(" ")) {
				cmd.add( "-D" + def );
			}
		}
		if (AssemblerExecutorLauncher.includeDirOpt.getValue(cmdLine)!=null) {
			for (String dir : AssemblerExecutorLauncher.includeDirOpt.getValue(cmdLine).split(" ") ){
				cmd.add( "-I" + dir );
			}
		}
		if (AssemblerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null) {
			for (String inc : AssemblerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine).split(" ") ){
				cmd.add("--include");
				cmd.add( inc );
			}
		}
		cmd.add("-o");
		cmd.add(AssemblerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		String[] strs = (String[]) cmd.toArray(new String[0]);
		exec(strs);	
	}
}
