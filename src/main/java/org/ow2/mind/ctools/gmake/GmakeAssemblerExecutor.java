package org.ow2.mind.ctools.gmake;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.AssemblerExecutor;
import org.ow2.mind.ctools.AssemblerExecutorLauncher;

public class GmakeAssemblerExecutor extends org.ow2.mind.ctools.AbstractExecutor  implements AssemblerExecutor {
	public void assemble(CommandLine cmdLine) {
		String target=AssemblerExecutorLauncher.outputFileOpt.getValue(cmdLine);
		String source=AssemblerExecutorLauncher.inputFileOpt.getValue(cmdLine);
		String dependencies="";
		String compiler="$(AS)";
		if (AssemblerExecutorLauncher.executableOpt.getValue(cmdLine)!=null) {
			compiler = AssemblerExecutorLauncher.executableOpt.getValue(cmdLine);
		}
		String cflags="";
		if (AssemblerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			cflags=AssemblerExecutorLauncher.flagsOpt.getValue(cmdLine);
		if (AssemblerExecutorLauncher.defineOpt.getValue(cmdLine)!=null) {
			for (String def : AssemblerExecutorLauncher.defineOpt.getValue(cmdLine).split(" ")) {
				cflags = cflags + " -D" + def + " ";
			}
		}
		if (AssemblerExecutorLauncher.includeDirOpt.getValue(cmdLine)!=null) {
			for (String dir : AssemblerExecutorLauncher.includeDirOpt.getValue(cmdLine).split(" ") ){
				cflags = cflags + "-I" + dir + " ";
			}
		}
		if (AssemblerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null) {
			for (String inc : AssemblerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine).split(" ") ){
				cflags = cflags + " --include " + inc + " ";
				dependencies = dependencies + " " + inc;
			}
		}
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter("tmp.make",true));

		output.append(target + " : " + source + dependencies);
		output.newLine();
		output.append("\t" + compiler + " -c " + source + " -o $@ " + cflags);
		output.newLine();
		output.newLine();
		output.close();
//		System.out.println("#makefile#" + target + " : " + source + dependencies);
//		System.out.println("#makefile#\t" + compiler + " -c " + source + " -o $@ " + cflags);
//		System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);	
	}
}
