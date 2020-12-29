package com.javaclimb.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.test.domain.AdminGuanli;
import com.javaclimb.test.service.AdminGuanliService;
import com.javaclimb.test.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class AdminGuanliController {

    @Autowired
    private AdminGuanliService AdminGuanliService;


    /*判断是否登录成功*/
    @RequestMapping(value="/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject=new JSONObject();
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        boolean flag=AdminGuanliService.verifyPassword(name,password);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            session.setAttribute(Consts.NAME,name);
            return jsonObject;
        }
        else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名或密码错误");
            return jsonObject;
        }
    }

    /*
    * 添加管理员
    * */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addAdminGuanli(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String pic = request.getParameter("pic").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();

        //保存到管理员的对象中
        AdminGuanli adminGuanli = new AdminGuanli();
        adminGuanli.setName(name);
        adminGuanli.setUsername(username);
        adminGuanli.setPassword(password);
        adminGuanli.setPic(pic);
        adminGuanli.setLocation(location);
        adminGuanli.setIntroduction(introduction);
        boolean flag = AdminGuanliService.insert(adminGuanli);
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
     * 修改管理员
     * */
    @RequestMapping(value ="/update",method = RequestMethod.POST)
    public Object updateAdminGuanli(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        //保存到管理员的对象中
        AdminGuanli adminGuanli = new AdminGuanli();
        adminGuanli.setId(Integer.parseInt(id));
        adminGuanli.setName(name);
        adminGuanli.setUsername(username);
        adminGuanli.setPassword(password);

        adminGuanli.setLocation(location);
        adminGuanli.setIntroduction(introduction);
        boolean flag = AdminGuanliService.update(adminGuanli);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            System.out.println("11111111111111111");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /*
     * 删除管理员
     * */
    @RequestMapping(value ="/delete",method = RequestMethod.GET)
    public Object deleteAdminGuanli(HttpServletRequest request){

        String id = request.getParameter("id").trim();
        boolean flag = AdminGuanliService.delete(Integer.parseInt(id));
        return flag;
    }

    /*
    * 查询管理员
    * */
    @RequestMapping(value ="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return AdminGuanliService.selectByPrimaryKey(Integer.parseInt(id));
    }


    @RequestMapping(value ="/allAdminGuanli",method = RequestMethod.GET)
    public Object allAdminGuanli(HttpServletRequest request){
        return AdminGuanliService.allAdminGuanli();
    }

    @RequestMapping(value ="/AdminGuanliOfName",method = RequestMethod.GET)
    public Object AdminGuanliOfName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        return AdminGuanliService.AdminGuanliOfName("%"+name+"#");
    }


    /*
    * 更新管理员图片
    * */
    @RequestMapping(value ="/updateAdminPic",method = RequestMethod.POST)
    public Object updateAdminPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"AdminPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(file1.exists()){
            file1.mkdir();
        }
        //实际文件路径
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库的相对文件地址
        String storeAvatorPath = "/img/AdminPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            AdminGuanli adminGuanli = new AdminGuanli();
            adminGuanli.setId(id);
            adminGuanli.setPic(storeAvatorPath);
            boolean flag = AdminGuanliService.update(adminGuanli);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败"+e.getMessage());
        }finally {
            return jsonObject;
        }

    }

}
