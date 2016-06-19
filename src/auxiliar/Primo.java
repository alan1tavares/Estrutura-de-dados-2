package auxiliar;
/**
 * Clase para saber se um numero e primo
 * */

public class Primo {
	
	// Verdade se um numero for primo
	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	// Busca o primo anterior
	public static int primoAnterior(int n){
		for (int i = n-1; i >= 2; i--) {
			if(isPrime(i))
				return i;
		}
		return 2;
	}
}
