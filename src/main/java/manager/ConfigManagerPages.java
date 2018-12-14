package manager;

import constants.ConfigConstant;

import java.util.ResourceBundle;

/**
 * Description: This class works with pathpages config-property file.
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class ConfigManagerPages {
    private volatile static ConfigManagerPages instance;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(ConfigConstant.PATHPAGES_PROPERTIES_SOURCE);

    public ConfigManagerPages() {
    }

    /**
     * Singleton realization with "Double Checked Locking & Volatile" principle for high performance and thread safety.
     *
     * @return       - an instance of the class.
     */
    public static ConfigManagerPages getInstance() {
        if (instance == null) {
            synchronized (ConfigManagerPages.class) {
                if (instance == null) {
                    instance = new ConfigManagerPages();
                }
            }
        }
        return instance;
    }

    /**
     * This method returns path page from pathpages.properties manager files.
     *
     * @param key   - an incoming key to define a property.
     * @return      - path page.
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
