package org.ow2.mind.ctools.gnu;

import org.ow2.mind.ctools.CompilerExecutorLauncher;
import org.ow2.mind.ctools.gnu.GnuCompilerExecutor;

public class GnuCompilerExecutorLauncher {
	public static void main(String[] args) {
		CompilerExecutorLauncher launch = new CompilerExecutorLauncher();
		launch.executor = new GnuCompilerExecutor();
		launch.run(args);
	}
}
