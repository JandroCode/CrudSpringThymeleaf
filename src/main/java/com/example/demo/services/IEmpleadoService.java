package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Empleado;

public interface IEmpleadoService {
	void save(Empleado empleado);
	List<Empleado> listadoEmpleados();
	Empleado findById(Long id);
	void delete(Long id);

}
