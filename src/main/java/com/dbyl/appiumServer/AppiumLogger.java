package main.java.com.dbyl.appiumServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This Class is for appium logger
 * 
 * @author Young
 */
public class AppiumLogger
{
    private final Class<?> clazz;
    private Logger         logger;

    /**
     * @param clazz
     */
    public AppiumLogger(Class<?> clazz)
    {
        this.clazz = clazz;
        this.logger = LogManager.getLogger(this.clazz);
    }

    /**
     * @author Young
     * @param message
     */
    public void info(String message)
    {
        logger.info(clazz.getCanonicalName() + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void debug(String message)
    {
        logger.debug(clazz.getCanonicalName() + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void error(String message)
    {
        logger.error(clazz.getCanonicalName() + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void trace(String message)
    {
        logger.trace(clazz.getCanonicalName() + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void warn(String message)
    {
        logger.warn(clazz.getCanonicalName() + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void fatal(String message)
    {
        logger.fatal(clazz.getCanonicalName() + ": " + message);
    }
}
