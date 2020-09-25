package view;

import java.util.concurrent.Semaphore;

import controller.ThreadProcesso;

public class Principal {

	public static void main(String[] args) {

		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int id= 0; id < 21; id++){
			Thread thread = new ThreadProcesso(id,semaforo);
			thread.start();

		}
		
	}

}
