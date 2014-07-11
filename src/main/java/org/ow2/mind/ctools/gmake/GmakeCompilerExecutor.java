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
		try {
			String target=CompilerExecutorLauncher.outputFileOpt.getValue(cmdLine);
			String dependencies=CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine) + " ";
			String source=CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine) + ".concatenated.c";

			BufferedWriter concatenated = new BufferedWriter(new FileWriter(source));

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
			cflags = cflags + "-I. ";
			if (CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null) {
				for (String inc : CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine).split(" ") ){
					//cflags = cflags + " --include " + inc + " ";			
					concatenated.append("#include \"" + inc.replace("\\", "/") +"\"");
					concatenated.newLine();
					dependencies = dependencies + " " + inc;
				}
			}
			concatenated.append("#include \"" + CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine).replace("\\", "/") +"\"");
			concatenated.newLine();
			concatenated.newLine();
			concatenated.close();
			
			BufferedWriter makefile = new BufferedWriter(new FileWriter("tmp.make",true));
			makefile.append(target + " : " + source + " " + dependencies);
			makefile.newLine();
			makefile.append("\t" + compiler + " -c " + source + " -o $@ " + cflags);
			makefile.newLine();
			makefile.newLine();
			makefile.close();
			//		System.out.println("#makefile#" + target + " : " + source + dependencies);
			//		System.out.println("#makefile#\t" + compiler + " -c " + source + " -o $@ " + cflags);
			//		System.out.println();

			System.exit(0);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}