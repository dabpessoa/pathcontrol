package br.com.digitoglobal.service.sizecontrol;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public interface DataSizeControlable {
    boolean hasFreeSpace();
    long freeSpace();
    long usedSpace();
    void setLimit(long limit);
}
