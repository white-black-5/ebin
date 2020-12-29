package com.javaclimb.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.test.domain.AdminGuanli;
import com.javaclimb.test.domain.User;
import com.javaclimb.test.service.AdminGuanliService;
import com.javaclimb.test.service.UserService;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService UserService;


    /*判断是否登录成功*/
    @RequestMapping(value="/userlogin/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject=new JSONObject();
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        boolean flag=UserService.verifyPassword(name,password);
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
    public Object addUser(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String contact = request.getParameter("contact").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(name);
        //保存到用户的对象中
        User user=new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(new Byte(sex));
        user.setPic(pic);
        user.setBirth(birthDate);
        user.setLocation(location);
        user.setContact(contact);

        boolean flag = UserService.insert(user);
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
    public Object updateUser(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String contact = request.getParameter("contact").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到用户的对象中
        User user=new User();
        user.setId(Integer.parseInt(id));
        user.setName(name);
        user.setPassword(password);
        user.setSex(new Byte(sex));
        user.setPic(pic);
        user.setBirth(birthDate);
        user.setLocation(location);
        user.setContact(contact);
        boolean flag = UserService.update(user);
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
     * 删除用户
     * */
    @RequestMapping(value ="/delete",method = RequestMethod.GET)
    public Object deleteUser(HttpServletRequest request){

        String id = request.getParameter("id").trim();
        boolean flag = UserService.delete(Integer.parseInt(id));
        return flag;
    }

    /*
    * 查询管理员
    * */
    @RequestMapping(value ="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return UserService.selectByPrimaryKey(Integer.parseInt(id));
    }


    @RequestMapping(value ="/allUser",method = RequestMethod.GET)
    public Object allUser(HttpServletRequest request){
        return UserService.allUser();
    }

    @RequestMapping(value ="/UserOfName",method = RequestMethod.GET)
    public Object UserOfName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        return UserService.userOfName("%"+name+"#");
    }




    /*
    * 更新用户图片
    * */
    @RequestMapping(value ="/updateUserPic",method = RequestMethod.POST)
    public Object updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
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
                +System.getProperty("file.separator")+"userPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(file1.exists()){
            file1.mkdir();
        }
        //实际文件路径
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库的相对文件地址
        String storeAvatorPath = "/img/userPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            User user = new User();
            user.setId(id);
            user.setPic(storeAvatorPath);
            boolean flag = UserService.update(user);
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
