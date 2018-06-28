
package com.tgt.profanity.utils;

import com.tgt.profanity.helpers.BaseResource;
import com.tgt.profanity.helpers.Constants;
import java.io.InputStream;
import java.util.Properties;


public class ProfanityResource implements BaseResource {

    private static Properties properties = null;

    //Load  File
    private void loadResource() {
        try {
            if (properties == null) {
                properties = new Properties();
                InputStream input = getClass().getClassLoader().getResourceAsStream(Constants.Resources.PROFANITY_EN_FILE);
                if (input != null) {
                    properties.load(input);
                } else {
                    System.err.println("Swear word resource file not found:"+Constants.Resources.PROFANITY_EN_FILE );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Load the data from the properties file.
    @Override
    public String getProperties(String property) {
        if (properties == null) {
            loadResource();
        }
        return properties.getProperty(property);
    }

}
