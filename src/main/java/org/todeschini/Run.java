package org.todeschini;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by Artur on 28/05/18.
 */
public class Run {

    public static void main(String[] args) {

        Path in = Paths.get( System.getProperty("user.dir") + "/in" );

        Path out = Paths.get( System.getProperty("user.dir") + "/out" );



        try {
            WatchService watcher =  in.getFileSystem().newWatchService();

            WatchKey key = in.register(watcher,
                    ENTRY_CREATE /*,
                    ENTRY_DELETE,
                    ENTRY_MODIFY */);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }


    }
}
