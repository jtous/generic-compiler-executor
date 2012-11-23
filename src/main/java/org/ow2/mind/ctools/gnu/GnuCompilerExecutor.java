package org.ow2.mind.ctools.gnu;

import java.util.ArrayList;
import java.util.List;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.CompilerExecutor;
import org.ow2.mind.ctools.CompilerExecutorLauncher;

public class GnuCompilerExecutor extends org.ow2.mind.ctools.AbstractExecutor implements CompilerExecutor {
	public void preprocess(CommandLine cmdLine) {
		List<String> cmd = new ArrayList<String>();
		if (CompilerExecutorLauncher.executableOpt.getValue(cmdLine)==null) {
			cmd.add("gcc");
		} else {
			cmd.add(CompilerExecutorLauncher.executableOpt.getValue(cmdLine));
		}
		cmd.add("-E");
		cmd.add(CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine));
		if (CompilerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			cmd.add( CompilerExecutorLauncher.flagsOpt.getValue(cmdLine) );
		if (CompilerExecutorLauncher.defineOpt.getValue(cmdLine)!=null) {
			for (String def : CompilerExecutorLauncher.defineOpt.getValue(cmdLine).split(" ")) {
				cmd.add( "-D" + def );
			}
		}
		if (CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine)!=null) {
			for (String dir : CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine).split(" ") ){
				cmd.add( "-I" + dir );
			}
		}
		if (CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null) {
			for (String inc : CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine).split(" ") ){
				cmd.add("--include");
				cmd.add( inc );
			}
		}
		if (CompilerExecutorLauncher.dependencyFileOpt.getValue(cmdLine)!=null) {
			cmd.add("-MMD");
			cmd.add("-MF");
			cmd.add(CompilerExecutorLauncher.dependencyFileOpt.getValue(cmdLine));
			cmd.add("-MT");
			cmd.add(CompilerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		}
		cmd.add("-o");
		cmd.add(CompilerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		String[] strs = (String[]) cmd.toArray(new String[cmd.size()]);
		System.exit(exec(strs));
	}

	public void compile(CommandLine cmdLine) {
		List<String> cmd = new ArrayList<String>();
		if (CompilerExecutorLauncher.executableOpt.getValue(cmdLine)==null) {
			cmd.add("gcc");
		} else {
			cmd.add(CompilerExecutorLauncher.executableOpt.getValue(cmdLine));
		}
		cmd.add("-c");
		cmd.add(CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine));
		if (CompilerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			cmd.add( CompilerExecutorLauncher.flagsOpt.getValue(cmdLine) );
		if (CompilerExecutorLauncher.defineOpt.getValue(cmdLine)!=null) {
			for (String def : CompilerExecutorLauncher.defineOpt.getValue(cmdLine).split(" ")) {
				cmd.add( "-D" + def );
			}
		}
		if (CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine)!=null) {
			for (String dir : CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine).split(" ") ){
				cmd.add( "-I" + dir );
			}
		}
		if (CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null) {
			for (String inc : CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine).split(" ") ){
				cmd.add("--include");
				cmd.add( inc );
			}
		}
		cmd.add("-o");
		cmd.add(CompilerExecutorLauncher.outputFileOpt.getValue(cmdLine));
		String[] strs = (String[]) cmd.toArray(new String[0]);
		System.exit(exec(strs));	
	}
}
