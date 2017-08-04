package br.com.digitoglobal.service.listenercontrol;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by diego.pessoa on 03/08/2017.
 */
public class DataListenerThread implements Runnable {

    private WatchService watchService;
    private WatchKey watchKey;
    private DataListener dataListener;
    private boolean running;

    public DataListenerThread(WatchService watchService, DataListener dataListener) {
        this.watchService = watchService;
        this.dataListener = dataListener;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {

            try {
                // wait for a key to be available
                watchKey = watchService.take();

                boolean valid = verifyKey(watchKey);
                if (!valid) {
                    running = false;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void eventHappens(Path path, DataEvent event) {
        dataListener.eventHappens(path, event);
    }

    public void stop() {
        watchKey.cancel();
        running = false;
    }

    public DataListener getDataListener() {
        return dataListener;
    }

    public boolean verifyKey(WatchKey watchKey) {

        for (WatchEvent<?> event : watchKey.pollEvents()) {

            // get event type
            WatchEvent.Kind<?> kind = event.kind();

            // get file name
            WatchEvent<Path> ev = (WatchEvent<Path>) event;
            Path path = ev.context();

            if (kind == OVERFLOW) {
                continue;
            } else if (kind == ENTRY_CREATE) {

                eventHappens(path, DataEvent.CREATE);

            } else if (kind == ENTRY_DELETE) {

                eventHappens(path, DataEvent.DELETE);

            } else if (kind == ENTRY_MODIFY) {

                eventHappens(path, DataEvent.MODIFY);

            }

        }

        return watchKey.reset();

    }

}
