package controller;

import java.util.concurrent.Semaphore;

public class ThreadProcesso extends Thread {
	private int id;
	private Semaphore semaforo;

	
	public ThreadProcesso(int id, Semaphore semaforo){
		this.semaforo = semaforo;
		this.id = id;
	}

	
	@Override
	public void run() {
		switch (CalculaRestoId()) {
		case 1:
			for(int i = 0; i< 2; i++){
				this.Calculos(0.2000, 1.000);
				this.Guardando(1);
			}
			break;
		case 2: 
			for(int i = 0; i< 3; i++){
				this.Calculos(0.500, 1.500);
				this.Guardando(1.500);
			}
			break;
		case 0: 
			for(int i = 0; i< 3; i++){
				this.Calculos(1.000, 2.000);
				this.Guardando(1.50);
			}
			break;
		}
	}
	
	
	private int CalculaRestoId(){
		return id % 3;
	}
	
	private void Calculos(double inferior, double superior){
		System.out.println("Thread: #" + id + " está calculando");
		double random = Math.random();
		double multiplicador = (random > (superior - inferior)) ? (superior - inferior) : random;
		long tempo = (long) ((multiplicador + inferior)*1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void Guardando(double tempo){
		try {
			semaforo.acquire();
			System.out.println("Thread: #" + id + " está guardando no banco");
			Thread.sleep((long) tempo*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		finally {
			semaforo.release();
		}
		System.out.println("Thread: #" + id + " guardado no banco");
	}
}
