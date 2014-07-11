package org.ow2.mind.ctools.gmakecopy;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.ctools.CompilerExecutorLauncher;

public class GmakeCopyCompilerExecutor extends org.ow2.mind.ctools.gmake.GmakeCompilerExecutor {
	@Override
	public void preprocess(CommandLine cmdLine) {
		try {
			String target=CompilerExecutorLauncher.outputFileOpt.getValue(cmdLine);

			BufferedWriter concatenated = new BufferedWriter(new FileWriter(target));

			if (CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine)!=null) {
				for (String inc : CompilerExecutorLauncher.preIncludeFileOpt.getValue(cmdLine).split(" ") ){
					concatenated.append("#include \"" + inc.replace("\\", "/") +"\"");
					concatenated.newLine();
				}
			}
			concatenated.append("# 1 \"" + CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine).replace("\\", "/") + "\"");
			concatenated.newLine();
			
		     BufferedInputStream bin = new BufferedInputStream(new FileInputStream(CompilerExecutorLauncher.inputFileOpt.getValue(cmdLine)));
		     while (true) {
		         int datum = bin.read();
		         if (datum == -1)
		           break;
		         concatenated.write(datum);
		       }
			concatenated.newLine();
			concatenated.close();
			bin.close();
			
			System.exit(0);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}