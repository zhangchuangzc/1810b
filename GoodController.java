package com.jk.controller;

import com.jk.bean.Comments;
import com.jk.bean.MenuTree;

import com.jk.bean.TreeNoteUtil;


import com.jk.bean.User;
import com.jk.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Controller
public class GoodController {
     @Resource
     GoodsService goodsService;
        //第一次提交999999998798749

    @RequestMapping("getCheckCode")
    @ResponseBody
    public String getCheckCode(String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        try {
            goodsService.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            return "";
        }
        return checkCode;
    }

    @RequestMapping("tologin")
    public String  tologin(HttpServletResponse response , User user, HttpSession session){
        User userFrom =goodsService.findusernameandpass(user);
          if(userFrom!=null){
              return "index";
          }
        return null;
    };
      @RequestMapping("index")
      public  String  getindex(){

          return "index";
      }
    @RequestMapping("show")
    public  String  getshow(){

        return "show";
    }
      @RequestMapping("findTreeList")
      @ResponseBody
      public List<MenuTree> findTreeList(){
          List<MenuTree>list=  goodsService.findTreeList();
          list= TreeNoteUtil.getFatherNode(list);
          return list;
      }
    @RequestMapping("findgoods")
    @ResponseBody
    public HashMap<String,Object>findgoods(Integer pageSize ,Integer start ){

        HashMap<String,Object> map= goodsService.findgoods(pageSize,start);

        return map;
    }

    /*@RequestMapping("findpinhlunbyid")
    @ResponseBody
    public HashMap<String,Object>findpinhlunbyid(Integer pageSize ,Integer start ){

        HashMap<String,Object> map= goodsService.findpinhlunbyid(pageSize,start);

        return map;
    }*/

    @RequestMapping("findpinglunbyid")
    public  String  findpinglunbyid(){

        return "findpinglunbyid";
    }
    @RequestMapping("login")
    public  String  getlogin(){

        return "login";
    }
    @RequestMapping("adduser")
    @ResponseBody
    public void adduser(User user){

        goodsService.adduser(user);
    }

    @RequestMapping("findpinhlunbyids")
    @ResponseBody
    public HashMap<String,Object>findpinhlunbyids(Integer pageSize ,Integer start ,Comments comments){

        HashMap<String,Object> map= goodsService.findpinhlunbyids(pageSize,start,comments);

        return map;
    }
    @RequestMapping("addhtml")
    public  String  addhtml(String id){

        return "addhtml";
    }
    @RequestMapping("updatebyid")
    public  String  updatebyid(String id, Model model){
        Comments Comments=goodsService.updatebyid(id);
        model.addAttribute("c",Comments);
        return "updatebyidhtml";
    }
    @RequestMapping("alldel")
    @ResponseBody
    public  void  alldel(String[] str){
        goodsService.alldel(str);
    }


    @RequestMapping("update")
    @ResponseBody
    public void update(Comments comments){

        goodsService.update(comments);


    }
}
