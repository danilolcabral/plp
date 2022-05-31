package loo2.plp.orientadaObjetos2.declaracao.classe;

import loo2.plp.expressions2.memory.VariavelJaDeclaradaException;
import loo2.plp.expressions2.memory.VariavelNaoDeclaradaException;
import loo2.plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import loo2.plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import loo2.plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import loo2.plp.orientadaObjetos1.expressao.leftExpression.Id;
import loo2.plp.orientadaObjetos2.declaracao.ConstrutorNaoDeclaradoException;
import loo2.plp.orientadaObjetos2.declaracao.DecConstrutor;
import loo2.plp.orientadaObjetos2.memoria.AmbienteCompilacaoOO2;

public class DecClasseContratoSimplesOO2 extends DecClasseSimplesOO2 {

	public DecClasseContratoSimplesOO2(Id nomeClasse, Id nomeSuperClasse, DecVariavel atributos,
			DecConstrutor construtor, DecProcedimento metodos) {
		super(nomeClasse, nomeSuperClasse, atributos, construtor, metodos);

	}

}
