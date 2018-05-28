package org.todeschini.files;

import java.nio.file.WatchService;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;

/**
 * Created by Artur on 28/05/18.
 */
public class FileController {

    private volatile static FileController instance;

    private FileController() {}

    public static FileController getInstace() {
        if ( instance == null ) {
            synchronized ( FileController.class ) {
                if ( instance == null ) {
                    instance = new FileController();
                }
            }
        }

        return instance;
    }


    public void createWath() throws java.io.IOException {

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
//                    System.out.println( eventName.toString() );
                    Operation operation = new FileOperation();
                    //return; // force end programa and exit
                    operation.categorize( eventName );
                }
            }
            key.reset(); //start loop again
        } // end while loop
    }

}
