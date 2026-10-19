package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataFromPropertyFile {

    public String getDataFromPropertyFile(String key) throws IOException {

        FileInputStream file = new FileInputStream("./src/main/resources/commondata.properties");
        Properties prop = new Properties();
        prop.load(file);
        String value = prop.getProperty(key);
        file.close();

        return value;
    }
}