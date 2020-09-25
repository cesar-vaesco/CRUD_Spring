package com.clienteweb.app.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.clienteweb.app.entity.Cliente;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/views/clientes/listar")
public class ListarClientesPdf extends AbstractPdfView{

	
	/*
	 * Visualizar en pdf: http://localhost:8080/views/clientes/?format=pdf
	 * */
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Cliente> listadoClientes = (List<Cliente>) model.get("clientes");
		PdfPTable tablaClientes = new PdfPTable(6);
		listadoClientes.forEach(cliente -> {
					tablaClientes.addCell(cliente.getId().toString());
					tablaClientes.addCell(cliente.getNombres());
					tablaClientes.addCell(cliente.getApellidos());
					tablaClientes.addCell(cliente.getTelefono());
					tablaClientes.addCell(cliente.getEmail());
					tablaClientes.addCell(cliente.getCiudad().getCiudad());
		});
		
		document.add(tablaClientes);
	}

}
