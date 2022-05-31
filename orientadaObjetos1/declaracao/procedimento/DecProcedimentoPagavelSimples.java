package loo2.plp.orientadaObjetos1.declaracao.procedimento;
import loo2.plp.expressions2.memory.VariavelJaDeclaradaException;
import loo2.plp.expressions2.memory.VariavelNaoDeclaradaException;
import loo2.plp.orientadaObjetos1.comando.Comando;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import loo2.plp.orientadaObjetos1.expressao.leftExpression.Id;
import loo2.plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;

public class DecProcedimentoPagavelSimples extends DecProcedimentoSimples {

	public DecProcedimentoPagavelSimples(Id nome, ListaDeclaracaoParametro parametrosFormais, Comando comando) {
		super(nome, parametrosFormais, comando);
		// TODO Auto-generated constructor stub
	}

	public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException, ProcedimentoJaDeclaradoException,
			ProcedimentoNaoDeclaradoException, ClasseNaoDeclaradaException, ClasseJaDeclaradaException {

		
		//HFA - Checa se os parâmetros destino e valor foram fornecidos ou não na declaração do procedimento pagável.
		boolean hasDestino = false;
		boolean hasValor = false;

		loo2.plp.imperative1.util.Lista<DecParametro> auxParametros = parametrosFormais;
		DecParametro auxHead = parametrosFormais.getHead();

		while (!(auxHead == null)) {

			if (auxHead.getId().getIdName().equals("origem") && auxHead.getTipo().toString().equals("int")) {
				hasDestino = true;
			}

			if (auxHead.getId().getIdName().equals("valor") && auxHead.getTipo().toString().equals("int")) {
				hasValor = true;
			}

			if (auxParametros.getTail() == null) {
				break;
			}

			auxParametros = auxParametros.getTail();
			auxHead = auxParametros.getHead();

		}

		if (!(hasDestino && hasValor)) {
			throw new VariavelNaoDeclaradaException(new loo2.plp.expressions2.expression.Id("Variáveis Obrigatórias 'origem' e 'valor' não declaradas."));
		}

		boolean resposta;
		if (parametrosFormais.checaTipo(ambiente)) {
			ambiente.mapParametrosProcedimento(nome, parametrosFormais);
			ambiente.incrementa();
			ambiente = parametrosFormais.declaraParametro(ambiente);
			resposta = comando.checaTipo(ambiente);
			ambiente.restaura();
		} else {
			resposta = false;
		}
		
		return resposta;
		
	}

}
