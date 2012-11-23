package org.ow2.mind.ctools.gnu;

import org.ow2.mind.ctools.LinkerExecutorLauncher;
import org.ow2.mind.ctools.gnu.GnuLinkerExecutor;

public class GnuLinkerExecutorLauncher {
	public static void main(String[] args) {
		LinkerExecutorLauncher launch = new LinkerExecutorLauncher();
		launch.executor = new GnuLinkerExecutor();
		launch.run(args);
	}
}
