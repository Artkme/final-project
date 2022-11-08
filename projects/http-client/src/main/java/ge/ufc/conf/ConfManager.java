package ge.ufc.conf;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class ConfManager {

    private static final String CONFIG_FILE_LOCATION = "client.properties";
    private volatile static ConfManager _singleton = null;
    private static URL url = null;
    protected long lastModified;

    private Conf conf;

    private ConfManager(URLConnection conn) throws IOException {
        this.lastModified = conn.getLastModified();

        try (InputStream ignored = conn.getInputStream()){
            Properties props = new Properties();
            props.load(conn.getInputStream());
            fillSetting(props);
        }
    }

    public static ConfManager getConfiguration() throws IOException {
        if (url == null) {
            url = ConfManager.class.getClassLoader().getResource(CONFIG_FILE_LOCATION);
        }
        if (url == null) {
            throw new IOException("Configuration file not found");
        }

        URLConnection conn = url.openConnection();

        long lastModified = conn.getLastModified();
        if (_singleton == null || lastModified > _singleton.lastModified) {
            synchronized (CONFIG_FILE_LOCATION) {
                if (_singleton == null || lastModified > _singleton.lastModified) {
                    _singleton = new ConfManager(conn);
                }
            }
        }

        return _singleton;
    }

    private void fillSetting(Properties props) {
        conf = new Conf();
        conf.setGetUserUrl(props.getProperty("getUser_url"));
        conf.setFillBalanceUrl(props.getProperty("fillBalance_url"));
        conf.setTimeout(Integer.parseInt(props.getProperty("timeout")));
    }

    public Conf getUser() {
        return conf;
    }

}
