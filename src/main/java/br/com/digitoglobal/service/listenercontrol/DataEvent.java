package br.com.digitoglobal.service.listenercontrol;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Created by diego.pessoa on 03/08/2017.
 */
public enum DataEvent {

    CREATE,
    DELETE,
    MODIFY;

    public WatchEvent.Kind<Path> toWatchEventKind() {
        switch (this) {
            case CREATE: return ENTRY_CREATE;
            case DELETE: return ENTRY_DELETE;
            case MODIFY: return ENTRY_MODIFY;
            default: throw new RuntimeException("Tipo inválido!");
        }
    }

}
