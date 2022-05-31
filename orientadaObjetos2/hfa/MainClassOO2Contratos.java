package loo2.plp.orientadaObjetos2.hfa;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import loo2.plp.orientadaObjetos2.Programa;
import loo2.plp.orientadaObjetos2.parser.OO2Parser;

public class MainClassOO2Contratos {

	private static OO2Parser oo2Parser = null;

	public static void main(String[] args) {

		try {
			Path filePath = Path.of("programa4.poc2");
			String sourceCode = Files.readString(filePath);
			String listaEntrada = "";
			// Formato: "endereco:saldo endereco2:saldo2"
			String blockChain = "9999:100 9998:200 9997:300";
			ByteArrayInputStream fis = new ByteArrayInputStream(sourceCode.getBytes());

			interpretarOO2(fis, listaEntrada, blockChain);

		} catch (Exception e) {
			System.err.println("Deu pau...");
			e.printStackTrace();
		}

	}

	private static void interpretarOO2(InputStream fis, String entradaStr, String strchain) throws Exception {
		Programa prog;
		
		if (oo2Parser == null) {
			oo2Parser = new OO2Parser(fis);
		} else {
			oo2Parser.ReInit(fis);
		}
		
		prog = oo2Parser.processaEntrada();

		//System.out.println("sintaxe verificada com sucesso!\n");

		loo2.plp.orientadaObjetos1.memoria.colecao.ListaValor entrada = obterListaEntradaOO2(entradaStr);

		HashMap<Integer, Integer> blockchain = montarBlockChainFromString(strchain);

		if (prog.checaTipo(new loo2.plp.orientadaObjetos2.memoria.ContextoCompilacaoOO2(entrada, blockchain))) {
			prog.executar(new loo2.plp.orientadaObjetos2.memoria.ContextoExecucaoOO2(entrada, blockchain), blockchain).toString();
		} else {
			System.out.println("erro de tipos!");
		}
	}

	@SuppressWarnings("unchecked")
	private static loo2.plp.orientadaObjetos1.memoria.colecao.ListaValor obterListaEntradaOO2(String texto) {
		List valores = new LinkedList<loo2.plp.expressions2.expression.ValorConcreto>();
		loo2.plp.orientadaObjetos1.memoria.colecao.ListaValor entrada = new loo2.plp.orientadaObjetos1.memoria.colecao.ListaValor();
		StringTokenizer parser = new StringTokenizer(texto);

		while (parser.hasMoreTokens()) {
			String parametro = parser.nextToken();

			try {
				Integer inteiro = Integer.valueOf(parametro);
				valores.add(new loo2.plp.orientadaObjetos1.expressao.valor.ValorInteiro(inteiro.intValue()));
				continue;
			} catch (NumberFormatException e) {

			}

			if (parametro.equalsIgnoreCase("true") || parametro.equalsIgnoreCase("false")) {
				Boolean booleano = Boolean.valueOf(parametro);
				valores.add(new loo2.plp.orientadaObjetos1.expressao.valor.ValorBooleano(booleano.booleanValue()));
			} else {
				valores.add(new loo2.plp.orientadaObjetos1.expressao.valor.ValorString(parametro));
			}
		}
		entrada = OO2Parser.criaListaValor(valores);
		return entrada;
	}

	public static HashMap<Integer, Integer> montarBlockChainFromString(String strchain) {
		HashMap<Integer, Integer> blockchain = new HashMap<>();

		String[] blocos = strchain.split(" ");

		for (int i = 0; i < blocos.length; i++) {
			String[] bloco = blocos[i].split(":");
			try {
				Integer endereco = Integer.parseInt(bloco[0]);
				Integer saldo = Integer.parseInt(bloco[1]);

				blockchain.put(endereco, saldo);
			} catch (NumberFormatException ex) {
				System.out.println("Deu pau na conversão da string para o hashmap!");
			}

		}

		return blockchain;
	}

}
