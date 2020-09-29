package com.clienteweb.app.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clienteweb.app.entity.Cliente;
import com.clienteweb.app.service.IClienteService;

@Controller
public class HomeController {

	@Autowired
	private IClienteService clienteService;
	/*
	@GetMapping({"/index","/home","/"})
	public String index() {
		return "home";
	} 
	
	*/
	@GetMapping({"/index","/home","/"})
	public String findAll(@RequestParam Map<String, Object> params, Model model) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

		PageRequest pageRequest = PageRequest.of(page, 5);

		Page<Cliente> pageCliente = clienteService.findAll(pageRequest);

		int totalPage = pageCliente.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}

		model.addAttribute("list", pageCliente.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "/views/clientes/paginacion";
	}
}
