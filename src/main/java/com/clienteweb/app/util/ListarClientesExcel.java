package com.clienteweb.app.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
		
		/*Las filas en excel inician en 1 - en spring en 0 */
		Row filaTitulo = hoja.createRow(0);
		/*LAs columnas en excel inician en A - en spring en 0*/
		Cell celda = filaTitulo.createCell(0);
		/*Asignar el titulo al documento*/
		celda.setCellValue("LISTADO GENERAL DE CLIENTES");
		
		/*Asignar cabeceras de los datos para inidcar los campos de la tabla*/
		Row filaData = hoja.createRow(2);
		String [] columnas = {"ID","NOMBRES", "APELLIDOS","TELEFONO", "CORREO", "CIUDAD"};
		
		/*Asignar los nombres(rotulos) de los campos a las filas a traves de un ciclo for*/
		for (int i = 0; i < columnas.length; i++) {
			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
		}
	}

}
