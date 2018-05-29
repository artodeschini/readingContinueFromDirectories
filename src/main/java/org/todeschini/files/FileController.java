package org.todeschini.files;

import java.nio.file.WatchService;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Artur on 28/05/18.
 */
public class FileController {

    private static Logger LOGGER = Logger.getLogger( FileController.class.getSimpleName() );

    private volatile static FileController instance;

    private String delimiterByField = ";";
    private String delimiterByItem = ",";
    private String delimiterByAttrItem = "-";

    private FileController(String delimiterByField, String delimiterByItem, String delimiterByAttrItem  ) {
        this.delimiterByField = delimiterByField;
        this.delimiterByItem = delimiterByItem;
        this.delimiterByAttrItem = delimiterByAttrItem;
    }

    public static FileController getInstace(String delimiterByField, String delimiterByItem, String delimiterByAttrItem  ) {
        if ( instance == null ) {
            synchronized ( FileController.class ) {
                if ( instance == null ) {
                    instance = new FileController(delimiterByField,delimiterByItem , delimiterByAttrItem);
                }
            }
        }

        return instance;
    }


    public void createWath() throws java.io.IOException {

        Operation operation = new FileOperation( this.delimiterByField, this.delimiterByItem, this.delimiterByAttrItem);

        operation.createDiretoryIfNotExist( Operation.PATH_IN);
        operation.createDiretoryIfNotExist( Operation.PATH_OUT );
        operation.createDiretoryIfNotExist( Operation.PATH_PROCESS );

        Path p = Paths.get( Operation.PATH_IN );
        WatchService watcher = FileSystems.getDefault().newWatchService();

        p.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

        while (true){
            WatchKey key = null;
            try{
                key = watcher.take();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
            for (WatchEvent<?> event : key.pollEvents() ) {
                WatchEvent.Kind<?> eventKind = event.kind();
//                System.out.println(eventKind.name()); //name of event
//                System.out.println(eventKind.type()); //type of event
//                System.out.println(event.context()); //name of file
                String eventName = event.context().toString();

                //get only data files .dat
                if (eventName.contains( Operation.FILE_EXTETION ) ){
                    //return; // force end programa and exit
                    try {
                        operation.categorize( eventName );
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, e.getMessage() );
                    }

                }
            }
            key.reset(); //start loop again
        } // end while loop
    }

}
