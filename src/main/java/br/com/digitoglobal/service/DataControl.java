package br.com.digitoglobal.service;

import br.com.digitoglobal.service.filterControl.DataFilterControl;
import br.com.digitoglobal.service.filterControl.FilterComparator;
import br.com.digitoglobal.service.listenercontrol.DataListenerControl;
import br.com.digitoglobal.service.sizecontrol.DataSizeControl;

import java.io.File;
import java.util.List;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public class DataControl {

    public static void main(String[] args) {

        DataSizeControl dsc = new DataSizeControl(new File("C:\\Users\\diego.pessoa\\Desktop\\teste").toPath(), 533352);

        System.out.println(dsc.getBasePath().toFile().getAbsolutePath());
        System.out.println(dsc.getBasePath().getFileName());
        System.out.println(dsc.usedSpace());
        System.out.println(dsc.freeSpace());
        System.out.println(dsc.hasFreeSpace());

        DataFilterControl dfc = new DataFilterControl(new File("C:\\Users\\diego.pessoa\\Desktop\\teste").toPath());

        List<File> files = dfc.filterFile("net", true, FilterComparator.CONTAINS);
        files.forEach(f -> System.out.println("Found File: "+ f.getAbsolutePath()));

        System.out.println();

        List<File> folders = dfc.filterFolder("ca", true, FilterComparator.CONTAINS);
        folders.forEach(f -> System.out.println("Found Folder: "+ f.getAbsolutePath()));

        System.out.println();
        System.out.println();

        DataListenerControl dlc = new DataListenerControl(new File("C:\\Users\\diego.pessoa\\Desktop\\teste").toPath());
        dlc.registerListener((path, event) -> {
            System.out.println("Path Event: "+path.toFile().getAbsolutePath());
            System.out.println("EVENT: "+event.name());
        });

        System.out.println("FIM...");


    }

}
