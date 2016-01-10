package main.java.com.dbyl.appiumServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.exec.ExecuteException;

public class RuntimeExec
{
    public StreamWrapper getStreamWrapper(InputStream is, String type)
    {
        return new StreamWrapper(is, type);
    }

    private class StreamWrapper extends Thread
    {
        InputStream is      = null;
        String      message = null;

        StreamWrapper(InputStream is, String type)
        {
            this.is = is;
        }

        public void run()
        {
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer buffer = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null)
                {
                    buffer.append(line);// .append("\n");
                }
                message = buffer.toString();
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }

    // this is where the action is
    public void excuteCMD(String comand)
    {
        Runtime rt = Runtime.getRuntime();
        RuntimeExec rte = new RuntimeExec();
        StreamWrapper error, output;

        try
        {
            Process proc = rt.exec(comand);
            error = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s;
            while ((s = stdInput.readLine()) != null)
            {
                System.out.println(s);
                if (s.contains("Appium REST http"))
                {
                    System.out.println("STARTED!");
                }
            }

            error.start();
            output.start();
            error.join(3000);
            output.join(3000);
            System.out.println("Output: " + output.message + "\nError: " + error.message);
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @author Young
     * @param appiumServicePort
     * @throws ExecuteException
     * @throws IOException
     */
    public void stopAppiumServer(String appiumServicePort) throws ExecuteException, IOException
    {
        ExectorUtils.runWithWatchDog("cmd /c echo off & FOR /F \"usebackq tokens=5\" %a in"
                + " (`netstat -nao ^| findstr /R /C:\"" + appiumServicePort + "\"`) do (FOR /F \"usebackq\" %b in"
                + " (`TASKLIST /FI \"PID eq %a\" ^| findstr /I node.exe`) do taskkill /F /PID %a)");
    }
}
