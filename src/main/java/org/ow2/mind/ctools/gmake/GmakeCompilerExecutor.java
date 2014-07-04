package org.ow2.mind.ctools.gmake;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.CompilerExecutor;
import org.ow2.mind.ctools.CompilerExecutorLauncher;

public class GmakeCompilerExecutor extends org.ow2.mind.ctools.AbstractExecutor implements CompilerExecutor {
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
		String target=CompilerExecutorLauncher.outputFileOpt.getValue(cmdLine);
		String dependencies="";
		String source=CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine);
		String compiler="$(CC)";
		if (CompilerExecutorLauncher.executableOpt.getValue(cmdLine)!=null) {
			compiler = CompilerExecutorLauncher.executableOpt.getValue(cmdLine);
		}
		String cflags="";
		if (CompilerExecutorLauncher.flagsOpt.getValue(cmdLine)!=null)
			cflags=CompilerExecutorLauncher.flagsOpt.getValue(cmdLine);
		if (CompilerExecutorLauncher.defineOpt.getValue(cmdLine)!=null) {
			for (String def : CompilerExecutorLauncher.defineOpt.getValue(cmdLine).split(" ")) {
				cflags = cflags + " -D" + def + " ";
			}
		}
		if (CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine)!=null) {
			for (String dir : CompilerExecutorLauncher.includeDirOpt.getValue(cmdLine).split(" ") ){
				cflags = cflags + " -I" + dir + " ";
			}
		}
		if (CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null) {
			for (String inc : CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine).split(" ") ){
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
