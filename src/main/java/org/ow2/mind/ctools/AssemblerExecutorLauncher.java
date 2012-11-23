package org.ow2.mind.ctools;
import java.io.PrintStream;
import java.io.File;

import org.ow2.mind.cli.CmdAppendOption;
import org.ow2.mind.cli.CmdArgument;
import org.ow2.mind.cli.CmdFlag;
import org.ow2.mind.cli.CmdOption;
import org.ow2.mind.cli.CommandLine;
import org.ow2.mind.cli.InvalidCommandLineException;
import org.ow2.mind.cli.Options;

public class AssemblerExecutorLauncher {
	static Options       options = new Options();
	static final String ID_PREFIX                  = "org.ow2.mind.ctools.";

	public static final CmdFlag       helpOpt                    = new CmdFlag(
			ID_PREFIX
			+ "Help",
			null, "help",
			"Print this help and exit");
	public static final CmdArgument       executableOpt            = new CmdArgument(
			ID_PREFIX
			+ "Executable",
			null, "executable",
			"The path of the compiler executable, or simply its name if it ","EXECUTABLE",null,false);

	public static final CmdArgument       inputFileOpt            = new CmdArgument(
			ID_PREFIX
			+ "InputFile",
			null, "input-file",
			"The file to preprocess or compile","INPUT_FILE",null,false);
	
	public static final CmdArgument       outputFileOpt                = new CmdArgument(
			ID_PREFIX
			+ "OutputFile",
			null, "output-file",
			"The compiled or preprocessed file","OUTPUT_FILE",null,false);

	public static final CmdAppendOption       flagsOpt                = new CmdAppendOption(
			ID_PREFIX
			+ "CompilationFlags",
			null, "compilation-flags",
			"Add some compilation flags","CFLAGS");

	public static final CmdAppendOption       defineOpt                = new CmdAppendOption(
			ID_PREFIX
			+ "Define",
			null, "define",
			"Add a defined value","DEFVALUE");

	public static final CmdAppendOption       includeDirOpt                = new CmdAppendOption(
			ID_PREFIX
			+ "IncludeDir",
			null, "include-dir",
			"Add an include directory","INCDIR");

	public static final CmdAppendOption       preIncludeFileOpt                = new CmdAppendOption(
			ID_PREFIX
			+ "IncludeFile",
			null, "include-file",
			"Pre-include a file","INCFILE");

	public static CommandLine cmdLine;
	
	public AssemblerExecutor executor;
	/**
	 * @param args
	 * @throws InvalidCommandLineException 
	 */
	public void run(String[] args) {
		
		options.addOptions(executableOpt,helpOpt,inputFileOpt,outputFileOpt,flagsOpt,defineOpt,includeDirOpt,preIncludeFileOpt);
		try {
			cmdLine = CommandLine.parseArgs(options, false, args);
		} catch (InvalidCommandLineException e) {
			System.out.println(e.getMessage());
			System.out.println();
			printHelp(System.out);
			System.exit(-1);
		}
		// If help is asked, print it and exit.
		if (helpOpt.isPresent(cmdLine)) {
			printHelp(System.out);
			System.exit(0);
		}
		if (!inputFileOpt.isPresent(cmdLine)) {
			printInputHelp(System.out);
			System.exit(-1);
		}
		if (!outputFileOpt.isPresent(cmdLine)) {
			printOutputHelp(System.out);
			System.exit(-1);
		}
		if ( !(new File(inputFileOpt.getValue(cmdLine))).isFile() ) {
			printMissingInputFileHelp(System.out);
			System.exit(-1);	
		}
	}

	private static void printMissingInputFileHelp(PrintStream ps) {
		ps.println("Specifyed "  + inputFileOpt.getArgDescription() + " : \"" + inputFileOpt.getValue(cmdLine) + "\" is not valid !");
		ps.println();
		printHelp(ps);
	}

	public static void printExecutableHelp(final PrintStream ps) {
		ps.println("No " + executableOpt.getArgDescription() + "defined. Please use " + executableOpt.getPrototype() + " option !");
		ps.println();
		printHelp(ps);
	}
	
	private static void printInputHelp(PrintStream ps) {
		ps.println("No " + inputFileOpt.getArgDescription() + " specified !");
		ps.println("Please provide an " + inputFileOpt.getArgDescription() + " by using the option : " + inputFileOpt.getPrototype() );
		ps.println();
		printHelp(ps);
	}

	private static void printOutputHelp(PrintStream ps) {
		ps.println("No " + outputFileOpt.getArgDescription() + " specified !");
		ps.println("Please provide an " + outputFileOpt.getArgDescription() + " by using the option : " + outputFileOpt.getPrototype() );
		ps.println();
		printHelp(ps);
	}
	
	private static void printHelp(final PrintStream ps) {
		printUsage(ps);
		ps.println();
		ps.println("Available options are :");
		int maxCol = 0;

		for (final CmdOption opt : options.getOptions()) {
			final int col = 2 + opt.getPrototype().length();
			if (col > maxCol) maxCol = col;
		}
		for (final CmdOption opt : options.getOptions()) {
			final StringBuffer sb = new StringBuffer("  ");
			sb.append(opt.getPrototype());
			while (sb.length() < maxCol)
				sb.append(' ');
			sb.append("  ").append(opt.getDescription());
			ps.println(sb);
		}
	}  
	private static void printUsage(final PrintStream ps) {
		ps.println("Usage: " + getProgramName() + " " + inputFileOpt.getPrototype()  + " " + outputFileOpt.getPrototype() + " [OPTIONS]+");
		ps.println("  where <" + inputFileOpt.getArgDescription() + "> is the name of the file to" + " be compiled, ");
		ps.println("  and <" + outputFileOpt.getArgDescription() + "> is the name of the output file to be created.");
	}

	private static String getProgramName() {
		return "generic-assembler";
	}
}