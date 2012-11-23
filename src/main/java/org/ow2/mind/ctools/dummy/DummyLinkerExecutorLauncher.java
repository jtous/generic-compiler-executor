package org.ow2.mind.ctools.dummy;

import org.ow2.mind.ctools.LinkerExecutorLauncher;

public class DummyLinkerExecutorLauncher {
	public static void main(String[] args) {
		LinkerExecutorLauncher launch = new LinkerExecutorLauncher();
		launch.executor = new DummyLinkerExecutor();
		launch.run(args);
	}
}
