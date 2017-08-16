package br.com.digitoglobal.service.basiccontrol;

import br.com.digitoglobal.service.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static void main(String[] args) {

        Path path = new File("C:\\Users\\diego.pessoa\\Desktop").toPath();
        Path p = Paths.get(path.toString(),Paths.get("teste\\outraPasta").toString());
        System.out.println(p);

    }

    @Override
    public byte[] toBytes(Path relativePath) {
        if (relativePath == null || relativePath.toString().isEmpty()) throw new EmptyPathException("O path não pode ser vazio!");

        Path absolutePath = Paths.get(getBasePath().toString(), relativePath.toString());
        try {
            return Files.readAllBytes(absolutePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PathControlRuntimeException("Erro ao ler bytes do Path. Error: "+e.getMessage(), e);
        }
    }

    @Override
    public void write(byte[] bytes) {
        write(null, bytes);
    }

    @Override
    public void write(Path relativePath, byte[] bytes) {
        try {
            Path absolutePath;
            if (relativePath == null) absolutePath = getBasePath();
            else absolutePath = Paths.get(getBasePath().toString(), relativePath.toString());

            Files.write(absolutePath, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new WriteBytesException("Erro ao escrever bytes. Error: "+e.getMessage(), e);
        }
    }

    @Override
    public List<File> list() {
        return list(null, null);
    }

    @Override
    public List<File> list(PathOrder pathOrder) {
        return list(pathOrder, null);
    }

    @Override
    public List<File> list(PathOrder pathOrder, PathOrderType pathOrderType) {
        try {

            if (pathOrder != null) {
                List<File> files = Files.list(getBasePath()).parallel().map(p -> p.toFile()).sorted((o1,o2) -> {

                    if (pathOrder == PathOrder.ALPHABETIC) {
                        if (pathOrderType != null && pathOrderType == PathOrderType.DESCENDING) {
                            return o2.getName().compareTo(o1.getName());
                        } else {
                            return o1.getName().compareTo(o2.getName());
                        }
                    } else if (pathOrder == PathOrder.SIZE) {
                        if (pathOrderType != null && pathOrderType == PathOrderType.DESCENDING) {
                            if (o2.getTotalSpace() > o1.getTotalSpace()) return 1;
                            if (o2.getTotalSpace() < o1.getTotalSpace()) return -1;
                            else return 0;
                        } else {
                            if (o1.getTotalSpace() > o2.getTotalSpace()) return 1;
                            if (o1.getTotalSpace() < o2.getTotalSpace()) return -1;
                            else return 0;
                        }
                    } else throw new InvalidPathOrderException("Inválid PathOrder.");

                }).collect(Collectors.toList());

                return files;
            }

            return Files.list(getBasePath()).parallel().map(p -> p.toFile()).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
        if (basePath == null) throw new EmptyBasePathException("O basePath não pode ser nulo!");
        return basePath;
    }

    public void setBasePath(Path basePath) {
        this.basePath = basePath;
    }

}
