package br.com.digitoglobal.service.sizecontrol;

import java.nio.file.Path;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public class DataSizeControl extends AbstractDataSizeControl {

    public DataSizeControl(Path basePath, long limit) {
        super(basePath, limit);
    }

    @Override
    public boolean hasFreeSpace() {
        return freeSpace() > 0;
    }

    @Override
    public long freeSpace() {
        return getLimit() - usedSpace();
    }

    @Override
    public long usedSpace() {
        return DataSizeControlUtil.size(getBasePath());
    }

}
