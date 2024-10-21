package com.vicentemorales.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicentemorales.modelos.Cancion;
import com.vicentemorales.repositorios.RepositorioCanciones;

@Service
public class ServicioCanciones {
	@Autowired
	RepositorioCanciones repositorio;
	
	public List<Cancion> obtenerTodasLasCanciones(){
		return repositorio.findAll();
	}
	
	public Cancion obtenerCancionPorId(Long id) {
		Optional<Cancion> cancion = repositorio.findById(id);
		return cancion.orElse(null);
	}
}
