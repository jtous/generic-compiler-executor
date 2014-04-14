package org.ow2.mind.ctools.ccs;

import org.ow2.mind.ctools.LinkerExecutorLauncher;

public class CcsLinkerExecutorLauncher {
	public static void main(String[] args) {
		LinkerExecutorLauncher launch = new LinkerExecutorLauncher();
		launch.executor = new CcsLinkerExecutor("cl2000");		
		launch.run(args);
	}
}
