package com.clienteweb.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clienteweb.app.entity.Ciudad;
import com.clienteweb.app.entity.Cliente;
import com.clienteweb.app.service.ICiudadService;
import com.clienteweb.app.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ICiudadService ciudadService;

	/*
	 * http://localhost:8080/views/clientes/
	 * */
	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoCliente = clienteService.listarTodos();
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listadoCliente);
		return "/views/clientes/listar";
	}

	/*
	 * http://localhost:8080/views/clientes/prueba
	 * */
	@GetMapping("/prueba")
	public ResponseEntity<List<Cliente>> listarClientesDos() {
		List<Cliente> cliente = clienteService.listarTodos();
		return ResponseEntity.ok(cliente);
	}

	/*
	 * http://localhost:8080/views/clientes/create
	 * */
	@GetMapping("/create")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		List<Ciudad> listCiudades = ciudadService.listaCiudades();
		model.addAttribute("titulo", "Formulario: Nuevo cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);
		return "/views/clientes/formCrear";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Cliente cliente) {
		clienteService.guardar(cliente);
		System.out.println("Cliente guardado con exito");
		return "redirect:/views/clientes/";
	}
	
}
