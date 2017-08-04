package br.com.digitoglobal.service.filterControl;

import br.com.digitoglobal.service.basiccontrol.DataBasicControl;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by diego.pessoa on 03/08/2017.
 */
public class AbstractDataFilterControl extends DataBasicControl implements DataFilterControlable {

    public AbstractDataFilterControl(Path basePath) {
        super(basePath);
    }

    @Override
    public List<File> filterFile(String name, boolean deepFind, FilterComparator... filterTypes) {
        Map<String, List<FilterComparator>> namesFilter = new HashMap<>();
        namesFilter.put(name, Arrays.asList(filterTypes));

        return filterFile(namesFilter, deepFind);
    }

    @Override
    public List<File> filterFile(boolean deepFind, String[] names, FilterComparator... filterTypes) {
        Map<String, List<FilterComparator>> namesFilter = new HashMap<>();
        for (String name : names) {
            namesFilter.put(name, Arrays.asList(filterTypes));
        }

        return filterFile(namesFilter, deepFind);
    }

    @Override
    public List<File> filterFile(Map<String, List<FilterComparator>> namesFilter, boolean deepFind) {
        if (deepFind) {
            return FilterUtil.deepFilter(getBasePath(), namesFilter, DataType.FILE);
        } else {
            return FilterUtil.filter(getBasePath(), namesFilter, DataType.FILE);
        }
    }

    @Override
    public List<File> filterFolder(String name, boolean deepFind, FilterComparator... filterTypes) {
        Map<String, List<FilterComparator>> namesFilter = new HashMap<>();
        namesFilter.put(name, Arrays.asList(filterTypes));

        return filterFolder(namesFilter, deepFind);
    }

    @Override
    public List<File> filterFolder(boolean deepFind, String[] names, FilterComparator... filterTypes) {
        Map<String, List<FilterComparator>> namesFilter = new HashMap<>();
        for (String name : names) {
            namesFilter.put(name, Arrays.asList(filterTypes));
        }

        return filterFolder(namesFilter, deepFind);
    }

    @Override
    public List<File> filterFolder(Map<String, List<FilterComparator>> namesFilter, boolean deepFind) {
        if (deepFind) {
            return FilterUtil.deepFilter(getBasePath(), namesFilter, DataType.FOLDER);
        } else {
            return FilterUtil.filter(getBasePath(), namesFilter, DataType.FOLDER);
        }
    }

}
