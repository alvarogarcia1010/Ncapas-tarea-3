package com.uca.capas.Tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/ingresar")
	public String ingresar() {
		return "ingresar";
	}
	
	int parseWithDefault(String s, int def) {
	    try {
	        return Integer.parseInt(s);
	    }
	    catch (NumberFormatException e) {
	        return def;
	    }
	}
	
	@RequestMapping("/validacion")
	public ModelAndView parametros2(
		@RequestParam String names, 
		@RequestParam String surnames,
		@RequestParam String birthdate,
		@RequestParam String birthplace,
		@RequestParam String institute,
		@RequestParam String phoneNumber,
		@RequestParam String mobilePhoneNumber
	) throws ParseException {
		ModelAndView mav = new ModelAndView();
		List<String> lista = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 

		if(names.length() > 25) {
			lista.add("El campo Nombres no puede ser mayor a 25 caracteres");
		}
		
		if(names.length() < 1) {
			lista.add("El campo Nombres no puede estar vacio");
		}
		
		if(surnames.length() > 25) {
			lista.add("El campo Apellidos no puede ser mayor a 25 caracteres");
		}
		
		if(surnames.length() < 1) {
			lista.add("El campo Apellidos no puede estar vacio");
		}
		
		if(!birthdate.isEmpty()) {
			Date date1 = sdf.parse(birthdate);
			Date date2 = sdf.parse("2003-01-01");
			
			if(date1.compareTo(date2) > 0) {
				lista.add("El campo Fecha de nacimiento no puede ser menor a 01-01-2003");
			}

		}else {
			lista.add("El campo Fecha de nacimiento no puede estar vacio");
		}
		

		if(birthplace.length() > 25) {
			lista.add("El campo Lugar de nacimiento no puede ser mayor a 25 caracteres");
		}
		
		if(birthplace.length() < 1) {
			lista.add("El campo Lugar de nacimiento no puede estar vacio");
		}
		
		if(institute.length() > 100) {
			lista.add("El campo Instituto o Colegio de procedencia  no puede ser mayor a 100 caracteres");
		}
		
		if(institute.length() < 1) {
			lista.add("El campo Instituto o Colegio de procedencia  no puede estar vacio");
		}
		
		if(parseWithDefault(phoneNumber, 0) == 0) {
			lista.add("El campo Telefono fijo debe ser numerico");	
		}
		
		if(parseWithDefault(mobilePhoneNumber, 0) == 0) {
			lista.add("El campo Telefono movil debe ser numerico");	
		}
		
		if(!(phoneNumber.length() == 8)) {
			lista.add("El campo Telefono fijo debe tener exactamente 8 numeros");
		}
		
		if(!(mobilePhoneNumber.length() == 8)) {
			lista.add("El campo Telefono fijo debe tener exactamente 8 numeros");
		}
		
		mav.addObject("errores", lista);
		mav.setViewName("validacion");
		return mav;
	}
}
