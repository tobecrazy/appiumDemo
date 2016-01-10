package main.java.com.dbyl.appiumCore.utils;

/**
 * This class is for define Android or IOS WebElemnt
 * 
 * @author Young
 */
public class Locator
{
    private String element;

    /**
     * create a enum variable for By
     * 
     * @author Young
     */
    public enum ByType
    {
        by, xpath, accessibilityId, linkText, id, name, className, cssSelector, partialLinkText, tagName, androidUIAutomator, iOSUIAutomation
    }

    private ByType byType;

    /**
     * @author Young
     * @param element
     */
    public Locator(String element)
    {
        this.element = element;
        this.byType = ByType.xpath;
    }

    public Locator(String element, int waitSec)
    {
        this.element = element;
        this.byType = ByType.xpath;
    }

    public Locator(String element, int waitSec, ByType byType)
    {
        this.element = element;
        this.byType = byType;
    }

    public String getElement()
    {
        return element;
    }

    public ByType getBy()
    {
        return byType;
    }

    public void setBy(ByType byType)
    {
        this.byType = byType;
    }
}
