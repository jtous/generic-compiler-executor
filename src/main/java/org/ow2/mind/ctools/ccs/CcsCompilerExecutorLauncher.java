package org.ow2.mind.ctools.ccs;

import org.ow2.mind.ctools.CompilerExecutorLauncher;

public class CcsCompilerExecutorLauncher {
	public static void main(String[] args) {
		CompilerExecutorLauncher launch = new CompilerExecutorLauncher();
		launch.executor = new CcsCompilerExecutor("cl2000");
		launch.run(args);
	}
}
