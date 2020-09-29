package com.clienteweb.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clienteweb.app.entity.Cliente;
import com.clienteweb.app.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listarTodos() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public void guardar(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente buscarPorId(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		clienteRepository.deleteById(id);

	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clienteRepository.findAll(pageable);
		
	}

}
