package com.example.demo.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RespDto;
import com.example.demo.service.ShoppingCartService;

@RestController
public class TestApiController {	
//	@Autowired ShoppingCartService shoppingCart;
	
	@RequestMapping(value = "/setSession", method = RequestMethod.POST)
	public RespDto setSession(HttpSession session) {
		RespDto resp = new RespDto();
		
		session.setAttribute("name", "Allen");
		
		resp.setStatus("OK");
		
		return resp;
	}
	
	@RequestMapping(value = "/getSession", method = RequestMethod.GET)
	public RespDto getSession(HttpSession session) {
		RespDto resp = new RespDto();
		
		resp.setStatus("OK");
		if(session.getAttribute("name") != null) {
			resp.setValue(session.getAttribute("name").toString());
		}
		
		return resp;
	}
	
	@DeleteMapping(value = "/deleteSession")
	public RespDto deleteSession(HttpSession session) {
		RespDto resp = new RespDto();
		session.removeAttribute("name");
		resp.setStatus("OK");
		
		return resp;
	}
	
	@GetMapping(value = "/createScarNo")
	public RespDto createScarNo(HttpSession session) throws ParseException{
		RespDto resp = new RespDto();
		ShoppingCartService shoppingCart = new ShoppingCartService();
//		shoppingCart = shoppingCart.getinit();
//		ShoppingCartService shoppingCart = new ShoppingCartService();
		resp.setStatus("OK");
		resp.setValue(shoppingCart.getScarNo());
		
		session.setAttribute("shoppingCart", shoppingCart);
		
		return resp;
	}
	
	@GetMapping(value = "/getSessionScarNo")
	public RespDto getSessionScarNo(HttpSession session) throws ParseException{
		RespDto resp = new RespDto();
		if(session.getAttribute("shoppingCart") != null && session.getAttribute("shoppingCart").getClass().equals(ShoppingCartService.class)) {
			ShoppingCartService shoppingCart = (ShoppingCartService) session.getAttribute("shoppingCart");
			resp.setStatus("OK");
			resp.setValue(shoppingCart.getScarNo());
		}else {
			resp.setStatus("ERROR");
		}
		
				
		return resp;
	}
}
