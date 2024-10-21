package com.vicentemorales.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vicentemorales.modelos.Cancion;
import com.vicentemorales.servicios.ServicioCanciones;

import jakarta.validation.Valid;

@Controller
public class ControladorCanciones {
	@Autowired
	ServicioCanciones servicio;
	
	@GetMapping("/canciones")
	public String desplegarCanciones(Model modelo){
		List<Cancion> canciones = servicio.obtenerTodasLasCanciones();
		modelo.addAttribute("canciones", canciones);
		return "canciones.jsp";
	}
	
	@GetMapping("/canciones/detalle/{id}")
	public String desplegarDetalleCancion(Model modelo, @PathVariable Long id){
		Cancion cancion = servicio.obtenerCancionPorId(id);
		modelo.addAttribute("cancion", cancion);
		return "detalleCancion.jsp";
		
	}
	@GetMapping("/canciones/formulario/agregar")
	public String formularioAgregarCancion(@ModelAttribute("cancion")Cancion cancion) {
		return "agregarCancion.jsp";
	}
	
	@PostMapping("/canciones/formulario/agregar/{idCancion}")
	public String procesarAgregarCancion(@Valid @ModelAttribute("cancion") Cancion cancion,
										BindingResult validaciones){
		if(validaciones.hasErrors()) {
			return "agregarCancion.jsp";
		}
		this.servicio.agregarCancion(cancion);
		return "redirect:/canciones";
	}
		
}
