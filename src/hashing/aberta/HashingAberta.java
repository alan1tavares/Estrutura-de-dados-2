package hashing.aberta;

public abstract class HashingAberta {
	public abstract void inserir(int valor);
	public abstract void deletar(int valor);
	public abstract double fatorDeBalanceamento();
	public abstract void exibir();
	public abstract int getFatorDeCarga();
}
