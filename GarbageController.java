package com.javaclimb.test.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.test.domain.Garbage;
import com.javaclimb.test.service.GarbageService;
import com.javaclimb.test.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/garbage")
public class GarbageController {

    @Autowired
    private GarbageService GarbageService;

    /*
    * 添加垃圾信息
    * */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public Object addGarbage(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String name=request.getParameter("name").trim();
        String type=request.getParameter("type").trim();
        String introduction=request.getParameter("introduction").trim();

        //保存到垃圾信息的对象当中
        Garbage garbage=new Garbage();
        garbage.setName(name);
        garbage.setType(type);
        garbage.setIntroduction(introduction);
        boolean flag=GarbageService.insert(garbage);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }


    /*
    * 修改垃圾信息
    * */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateGarbage(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();
        String name=request.getParameter("name").trim();
        String type=request.getParameter("type").trim();
        String introduction=request.getParameter("introduction");

        //保存到垃圾信息的对象中去
        Garbage garbage=new Garbage();
        garbage.setId(Integer.parseInt(id));
        garbage.setName(name);
        garbage.setType(type);
        garbage.setIntroduction(introduction);
        boolean flag=GarbageService.update(garbage);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /*
    * 删除垃圾信息
    * */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteGarbage(HttpServletRequest request){

        String id=request.getParameter("id").trim();
        boolean flag=GarbageService.delete(Integer.parseInt(id));
        return flag;
    }

    /*
    * 查询垃圾信息
    * */

    @RequestMapping(value = "/allGarbage",method = RequestMethod.GET)
    public Object allGarbage(HttpServletRequest request){
        return GarbageService.allGarbage();
    }


}
