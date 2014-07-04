package org.ow2.mind.ctools.gmake;

import org.ow2.mind.ctools.LinkerExecutorLauncher;
import org.ow2.mind.ctools.gmake.GmakeLinkerExecutor;

public class GmakeLinkerExecutorLauncher {
	public static void main(String[] args) {
		LinkerExecutorLauncher launch = new LinkerExecutorLauncher();
		launch.executor = new GmakeLinkerExecutor();
		launch.run(args);
	}
}
