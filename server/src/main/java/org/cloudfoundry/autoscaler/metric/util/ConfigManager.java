package org.cloudfoundry.autoscaler.metric.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigManager {

    private static final Logger logger = Logger.getLogger(ConfigManager.class);

    private static String CONFIG_FILE = "/config.properties";
    private static Properties props = new Properties();

    private static InputStream is = ConfigManager.class.getResourceAsStream(CONFIG_FILE);
    static {
        try {
            props.load(is);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            try {
                is.close();
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }

    }

    public static String get(String key, String defaultValue) {
        String v = get(key);
        if(v == null) {
            v = defaultValue;
        }
        return v;
    }

    public static String get(String key) {
        String v = System.getenv(key);
        if ((v== null) || v.isEmpty() ) {
            v = props.getProperty(key); 
        }
        return v;
    }

    public static double getDouble(String key, double defaultValue) {
        String stringValue = get(key);
        if (stringValue != null) {
            try {
                double doubleValue = Double.parseDouble(stringValue);
                return doubleValue;
            } catch (Exception e) {
                // ignore
            }
        }

        return defaultValue;
    }

    public static double getDouble(String key) {
        String stringValue = get(key);
        if (stringValue != null) {
            try {
                double doubleValue = Double.parseDouble(stringValue);
                return doubleValue;
            } catch (Exception e) {
                // ignore
            }
        }

        return 0.0;
    }

    public static int getInt(String key, int defaultValue) {
        String stringValue = get(key);
        if (stringValue != null) {
            try {
                int intValue = Integer.parseInt(stringValue);
                return intValue;
            } catch (Exception e) {
                // ignore
            }
        }

        return defaultValue;
    }

    public static int getInt(String key) {
        String stringValue = get(key);
        if (stringValue != null) {
            try {
                int intValue = Integer.parseInt(stringValue);
                return intValue;
            } catch (Exception e) {
                // ingore
            }
        }

        return 0;
    }

    public static long getLong(String key, long defaultValue) {
        String stringValue = get(key);
        if (stringValue != null) {
            try {
                long longValue = Long.parseLong(stringValue);
                return longValue;
            } catch (Exception e) {
                // ignore
            }
        }

        return defaultValue;
    }

    public static long getLong(String key) {
        String stringValue = get(key);
        if (stringValue != null) {
            try {
                long longValue = Long.parseLong(stringValue);
                return longValue;
            } catch (Exception e) {
                // ignore
            }
        }

        return 0;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {

        String stringValue = get(key);
        if (stringValue != null) {
            try {
                return Boolean.parseBoolean(stringValue);
            } catch (Exception e) {
                // ignore
            }
        }
        return defaultValue;
    }

}
