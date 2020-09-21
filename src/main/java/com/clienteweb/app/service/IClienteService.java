package com.clienteweb.app.service;

import java.util.List;

import com.clienteweb.app.entity.Cliente;

public interface IClienteService {

	public List<Cliente> listarTodos();
	public void guardar (Cliente cliente);
	public Cliente buscarPorId(Long id);
	public void eliminar(Long id);
}
