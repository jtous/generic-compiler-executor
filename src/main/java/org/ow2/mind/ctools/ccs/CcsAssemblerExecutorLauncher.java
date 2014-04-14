package org.ow2.mind.ctools.ccs;

import org.ow2.mind.ctools.AssemblerExecutorLauncher;

public class CcsAssemblerExecutorLauncher {
	public static void main(String[] args) {
		AssemblerExecutorLauncher launch = new AssemblerExecutorLauncher();
		launch.executor = new CcsAssemblerExecutor("cl2000");
		launch.run(args);
	}
}
