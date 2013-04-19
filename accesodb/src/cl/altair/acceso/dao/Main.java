package cl.altair.acceso.dao;

import java.util.List;


import cl.altair.acceso.modelo.Region;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Busco la aplicacion en la BD
		RegionDAO adao = new RegionDAO();
		List<Region> todoList = adao.findAll();
	    for (Region todo : todoList) {
	      System.out.println(todo.getRegionNombre());
	    }
	    System.out.println("Size: " + todoList.size());

	    // Create new todo

	}

}
