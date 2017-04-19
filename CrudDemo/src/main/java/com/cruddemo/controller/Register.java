package com.cruddemo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // @Controller ����Java����controller���Ʋ�
public class Register {

    /**
     * @RequestParamע��������ǣ����ݲ�������URL��ȡ�ò���ֵ
     * @param username
     *            �û�����һ��Ҫ��Ӧ�ű���name����
     * @param password
     *            �û����룬ҲӦ�ö�Ӧ����������
     * @param model
     *            һ������󣬿����ڴ洢����ֵ
     * @return
     */
    @RequestMapping("/register") // @RequestMapping ע�������ָ����URL·�����ʱ����Ʋ�
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
