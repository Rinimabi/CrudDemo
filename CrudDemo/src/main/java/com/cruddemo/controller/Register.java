package com.cruddemo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // @Controller 代表本Java类是controller控制层
public class Register {

    /**
     * @RequestParam注解的作用是：根据参数名从URL中取得参数值
     * @param username
     *            用户名，一定要对应着表单的name才行
     * @param password
     *            用户密码，也应该对应表单的数据项
     * @param model
     *            一个域对象，可用于存储数据值
     * @return
     */
    @RequestMapping("/register") // @RequestMapping 注解可以用指定的URL路径访问本控制层
    public String register(@RequestParam("username") String username, 
    		@RequestParam("password") String password,@RequestParam("email") String email,
    		@RequestParam("phone") String phone,Model model) {
    	
    	Map<String ,Object> param=new HashMap<String,Object>();
    	param.put("username",username);
    	param.put("password",password);
    	param.put("email",email);
    	param.put("phone",phone);
    	model.addAttribute("param", param);
    	return "registersuccess.jsp";
    }

}
