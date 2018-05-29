package org.todeschini;


import org.todeschini.files.FileController;
import org.todeschini.files.Operation;

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
        String delimiterByField = ";";
        String delimiterByItem = Operation.ITEN_DELIMITER;
        String delimiterByAttrItem = Operation.ITEM_DELIMITER_ATR;

        if ( args.length > 0 ) {
            delimiterByField = args[0];

            if ( args.length > 1 ) {
                delimiterByItem = args[1];
            }

            if ( args.length > 2 ) {
                delimiterByAttrItem = args[2];
            }
        }

        //This item controller the path in of files create and modify
        FileController controller = FileController.getInstace(delimiterByField, delimiterByItem, delimiterByAttrItem);

        try {
            controller.createWath();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() );
        }
    }
}
