package org.ow2.mind.ctools.gnu;

import org.ow2.mind.ctools.AssemblerExecutorLauncher;
import org.ow2.mind.ctools.gnu.GnuAssemblerExecutor;

public class GnuAssemblerExecutorLauncher {
	public static void main(String[] args) {
		AssemblerExecutorLauncher launch = new AssemblerExecutorLauncher();
		launch.executor = new GnuAssemblerExecutor();
		launch.run(args);
	}
}
