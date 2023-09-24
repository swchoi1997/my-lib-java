package org.example.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;
import org.example.os.OsUtils;

public class RunShell {
    private static final Logger LOG = Logger.getLogger(RunShell.class);

    private final String command;

    public RunShell(String command) {
        this.command = command;
    }

    public String run() throws RuntimeException {
        String[] cmd = null;

        String os = OsUtils.currentOs();

        if (os.toLowerCase().startsWith("window")) {
            cmd = new String[]{"cmd.exe", "/c", this.command};
        } else {
            cmd = new String[]{"/bin/sh", "-c", this.command};
        }

        BufferedReader stdIn = null;
        BufferedReader stdErr = null;

        StringBuffer sbLog = new StringBuffer();
        Process process = null;

        try {
            LOG.info("OS NAME : " + os);
            LOG.info("COMMAND : " + this.command);
            ProcessBuilder builder = new ProcessBuilder(cmd);
            builder.redirectErrorStream(true);
            process = builder.start();

            stdIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
            stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            if (stdErr == null)
                throw new RuntimeException();

            String line = "";

            for (boolean isFirst = true; (line = stdIn.readLine()) != null; isFirst = false) {
                if (isFirst) {
                    sbLog.append(line);
                    continue;
                }
                sbLog.append("\n").append(line);
            }


            try {
                final int exitValue = process.waitFor();
                LOG.info("process exit value : [" + exitValue + "]");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != stdErr) {
                try {
                    stdErr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (null != stdIn) {
                try {
                    stdIn.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (null != process) {
                process.destroy();
            }
        }
        return String.valueOf(sbLog);
    }


}
