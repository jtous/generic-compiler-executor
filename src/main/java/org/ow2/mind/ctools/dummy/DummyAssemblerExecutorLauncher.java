package org.ow2.mind.ctools.dummy;

import org.ow2.mind.ctools.AssemblerExecutorLauncher;

public class DummyAssemblerExecutorLauncher {
	public static void main(String[] args) {
		AssemblerExecutorLauncher launch = new AssemblerExecutorLauncher();
		launch.executor = new DummyAssemblerExecutor();
		launch.run(args);
	}
}
