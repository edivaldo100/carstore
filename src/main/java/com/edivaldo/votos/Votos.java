package com.edivaldo.votos;

public class Votos {
	public static final double totalDeVotos = 1000;
	
	public static void main(String[] args) {
		double votosBrancos = new VotosBrancos().getVotos();
		double votosNulos = new VotosNulos().getVotos();
		double votosValidos = new VotosValidos().getVotos();
		System.out.println("PERCENTUAL DE VOTOS BRANCOS "+new Votos().calc(votosBrancos)+" %");
		System.out.println("PERCENTUAL DE VOTOS NULOS "+new Votos().calc(votosNulos)+" %");
		System.out.println("PERCENTUAL DE VOTOS VALIDOS "+new Votos().calc(votosValidos)+" %");
	}
	
	private int calc(double voto) {
		return  (int) ((voto/totalDeVotos) * 100);
	}
}
