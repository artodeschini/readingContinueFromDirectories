package org.todeschini;


import org.todeschini.files.FileController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Artur on 28/05/18.
 */
public class Run {

    private static Logger LOGGER = Logger.getLogger( Run.class.getSimpleName() );

    /**
     * Start the program
     * @param args
     */
    public static void main(String[] args) {
        FileController controller = FileController.getInstace();

        try {
            controller.createWath();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() );
        }
    }
}
