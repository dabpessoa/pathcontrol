package br.com.digitoglobal.service.basiccontrol;

import java.io.File;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public interface DataBasicControlable {

    byte[] toBytes(Path relativePath);
    void write(byte[] bytes);
    void write(Path relativePath);
    void write(Path relativePath, byte[] bytes);
    void createFolder(Path path);
    void remove(Path path);
    List<File> list();
    List<File> list(PathOrder pathOrder);
    List<File> list(PathOrder pathOrder, PathOrderType pathOrderType);
    Date lastModification(File file);
    Path getBasePath();

}
