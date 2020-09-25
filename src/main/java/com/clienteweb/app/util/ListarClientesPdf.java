package com.clienteweb.app.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.clienteweb.app.entity.Cliente;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
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
		
		/*
		 * dar tamaño de hoja y formato, para el ejemplo es tamaño carta (LETTER) 
		 * y mostrar horizontal(rotate()) - para aplicar los cambios se aplica la función open()
		 * */
		document.setPageSize(PageSize.LETTER.rotate());
		document.open();
		
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;
		
		Font fuenteTitulo = FontFactory.getFont("Helvetica",18,Color.WHITE);
		celda = new PdfPCell(new Phrase("LISTADO GENERAL DE CLIENTES", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40,190,138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(30);
		
		tablaTitulo.addCell(celda);
		/*Dando espacio al titulo del cuerpo de la tabla*/
		tablaTitulo.setSpacingAfter(30);
		
		
		PdfPTable tablaClientes = new PdfPTable(6);
		listadoClientes.forEach(cliente -> {
					tablaClientes.addCell(cliente.getId().toString());
					tablaClientes.addCell(cliente.getNombres());
					tablaClientes.addCell(cliente.getApellidos());
					tablaClientes.addCell(cliente.getTelefono());
					tablaClientes.addCell(cliente.getEmail());
					tablaClientes.addCell(cliente.getCiudad().getCiudad());
		});
		
		document.add(tablaTitulo);
		document.add(tablaClientes);
	}

}
