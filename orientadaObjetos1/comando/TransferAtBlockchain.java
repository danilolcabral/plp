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

public class TransferAtBlockchain implements IO {

	private Expressao enderecoOrigem;
	private Expressao enderecoDestino;
	private Expressao valor;

	public TransferAtBlockchain(Expressao enderecoOrigem, Expressao enderecoDestino, Expressao valor) {
		this.enderecoOrigem = enderecoOrigem;
		this.enderecoDestino = enderecoDestino;
		this.valor = valor;
	}

	@Override
	public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException, EntradaInvalidaException {

		Valor enderecoOrigemTmp = enderecoOrigem.avaliar(ambiente);
		Valor enderecoDestinoTmp = enderecoDestino.avaliar(ambiente);
		
		Integer saldoOrigem = ambiente.getBlockchain().get(((ValorInteiro) enderecoOrigemTmp).valor());
		Integer saldoDestino = ambiente.getBlockchain().get(((ValorInteiro) enderecoDestinoTmp).valor());
		
		if (saldoOrigem != null) {
			
			if (saldoDestino == null) {
				saldoDestino = 0;
			}

			Valor valorTmp = valor.avaliar(ambiente);

			if (saldoOrigem >= ((ValorInteiro) valorTmp).valor()) {
				Integer valorFinal = saldoDestino.intValue() + ((ValorInteiro) valorTmp).valor();
				Integer valorFinalOrigem =  saldoOrigem - ((ValorInteiro) valorTmp).valor();
				
				//Debita da origem
				ambiente.getBlockchain().put(((ValorInteiro) enderecoOrigemTmp).valor(), valorFinalOrigem);
				//Credita no destino
				ambiente.getBlockchain().put(((ValorInteiro) enderecoDestinoTmp).valor(), valorFinal);

			} else {
				System.out.println("O endereço de origem '" + enderecoOrigemTmp + "' não possui saldo para realizar esta transferência.");
			}
		} else {
			System.out.println("O endereço de origem '" + enderecoOrigemTmp + "' não existe na blockchain.");
		}

		return ambiente;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException, ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
		return enderecoOrigem.checaTipo(ambiente) && enderecoDestino.checaTipo(ambiente) && valor.checaTipo(ambiente);
	}

}
