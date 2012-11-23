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

public class CompilerExecutorLauncher {
	static Options       options = new Options();
	static final String ID_PREFIX                  = "org.ow2.mind.ctools.";

	public static final CmdFlag       helpOpt                    = new CmdFlag(
			ID_PREFIX
			+ "Help",
			null, "help",
			"Print this help and exit");

	public static final CmdFlag       preprocessOpt                 = new CmdFlag(
			ID_PREFIX
			+ "Preprocess",
			null, "preprocess",
			"run as preprocessor");

	public static final CmdFlag       compileOpt                = new CmdFlag(
			ID_PREFIX
			+ "Compile",
			null, "compile",
			"run as compiler");

	public static CmdArgument       executableOpt;
	
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

	public static final CmdArgument       dependencyFileOpt                = new CmdArgument(
			ID_PREFIX
			+ "DependencyFile",
			null, "dependency-file",
			"dependency-file created while preprocessing","DEPFILE",null,false);
	public static CommandLine cmdLine;
	
	public CompilerExecutor executor;
	/**
	 * @param args
	 * @throws InvalidCommandLineException 
	 */
	public void run(String[] args) {
		int dot=args[0].lastIndexOf(java.io.File.pathSeparatorChar);
		if (dot == -1) {
			executableOpt = new CmdArgument(
			ID_PREFIX
			+ "Executable",
			null, "executable",
			"The path of the compiler executable, or simply its name if it ","EXECUTABLE",null,false);
		} else {
			executableOpt = new CmdArgument(
			ID_PREFIX
			+ "Executable",
			args[0].substring(+1,args[0].length()), "executable",
			"The path of the compiler executable, or simply its name if it ","EXECUTABLE",null,false);
		}
		
		options.addOptions(executableOpt,helpOpt,preprocessOpt,compileOpt,inputFileOpt,outputFileOpt,flagsOpt,defineOpt,includeDirOpt,preIncludeFileOpt,dependencyFileOpt);
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
		// If two actions are specified exit.
		if (compileOpt.isPresent(cmdLine) && preprocessOpt.isPresent(cmdLine)) {
			printActionHelp(System.out);
			System.exit(-1);
		}
		// If no action are specified exit.
		if (!compileOpt.isPresent(cmdLine) && !preprocessOpt.isPresent(cmdLine)) {
			printActionHelp(System.out);
			System.exit(-1);
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
		
		if (preprocessOpt.isPresent(cmdLine)) {
			executor.preprocess(cmdLine);
			System.exit(0);
		}
	
		if (dependencyFileOpt.isPresent(cmdLine)) {
			printDependencyWhileCompilingHelp(System.out);
			System.exit(-1);		
		}
		if (compileOpt.isPresent(cmdLine)) {
			executor.compile(cmdLine);
			System.exit(0);		
		}
	}

	private static void printDependencyWhileCompilingHelp(PrintStream ps) {
		ps.println("Option " + dependencyFileOpt.getPrototype() + " is not compatible with " + compileOpt.getPrototype() );
		ps.println();
		printHelp(ps);
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
	
	private static void printActionHelp(final PrintStream ps) {
		ps.println("One and only one of --preprocess and --compile option can be specified !");
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
		ps.println("Usage: " + getProgramName()
				+ " ACTION " + inputFileOpt.getPrototype() + outputFileOpt.getPrototype() + " [OPTIONS]+");
		ps.println("  where <ACTION> is either " + preprocessOpt.getPrototype() + " or " + compileOpt.getPrototype());
		ps.println("  and <" + inputFileOpt.getArgDescription() + "> is the name of the file to" + " be compiled, ");
		ps.println("  and <" + outputFileOpt.getArgDescription() + "> is the name of the output file to be created.");
	}

	private static String getProgramName() {
		return "generic-compiler";
	}
}