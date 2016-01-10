package main.java.com.dbyl.appiumServer;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;

/**
 * This Utils if for execute the command line
 * 
 * @version V1.0
 * @see commons-exec
 * @author Young
 */
public class ExectorUtils
{
    /**
     * This method is for execute the command without track, if return 1 means
     * run successful
     * 
     * @author Young
     * @param command
     * @return
     * @throws ExecuteException
     * @throws IOException
     */
    public static int runWithoutWatchDog(String command) throws ExecuteException, IOException
    {
        CommandLine cmdLine = CommandLine.parse(command);
        DefaultExecutor executor = new DefaultExecutor();
        int exitValue = executor.execute(cmdLine);
        return exitValue;
    }

    /**
     * @author Young
     * @param command
     * @return
     * @throws ExecuteException
     * @throws IOException
     */
    public static int runWithWatchDog(String command) throws ExecuteException, IOException
    {
        CommandLine cmdLine = CommandLine.parse(command);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(6000);
        executor.setWatchdog(watchdog);
        int exitValue = executor.execute(cmdLine);
        return exitValue;
    }

    /**
     * @author Young
     * @param commandLineBean
     * @return
     * @throws ExecuteException
     * @throws IOException
     */
    public static int runWithWatchDog(CommandLineBean commandLineBean) throws ExecuteException, IOException
    {
        CommandLine cmdLine = new CommandLine(commandLineBean.getCommandLine());
        cmdLine.setSubstitutionMap(commandLineBean.getArgumentMap());
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(6000);
        executor.setWatchdog(watchdog);
        int exitValue = executor.execute(cmdLine);
        return exitValue;
    }
}
