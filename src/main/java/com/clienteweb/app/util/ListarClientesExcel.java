package com.clienteweb.app.util;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.clienteweb.app.entity.Cliente;

@Component("/views/clientes/listar.xlsx")
public class ListarClientesExcel extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		/*
		 * Descargar el docuemnto de excel
		 * http://localhost:8080/views/clientes/?format=xlsx
		 */
		// Crea un archivo en excel que se llama listado-clientes
		response.setHeader("Content-Disposition", "attachment; filename=\"listado-clientes.xlsx\"");
		// Crea una hoja en excel que se llame Clientes
		Sheet hoja = workbook.createSheet("Clientes");

		/* Las filas en excel inician en 1 - en spring en 0 */
		Row filaTitulo = hoja.createRow(0);
		/* LAs columnas en excel inician en A - en spring en 0 */
		Cell celda = filaTitulo.createCell(0);
		/* Asignar el titulo al documento */
		celda.setCellValue("LISTADO GENERAL DE CLIENTES");

		/* Asignar cabeceras de los datos para inidcar los campos de la tabla */
		Row filaData = hoja.createRow(2);
		String[] columnas = { "ID", "NOMBRES", "APELLIDOS", "TELEFONO", "CORREO", "CIUDAD" };

		/*
		 * Asignar los nombres(rotulos) de los campos a las filas a traves de un ciclo
		 * for
		 */
		for (int i = 0; i < columnas.length; i++) {
			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
		}

		/* Obtener el listado de los datos de los clientes */
		/*
		 * ListaC toma los datos que se han pasado en el model de la vista que muestra
		 * los datos
		 * 
		 * @GetMapping("/") public String listarClientes(Model model) { List<Cliente>
		 * listadoClientes = clienteService.listarTodos(); model.addAttribute("titulo",
		 * "Lista de Clientes"); model.addAttribute("clientes", listadoClientes); return
		 * "/views/clientes/listar"; }
		 * 
		 */
		List<Cliente> listaC = (List<Cliente>) model.get("clientes");
		// Fila en la cual se va a cargar la información
		int numFila = 3;

		// Obtener la información de listaC
		for (Cliente cliente : listaC) {
			/*Creando filas nuevas para agregar la información al documento*/
			filaData = hoja.createRow(numFila);
			/*Crear las celdas donde se vana agregar los datos*/
			filaData.createCell(0).setCellValue(cliente.getId());
			filaData.createCell(1).setCellValue(cliente.getNombres());
			filaData.createCell(2).setCellValue(cliente.getApellidos());
			filaData.createCell(3).setCellValue(cliente.getTelefono());
			filaData.createCell(4).setCellValue(cliente.getEmail());
			filaData.createCell(5).setCellValue(cliente.getCiudad().getCiudad());
			
			numFila ++;
		}
	}

}
