package org.ow2.mind.ctools.ccs;

import org.ow2.mind.ctools.AssemblerExecutorLauncher;
import org.ow2.mind.ctools.ccs.CcsAssemblerExecutor;

public class CcsAssemblerExecutorLauncher {
	public static void main(String[] args) {
		AssemblerExecutorLauncher launch = new AssemblerExecutorLauncher();
		launch.executor = new CcsAssemblerExecutor();
		launch.run(args);
	}
}
