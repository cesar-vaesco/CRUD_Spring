package com.clienteweb.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	 */
	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoCliente = clienteService.listarTodos();
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listadoCliente);
		return "/views/clientes/listar";
	}

	/*
	 * http://localhost:8080/views/clientes/prueba
	 */
	@GetMapping("/prueba")
	public ResponseEntity<List<Cliente>> listarClientesDos() {
		List<Cliente> cliente = clienteService.listarTodos();
		return ResponseEntity.ok(cliente);
	}

	/*
	 * http://localhost:8080/views/clientes/create
	 */
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
	public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model) {
		List<Ciudad> listCiudades = ciudadService.listaCiudades();

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo cliente");
			model.addAttribute("cliente", cliente);
			model.addAttribute("ciudades", listCiudades);
			System.out.println("Existieron errores en el formulario");
			return "/views/clientes/formCrear";
		}
		clienteService.guardar(cliente);
		System.out.println("Cliente guardado con exito");
		return "redirect:/views/clientes/";
	}

	/*
	 * http://localhost:8080/views/clientes/edit/{id}
	 */
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idCliente, Model model) {
			
		Cliente cliente = null;
		
		if (idCliente > 0) {
			cliente = clienteService.buscarPorId(idCliente);
			
			if (cliente == null) {
				System.out.println("Error: El ID del cliente no existe!");
				return "redirect:/views/clientes/";
			}
		}else {
			System.out.println("Error: Error con el ID del Cliente");
			return "redirect:/views/clientes/";
		}
		
		List<Ciudad> listCiudades = ciudadService.listaCiudades();

		model.addAttribute("titulo", "Formulario: Editar Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);

		return "/views/clientes/formCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idCliente) {
		clienteService.eliminar(idCliente);
		System.out.println("Registro eliminado con exito!");
		return "redirect:/views/clientes/";
	}

}
