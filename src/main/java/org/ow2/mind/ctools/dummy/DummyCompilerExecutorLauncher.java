package org.ow2.mind.ctools.dummy;

import org.ow2.mind.ctools.CompilerExecutorLauncher;

public class DummyCompilerExecutorLauncher {
	public static void main(String[] args) {
		CompilerExecutorLauncher launch = new CompilerExecutorLauncher();
		launch.executor = new DummyCompilerExecutor();
		launch.run(args);
	}
}
