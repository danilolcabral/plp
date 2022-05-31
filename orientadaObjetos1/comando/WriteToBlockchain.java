package loo2.plp.orientadaObjetos1.comando;


import loo2.plp.expressions2.memory.VariavelJaDeclaradaException;
import loo2.plp.expressions2.memory.VariavelNaoDeclaradaException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import loo2.plp.orientadaObjetos1.excecao.execucao.EntradaInvalidaException;
import loo2.plp.orientadaObjetos1.expressao.Expressao;
import loo2.plp.orientadaObjetos1.expressao.valor.Valor;
import loo2.plp.orientadaObjetos1.expressao.valor.ValorInteiro;
import loo2.plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import loo2.plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;

public class WriteToBlockchain implements IO {

	private Expressao endereco;
	private Expressao valor;
	
	public WriteToBlockchain(Expressao endereco, Expressao valor) {
		this.endereco = endereco;
		this.valor = valor;
	}

	@Override
	public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException, EntradaInvalidaException {
		
		Valor enderecoTmp = endereco.avaliar(ambiente);
		Valor valorTmp = valor.avaliar(ambiente);
		
		ambiente.getBlockchain().put(((ValorInteiro) enderecoTmp).valor(), ((ValorInteiro) valorTmp).valor());
		
		return ambiente;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException, ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
		return endereco.checaTipo(ambiente) && valor.checaTipo(ambiente);
	}

}
