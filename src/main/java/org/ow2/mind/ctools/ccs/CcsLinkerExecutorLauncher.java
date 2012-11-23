package org.ow2.mind.ctools.ccs;

import org.ow2.mind.ctools.LinkerExecutorLauncher;
import org.ow2.mind.ctools.ccs.CcsLinkerExecutor;

public class CcsLinkerExecutorLauncher {
	public static void main(String[] args) {
		LinkerExecutorLauncher launch = new LinkerExecutorLauncher();
		launch.executor = new CcsLinkerExecutor();
		launch.run(args);
	}
}
