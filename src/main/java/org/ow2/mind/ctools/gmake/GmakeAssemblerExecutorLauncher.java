package org.ow2.mind.ctools.gmake;

import org.ow2.mind.ctools.AssemblerExecutorLauncher;
import org.ow2.mind.ctools.gmake.GmakeAssemblerExecutor;

public class GmakeAssemblerExecutorLauncher {
	public static void main(String[] args) {
		AssemblerExecutorLauncher launch = new AssemblerExecutorLauncher();
		launch.executor = new GmakeAssemblerExecutor();
		launch.run(args);
	}
}
