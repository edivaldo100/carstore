package com.edivaldo.soma_multimos_3e5;

public class Multiplos3e5 {

	public static void main(String[] args) {
	
        System.out.println("A soma dos multiplos de 3 e 5 abaixo de 10, é: " +calculador(10));

        System.out.println("A soma dos multiplos de 3 e 5 abaixo de 100, é: " +calculador(100));
        
        System.out.println("A soma dos multiplos de 3 e 5 abaixo de 1000, é: " +calculador(1000));
	}
	
	static int calculador(int valor){
		int x = 3;
        int z = 5;
		int res = 0;
        for(int i = 0; i < valor; i++){
        	if(i % x == 0 || i % z == 0){
        		res += i;
        	}
        }
		return res;
	}
}
