package br.com.digitoglobal.service.filterControl;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public interface DataFilterControlable {

    List<File> filterFile(String name, boolean deepFind, FilterComparator... filterTypes);
    List<File> filterFile(boolean deepFind, String[] names, FilterComparator... filterTypes);
    List<File> filterFile(Map<String, List<FilterComparator>> namesFilter, boolean deepFind);
    List<File> filterFolder(String name, boolean deepFind, FilterComparator... filterTypes);
    List<File> filterFolder(boolean deepFind, String[] names, FilterComparator... filterTypes);
    List<File> filterFolder(Map<String, List<FilterComparator>> namesFilter, boolean deepFind);


}
