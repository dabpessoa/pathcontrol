package br.com.digitoglobal.service.listenercontrol;

import java.nio.file.Path;

/**
 * Created by diego.pessoa on 03/08/2017.
 */
@FunctionalInterface
public interface DataListener {

    void eventHappens(Path path, DataEvent event);

}
