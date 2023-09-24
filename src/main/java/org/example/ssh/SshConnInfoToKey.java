package org.example.ssh;

public class SshConnInfoToKey extends SshConnInfo {

    private final String keyFile;

    public SshConnInfoToKey(final String host,
                            final String name,
                            final String passwd,
                            final String keyFile) {
        this(host, 22, name, passwd, keyFile);
    }

    public SshConnInfoToKey(final String host,
                            final int port,
                            final String name,
                            final String passwd,
                            final String keyFile) {
        super(host, port, name, passwd);
        this.keyFile = keyFile;
    }

    public String getKeyFile() {
        return keyFile;
    }
}
