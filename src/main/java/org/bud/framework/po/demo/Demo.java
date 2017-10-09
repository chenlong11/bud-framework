package org.bud.framework.po.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bud.framework.po.base.BaseModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by chenlong
 * Date：2017/9/4
 * time：17:52
 */
@Data
@NoArgsConstructor//生成无参的构造方法
@AllArgsConstructor//生成全参的构造方法
public class Demo extends BaseModel implements Serializable{

    private static final long serialVersionUID = 8361634765195908016L;

    private Long id;

    private String demoStr;

    private LocalDateTime demoDate;

    private Long demoNumber;

}
