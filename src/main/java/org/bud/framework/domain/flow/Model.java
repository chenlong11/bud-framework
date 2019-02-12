package org.bud.framework.domain.flow;

import lombok.Data;

/**
 * Created by chenlong
 * Date：2017/9/11
 * time：16:50
 */
@Data
public class Model extends AbstractModel{

    public Model(String id, String deploymentId, String processDefId) {
        this.id = id;
        this.deploymentId = deploymentId;
        this.processDefId = processDefId;
    }

    private byte[] thumbnail;

    private String deploymentId;

    private String processDefId;

    public Model() {
        super();
    }

}
