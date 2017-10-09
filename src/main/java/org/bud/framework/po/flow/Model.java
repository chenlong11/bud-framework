package org.bud.framework.po.flow;

import lombok.Data;

/**
 * Created by chenlong
 * Date：2017/9/11
 * time：16:50
 */
@Data
public class Model extends AbstractModel{

    public Model(String id,String deploymentId){
        this.id = id;
        this.deploymentId = deploymentId;
    }

    private byte[] thumbnail;

    private String deploymentId;

    public Model() {
        super();
    }

}
