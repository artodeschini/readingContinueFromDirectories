package org.todeschini.files;

import java.io.File;
import java.io.IOException;

/**
 * Created by Artur on 28/05/18.
 */
public interface Operation {

    String DELIMITER_DEFAULT = "ç";
    String ITEN_DELIMITER = ",";
    String ITEM_DELIMITER_ATR = "-";
    String PATH_IN  = System.getProperty("user.home") + "/in";
    String PATH_OUT = System.getProperty("user.home") + "/out";
    String FILE_EXTETION = ".dat";

    void categorize(String fileName) throws IOException;
}
