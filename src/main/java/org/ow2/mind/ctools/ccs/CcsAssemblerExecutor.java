package org.ow2.mind.ctools.ccs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.ow2.mind.adl.jtb.syntaxtree.Path;
import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.AssemblerExecutor;
import org.ow2.mind.ctools.AssemblerExecutorLauncher;

public class CcsAssemblerExecutor extends org.ow2.mind.ctools.AbstractExecutor  implements AssemblerExecutor {
	public String COMMAND;
	public CcsAssemblerExecutor(String cmd) {
		COMMAND = cmd;
	}
	public void assemble(CommandLine cmdLine) {
		List<String> cmd = new ArrayList<String>();
		if (AssemblerExecutorLauncher.executableOpt.getValue(cmdLine)==null) {
			cmd.add(COMMAND);
		} else {
			cmd.add(AssemblerExecutorLauncher.executableOpt.getValue(cmdLine));
		}
		cmd.add("-c");
		cmd.add("-fa=" + AssemblerExecutorLauncher.inputFileOpt.getValue(cmdLine));
		if (AssemblerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			for (String def : AssemblerExecutorLauncher.flagsOpt.getValue(cmdLine).split(" ")){
				cmd.add( def);
			}
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
		
		String[] strs = (String[]) cmd.toArray(new String[0]);
		for (String str : strs) System.out.print(str + " ");
		System.out.println();
//		System.exit(exec(strs));
		exec(strs);
		
		String inName = AssemblerExecutorLauncher.inputFileOpt.getValue(cmdLine);
		inName = inName.substring(inName.lastIndexOf("/")+1,inName.lastIndexOf("."));
		inName = inName + ".obj";
		String[] move = new String[3];
		move[0] = "mv";
		move[1] = inName;
		move[2] = AssemblerExecutorLauncher.outputFileOpt.getValue(cmdLine);
		exec(move);
		for (String str : move) System.out.print(str + " ");

	}
}
