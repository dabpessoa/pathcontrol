package br.com.digitoglobal.service.listenercontrol;

import br.com.digitoglobal.service.basiccontrol.DataBasicControl;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public class DataListenerControl extends DataBasicControl {

    private Path path;
    private WatchService watchService;
    private WatchKey watchKey;
    private List<DataListenerThread> dataListenerThreads;

    public DataListenerControl(Path path) {
        this(path, null);
    }

    public DataListenerControl(Path path, DataEvent[] dataEvent) {
        super(path);
        this.dataListenerThreads = new ArrayList<>();
        this.path = path;
        try {
            watchService = FileSystems.getDefault().newWatchService();

            if (dataEvent == null || dataEvent.length == 0) {
                watchKey = this.path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            } else {
                List<WatchEvent.Kind<Path>> events = Arrays.stream(dataEvent).map(de -> de.toWatchEventKind()).collect(Collectors.toList());
                watchKey = this.path.register(watchService, events.toArray(new WatchEvent.Kind[events.size()]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Não foi possível criar uma listener. ERROR: "+e.getMessage());
        }
    }

    public void registerListener(DataListener dataListener) {
        DataListenerThread dataListenerThread = new DataListenerThread(watchService, dataListener);
        dataListenerThreads.add(dataListenerThread);
        new Thread(dataListenerThread).start();
    }

    public List<DataListener> getListeners() {
        return dataListenerThreads.parallelStream().map(dlt -> dlt.getDataListener()).collect(Collectors.toList());
    }

    public List<DataListenerThread> getDataListenerThreads() {
        return dataListenerThreads;
    }

    public void close() throws IOException {
        for (DataListenerThread dataListenerThread : dataListenerThreads) {
            dataListenerThread.stop();
        }
        watchService.close();
    }

//
//
//
//
//    public static void main(String[] args) {
//
//        Path path = new File("C:\\Users\\diego.pessoa\\Desktop\\teste").toPath();
//
//
//        try {
//
//            WatchService watcher = FileSystems.getDefault().newWatchService();
//
//            WatchKey key = path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
//
//            while (true) {
//
//                // wait for a key to be available
//                key = watcher.take();
//
//
//                for (WatchEvent<?> event : key.pollEvents()) {
//                    // get event type
//                    WatchEvent.Kind<?> kind = event.kind();
//
//                    // get file name
//                    @SuppressWarnings("unchecked")
//                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
//                    Path fileName = ev.context();
//
//                    System.out.println(kind.name() + ": " + fileName);
//
//                    if (kind == OVERFLOW) {
//                        continue;
//                    } else if (kind == ENTRY_CREATE) {
//
//                        // process create event
//
//                    } else if (kind == ENTRY_DELETE) {
//
//                        // process delete event
//
//                    } else if (kind == ENTRY_MODIFY) {
//
//                        // process modify event
//
//                    }
//                }
//
//                // IMPORTANT: The key must be reset after processed
//                boolean valid = key.reset();
//                if (!valid) {
//                    break;
//                }
//
//            }
//
//        } catch (IOException | InterruptedException e) {
//            System.err.println(e);
//        }
//
//    }

}
