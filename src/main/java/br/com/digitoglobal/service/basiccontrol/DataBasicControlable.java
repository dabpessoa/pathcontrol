package br.com.digitoglobal.service.basiccontrol;

import br.com.digitoglobal.service.filterControl.DataFilterControlable;

import java.io.File;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public interface DataBasicControlable {

    List<File> list();
    List<File> find(DataFilterControlable filter);
    void write(Path path);
    void writeFile(File file);
    void createFolder(Path path);
    void remove(Path path);
    void removeCascade(Path path);
    Date lastModification(File file);
    Path getBasePath();

}
