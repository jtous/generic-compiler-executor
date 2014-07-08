package org.ow2.mind.ctools.gmakecopy;

import org.ow2.mind.ctools.CompilerExecutorLauncher;
import org.ow2.mind.ctools.gmakecopy.GmakeCopyCompilerExecutor;

public class GmakeCopyCompilerExecutorLauncher {
	public static void main(String[] args) {
		CompilerExecutorLauncher launch = new CompilerExecutorLauncher();
		launch.executor = new GmakeCopyCompilerExecutor();
		launch.run(args);
	}
}
