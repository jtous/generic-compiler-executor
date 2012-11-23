package org.ow2.mind.ctools.ccs;

import org.ow2.mind.ctools.CompilerExecutorLauncher;
import org.ow2.mind.ctools.ccs.CcsCompilerExecutor;

public class CcsCompilerExecutorLauncher {
	public static void main(String[] args) {
		CompilerExecutorLauncher launch = new CompilerExecutorLauncher();
		launch.executor = new CcsCompilerExecutor();
		launch.run(args);
	}
}
