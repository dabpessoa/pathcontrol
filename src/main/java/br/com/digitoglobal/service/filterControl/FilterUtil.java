package br.com.digitoglobal.service.filterControl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by diego.pessoa on 03/08/2017.
 */
public class FilterUtil {

    public static List<File> filter(Path path, Map<String, List<FilterComparator>> namesFilter, DataType dataType) {

        List<File> filesFound = new ArrayList<>();

        File file = path.toFile();

        if (file.isFile()) {

            if (filterPass(file.toPath(), namesFilter)) {
                filesFound.add(file);
            }

        } else if (file.isDirectory()) {

            File[] files = file.listFiles();
            for (File f : files) {
                if (filterPass(f.toPath(), namesFilter)) {
                    filesFound.add(f);
                }
            }

        }

        return filesFound;

    }

    public static List<File> deepFilter(Path path, Map<String, List<FilterComparator>> namesFilter, DataType dataType) {

        List<File> filesFound = new ArrayList<>();

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                    if (dataType == DataType.FOLDER || dataType == dataType.BOTH) {
                        if (filterPass(dir, namesFilter)) {
                            filesFound.add(dir.toFile());
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

                    if (dataType == DataType.FILE || dataType == dataType.BOTH) {
                        if (filterPass(file, namesFilter)) {
                            filesFound.add(file.toFile());
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {

                    System.out.println("skipped: " + file + " (" + exc + ")");
                    // Skip folders that can't be traversed
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {

                    if (exc != null)
                        System.out.println("had trouble traversing: " + dir + " (" + exc + ")");
                    // Ignore errors traversing a folder
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            throw new AssertionError("walkFileTree will not throw IOException if the FileVisitor does not");
        }

        return filesFound;

    }

    // Se passar em um dos nomes do mapa, já está ok.
    public static boolean filterPass(Path path, Map<String, List<FilterComparator>> namesFilter) {

        boolean filterPass = false;

        for (Map.Entry<String, List<FilterComparator>> entry : namesFilter.entrySet()) {
            String string = entry.getKey();
            String filterString = path.getFileName().toString();
            if (filterPass(string, filterString, entry.getValue())) {
                filterPass = true;
                break;
            }
        }

        return filterPass;

    }

    public static boolean filterPass(String string, String filterString, List<FilterComparator> filterTypes) {
        return filterTypes.parallelStream().allMatch(filterType -> filterPass(string, filterString, filterType));
    }

    public static boolean filterPass(String string, String filterString, FilterComparator filterType) {

        boolean filterResult;
        switch (filterType) {
            case CONTAINS: {filterResult = filterString.contains(string);} break;
            case ENDS_WITH: {filterResult = filterString.endsWith(string);} break;
            case EQUALS: {filterResult = filterString.equals(string);} break;
            case STARTS_WITH: {filterResult = filterString.startsWith(string);} break;
            default: throw new RuntimeException("FilterComparator inválido: "+filterType);
        }
        return filterResult;

    }

}
