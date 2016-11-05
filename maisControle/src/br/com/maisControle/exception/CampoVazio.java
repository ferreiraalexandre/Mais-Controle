package br.com.maisControle.exception;

public class CampoVazio extends ProdutoException {
	
	private static final long serialVersionUID = 1L;
	
	public CampoVazio() {
		super("Todos os campos são obrigatórios o preenchimento");
	}
	public CampoVazio(String msg) {
		super(msg);
	}

	public CampoVazio(String msg,Throwable t) {
		super(msg, t);
	}

	public CampoVazio(Throwable t) {
		super("Erro", t);
	}
}
	
