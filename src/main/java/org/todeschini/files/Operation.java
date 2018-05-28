package org.todeschini.files;

import java.io.File;

/**
 * Created by Artur on 28/05/18.
 */
public interface Operation {

    String DELIMITER_DEFAULT = "ç";
    String ITEN_DELIMITER = ",";
    String PATH_IN  = System.getProperty("user.home") + "/in";
    String PATH_OUT = System.getProperty("user.home") + "/out";
    String FILE_EXTETION = ".dat";

    void categorize(String fileName);
}
