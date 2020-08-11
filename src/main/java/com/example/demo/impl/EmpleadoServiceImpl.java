package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IEmpleadoDAO;
import com.example.demo.models.Empleado;
import com.example.demo.services.IEmpleadoService;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
	
	@Autowired
	private IEmpleadoDAO empleadoDao;

	@Override
	public void save(Empleado empleado) {
		empleadoDao.save(empleado);
	}

	@Override
	public List<Empleado> listadoEmpleados() {
		return empleadoDao.findAll();
	}

	@Override
	public Empleado findById(Long id) {
		return empleadoDao.getOne(id);
	}

	@Override
	public void delete(Long id) {
		empleadoDao.deleteById(id);
	}
	
	

}
