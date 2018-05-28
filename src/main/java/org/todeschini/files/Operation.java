package org.todeschini.files;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by Artur on 28/05/18.
 */
public interface Operation {

    Charset CHARSET_DEFAULT = StandardCharsets.UTF_8;
    String DELIMITER_DEFAULT = "ç";
}
