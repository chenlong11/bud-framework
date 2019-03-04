package org.bud.framework.controller.app.admin.flow.model;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bud.framework.service.flow.exception.BadRequestException;
import org.bud.framework.domain.flow.ModelKeyRepresentation;
import org.bud.framework.domain.flow.ModelRepresentation;
import org.bud.framework.domain.system.User;
import org.bud.framework.service.flow.model.ModelService;
import org.bud.framework.util.WebUtil;
import org.bud.framework.vo.flow.ModelVo;
import org.flowable.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenlong
 * Date：2017/9/9
 * time：18:25
 */
@Controller
@RequestMapping("/app/admin/flow/model")
public class ModelController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ModelService modelService;

    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String modelList(Model model) {
        return "/app/admin/flow/model/modelIndex";
    }

    @RequestMapping(value = "/data",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public PageInfo modelData(PageInfo pageInfo,ModelVo modelVo) {
        PageHelper.startPage(Integer.valueOf(pageInfo.getPageNum()), pageInfo.getPageSize());
        return new PageInfo(modelService.getModelsByParameters(modelVo));
    }

    /**
     * 新增页面
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String modelNew() {
        return "/app/admin/flow/model/modelNew";
    }

    /**
     * 新增
     * @param modelRepresentation
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity saveModel(ModelRepresentation modelRepresentation) {
        modelRepresentation.setKey(modelRepresentation.getKey().replaceAll(" ", ""));
        ModelKeyRepresentation modelKeyInfo = modelService.validateModelKey(null, modelRepresentation.getModelType(), modelRepresentation.getKey());
        if (modelKeyInfo.isKeyAlreadyExists()) {
            throw new BadRequestException("Provided model key already exists: " + modelRepresentation.getKey());
        }
        String json = modelService.createModelJson(modelRepresentation);
        modelService.createModel(modelRepresentation, json, WebUtil.getCurrentUser());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * 编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}/edit",method = RequestMethod.GET)
    public String modelEdit(@PathVariable String id,Model model) {
        model.addAttribute("model",modelService.getModel(id));
        return "/app/admin/flow/model/modelEdit";
    }

    /**
     * 编辑
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity editModel(HttpServletRequest request,User user) {
        System.out.println(request.getParameter("name"));
        System.out.println("name:"+user.getName());
        //modelService.saveModel(model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        modelService.delModelById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * 流程发布
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/public",method = RequestMethod.POST)
    public ResponseEntity modelPublic(@PathVariable String id) {
        modelService.publicModel(id);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
