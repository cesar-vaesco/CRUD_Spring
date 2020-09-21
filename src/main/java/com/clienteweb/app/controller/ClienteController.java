package com.clienteweb.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clienteweb.app.entity.Cliente;
import com.clienteweb.app.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoCliente = clienteService.listarTodos();
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listadoCliente);
		return "/views/clientes/listar";
	}

	@GetMapping("/prueba")
	public ResponseEntity<List<Cliente>> listarClientesDos() {
		List<Cliente> cliente = clienteService.listarTodos();
		return ResponseEntity.ok(cliente);
	}

}
