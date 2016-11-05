package br.com.maisControle.exception;

public class ProdutoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProdutoException(String msg) {
		super(msg);
	}

	public ProdutoException(String msg,Throwable t) {
		super(msg, t);
	}

	public ProdutoException(Throwable t) {
		super("Erro de Conexão", t);
	}
}
