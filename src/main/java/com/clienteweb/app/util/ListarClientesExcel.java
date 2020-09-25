package com.clienteweb.app.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

@Component("/views/clientes/listar.xlsx")
public class ListarClientesExcel extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		/*
		 * Descargar el docuemnto de excel http://localhost:8080/views/clientes/?format=xlsx
		 * */
		//Crea un archivo en excel que se llama listado-clientes
		response.setHeader("Content-Disposition","attachment; filename=\"listado-clientes.xlsx\"");
		// Crea una hoja en excel que se llame Clientes
		Sheet hoja = workbook.createSheet("Clientes"); 
	}

}
