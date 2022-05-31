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
import loo2.plp.orientadaObjetos1.expressao.leftExpression.Id;
import loo2.plp.orientadaObjetos1.expressao.valor.Valor;
import loo2.plp.orientadaObjetos1.expressao.valor.ValorInteiro;
import loo2.plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import loo2.plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;

public class ReadFromBlockchain implements IO {

	private Expressao endereco;

	public ReadFromBlockchain(Expressao endereco) {
		this.endereco = endereco;
	}

	@Override
	public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException, EntradaInvalidaException {
		// TODO Auto-generated method stub

		Valor valorEndereco = endereco.avaliar(ambiente);
		
		Integer saldo = ambiente.getBlockchain().get(((ValorInteiro) valorEndereco).valor());
		
		if (saldo == null) {
			System.out.println("O endereço '" + ((ValorInteiro) valorEndereco).valor() + "' não encontra-se disponível na blockchain.");
		} else {
			System.out.println("O saldo relativo ao enderenço '" + ((ValorInteiro) valorEndereco).valor() + "' é de: '"+ saldo.intValue() +"'");			
		}
		
		
		return ambiente;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException, ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
		return endereco.checaTipo(ambiente);
	}

}
