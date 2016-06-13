import java.util.Random;

public class NumerosTeste {
	
	public static int[] gerar(int inicio, int fim, int totalNumeros){
		Random gerador = new Random();
		int[] lista = new int[totalNumeros];
		for (int i = 0; i < lista.length; i++) 
			lista[i] = gerador.nextInt(fim-inicio)+inicio;
		return lista;
	}
}
