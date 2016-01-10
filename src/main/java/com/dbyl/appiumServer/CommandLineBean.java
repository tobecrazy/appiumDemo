package main.java.com.dbyl.appiumServer;

import java.util.HashMap;
import java.util.Map;

public class CommandLineBean
{
    private String commandLine;
    private Map    argumentMap = new HashMap();

    /**
     * @author Young
     * @param cmd
     */
    public CommandLineBean(String cmd)
    {
        this.commandLine = cmd;
    }

    public CommandLineBean(String cmd, HashMap<Object, Object> map)
    {
        this.commandLine = cmd;
        this.argumentMap = map;
    }

    public String getCommandLine()
    {
        return commandLine;
    }

    public void setCommandLine(String commandLine)
    {
        this.commandLine = commandLine;
    }

    public Map getArgumentMap()
    {
        return argumentMap;
    }

    public void setArgumentMap(Map argumentMap)
    {
        this.argumentMap = argumentMap;
    }

}
