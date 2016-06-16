package hashing.aberta;

public class TesteAberta {
	public static void main(String[] args) {
		OpenHashing op = new OpenHashing(13);
		op.inserir(12);
		op.inserir(21);
		op.inserir(13);
		op.inserir(81);
		op.inserir(19);
		op.inserir(17);
		op.inserir(21);
		op.inserir(29);
		op.inserir(11);
		op.inserir(2);
		op.inserir(12);
		op.inserir(18);
		op.inserir(41);
		op.inserir(9);
		op.inserir(23);
		op.inserir(17);
		op.inserir(3);
		op.inserir(8);
		op.inserir(11);
		op.inserir(11);
		op.inserir(15);
		op.inserir(124);
		op.inserir(189);
		op.inserir(89);
		
		op.exibir();
		System.out.println("\n"+op.getFatorDeCarga());
		System.out.println();
		op.deletar(11);
		
		op.exibir();
		
		System.out.println("\n"+op.getFatorDeCarga());
	}
}
