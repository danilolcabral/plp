# Paradigma Orientado a Contratos
Projeto da Disciplina Paradigmas de Linguagens de Programação do CIn UFPE, que tem como objetivo estender a linguagem orientada a objetos para suportar algumas das principais características do paradigma orientado a contratos.

# BNF
Programa ::= "{" Declaracao ";" Comando "}"

Declaracao ::= DecClasse  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| DecClasse "," Declaracao

DecClasse ::= DecClasseTradicional  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| [DecClasseContrato](https://github.com/danilolcabral/plp/blob/main/orientadaObjetos2/declaracao/classe/DecClasseContratoSimplesOO2.java)

Comando ::= Atribuicao  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| ComDeclaracao  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| While  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| IfTheElse  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| IO  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| Skip  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| New  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| ChamadaMetodo  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| Comando ";" Comando
           
IO ::= "write" "(" Expressao ")"  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| "read" "(" Id ")"  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| "[escreverNaBlockchain](https://github.com/danilolcabral/plp/blob/main/orientadaObjetos1/comando/WriteToBlockchain.java)" "(" Expressao ")"  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| "[lerDaBlockchain](https://github.com/danilolcabral/plp/blob/main/orientadaObjetos1/comando/ReadFromBlockchain.java)" "(" Expressao ")"  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| "[transferir](https://github.com/danilolcabral/plp/blob/main/orientadaObjetos1/comando/TransferAtBlockchain.java)" "(" Expressao ")"  
     
DecClasseTradicional ::= "classe" Id ["extends"  Id] {" DecVariavel ";" DecConstrutor "," DecProcedimentoTradicional "}"

[DecClasseContrato](https://github.com/danilolcabral/plp/blob/main/orientadaObjetos2/declaracao/classe/DecClasseContratoSimplesOO2.java) ::= "classe" "contrato" Id {" DecVariavel ";" DecConstrutor "," DecProcedimento "}"

DecProcedimento ::= DecProcedimentoTradicional  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| [DecProcedimentoPagavel](https://github.com/danilolcabral/plp/blob/main/orientadaObjetos1/declaracao/procedimento/DecProcedimentoPagavelSimples.java)  
&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;| DecProcedimento "," DecProcedimento

DecProcedimentoTradicional ::= "proc" Id "(" [ListaDeclaracaoParametro] ")" "{" Comando "}"

[DecProcedimentoPagavel](https://github.com/danilolcabral/plp/blob/main/orientadaObjetos1/declaracao/procedimento/DecProcedimentoPagavelSimples.java) ::= "proc" "pagavel" Id "(" ParametrosPagaveis [ListaDeclaracaoParametro] ")" "{" Comando "}"

# Equipe
- Danilo Rafael de Lima Cabral - drlc@cin.ufpe.br
- Henrique Ferraz Arcoverde - hfa@cin.ufpe.br
