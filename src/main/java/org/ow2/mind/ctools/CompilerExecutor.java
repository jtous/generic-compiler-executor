package org.ow2.mind.ctools;

import org.ow2.mind.cli.CommandLine;

public interface CompilerExecutor {
	void preprocess(CommandLine cmdLine);
	void compile(CommandLine cmdLine);
}
