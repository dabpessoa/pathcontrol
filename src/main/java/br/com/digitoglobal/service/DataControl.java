package br.com.digitoglobal.service;

import br.com.digitoglobal.service.basiccontrol.DataBasicControl;
import br.com.digitoglobal.service.filterControl.DataFilterControl;
import br.com.digitoglobal.service.filterControl.FilterComparator;
import br.com.digitoglobal.service.listenercontrol.DataEvent;
import br.com.digitoglobal.service.listenercontrol.DataListenerControl;
import br.com.digitoglobal.service.sizecontrol.DataSizeControl;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public class DataControl {

    private DataSizeControl dataSizeControl;
    private DataFilterControl dataFilterControl;
    private DataListenerControl dataListenerControl;
    private DataBasicControl dataBasicControl;

    public DataControl(Path path) {
        this(path, null, null);
    }

    public DataControl(Path path, Long pathSizeLimit) {
        this(path, pathSizeLimit, null);
    }

    public DataControl(Path path, DataEvent... dataEvents) {
        this(path, null, dataEvents);
    }

    public DataControl(Path path, Long pathSizeLimit, DataEvent[] dataEvents) {
        this.dataBasicControl = new DataBasicControl(path);
        this.dataSizeControl = new DataSizeControl(path, pathSizeLimit);
        this.dataFilterControl = new DataFilterControl(path);
        this.dataListenerControl = new DataListenerControl(path, dataEvents);
    }

    public void createFile(File file) {
        getDataBasicControl().write(file.toPath());
    }

    public List<File> listFilesFromBasePath() {
    	return getDataBasicControl().list();
    }

    public List<File> filter(String name) {
        return getDataFilterControl().filterFolder(name, true, FilterComparator.CONTAINS);
    }

    public long freeSpace() {
        return getDataSizeControl().freeSpace();
    }

    public long usedSpace() {
        return getDataSizeControl().usedSpace();
    }

    public long sizeLimit() {
        return getDataSizeControl().getLimit();
    }

    public boolean hasFreeSpace() {
        return getDataSizeControl().hasFreeSpace();
    }

    public DataSizeControl getDataSizeControl() {
        return dataSizeControl;
    }

    public void setDataSizeControl(DataSizeControl dataSizeControl) {
        this.dataSizeControl = dataSizeControl;
    }

    public DataFilterControl getDataFilterControl() {
        return dataFilterControl;
    }

    public void setDataFilterControl(DataFilterControl dataFilterControl) {
        this.dataFilterControl = dataFilterControl;
    }

    public DataListenerControl getDataListenerControl() {
        return dataListenerControl;
    }

    public void setDataListenerControl(DataListenerControl dataListenerControl) {
        this.dataListenerControl = dataListenerControl;
    }

    public DataBasicControl getDataBasicControl() {
        return dataBasicControl;
    }

    public void setDataBasicControl(DataBasicControl dataBasicControl) {
        this.dataBasicControl = dataBasicControl;
    }

    public static void main(String[] args) {

        String camiho = "C:/Users/dabpessoa/Desktop/teste";

        DataSizeControl dsc = new DataSizeControl(new File(camiho).toPath(), 533352);

        System.out.println(dsc.getBasePath().toFile().getAbsolutePath());
        System.out.println(dsc.getBasePath().getFileName());
        System.out.println(dsc.usedSpace());
        System.out.println(dsc.freeSpace());
        System.out.println(dsc.hasFreeSpace());

        DataFilterControl dfc = new DataFilterControl(new File(camiho).toPath());

        List<File> files = dfc.filterFile("net", true, FilterComparator.CONTAINS);
        files.forEach(f -> System.out.println("Found File: "+ f.getAbsolutePath()));

        System.out.println();

        List<File> folders = dfc.filterFolder("ca", true, FilterComparator.CONTAINS);
        folders.forEach(f -> System.out.println("Found Folder: "+ f.getAbsolutePath()));

        System.out.println();
        System.out.println();

        DataListenerControl dlc = new DataListenerControl(new File(camiho).toPath());
        dlc.registerListener((path, event) -> {
            System.out.println("Path Event: "+path.toFile().getAbsolutePath());
            System.out.println("EVENT: "+event.name());
        });

        System.out.println("FIM...");


    }

}
