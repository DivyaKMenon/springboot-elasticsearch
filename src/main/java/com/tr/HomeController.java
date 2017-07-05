package com.tr;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tr.bean.ProductBean;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
	public String welcome(Map<String, Object> model) {
		model.put("id", 1);
		return "home";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String search(@RequestBody ProductBean productBean, Model model) {

		System.out.println(productBean.getId());
		return "home";
	}

}