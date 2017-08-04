package br.com.digitoglobal.service.basiccontrol;

import br.com.digitoglobal.service.filterControl.DataFilterControlable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public abstract class AbstractDataBasicControl implements DataBasicControlable {

    private Path basePath;

    public AbstractDataBasicControl(Path basePath) {
        this.basePath = basePath;
    }

    @Override
    public void writeFile(File file) {
        write(file.toPath());
    }

    @Override
    public void write(Path path) {
        try {
            Files.write(path, Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<File> list() {
        try {
            return Files.list(getBasePath()).parallel().map(p -> p.toFile()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<File> find(DataFilterControlable filter) {
        return null;
    }

    @Override
    public void remove(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCascade(Path path) {
        try {
            // TODO corrigir para cascade.
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createFolder(Path path) {
        try {
            // TODO corrigir para cascade.
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Date lastModification(File file) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(file.lastModified());
        return c.getTime();
    }

    @Override
    public Path getBasePath() {
        return basePath;
    }

    public void setBasePath(Path basePath) {
        this.basePath = basePath;
    }

}
