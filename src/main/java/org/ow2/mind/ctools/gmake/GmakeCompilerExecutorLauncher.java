package org.ow2.mind.ctools.gmake;

import org.ow2.mind.ctools.CompilerExecutorLauncher;
import org.ow2.mind.ctools.gmake.GmakeCompilerExecutor;

public class GmakeCompilerExecutorLauncher {
	public static void main(String[] args) {
		CompilerExecutorLauncher launch = new CompilerExecutorLauncher();
		launch.executor = new GmakeCompilerExecutor();
		launch.run(args);
	}
}
