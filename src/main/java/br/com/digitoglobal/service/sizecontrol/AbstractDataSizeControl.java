package br.com.digitoglobal.service.sizecontrol;

import br.com.digitoglobal.service.basiccontrol.DataBasicControl;

import java.nio.file.Path;

/**
 * Created by diego.pessoa on 02/08/2017.
 */
public abstract class AbstractDataSizeControl extends DataBasicControl implements DataSizeControlable {

    private Long limit;

    public AbstractDataSizeControl(Path basePath, Long limit) {
        super(basePath);
        setLimit(limit);
    }

    @Override
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getLimit() {
        return limit;
    }

}
