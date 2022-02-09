package core;

public class Propriedades {

    public static boolean FECHAR_BROWSER_DPS_CADA_TESTE = true;

    public static Browsers browsers = Browsers.CHROME;

    public static TipoExecucao TIPO_EXECUCAO = TipoExecucao.GRID;
//    public static String NOME_CONTA_ALTERADA = "Conta Alterada" + System.nanoTime();

    public enum Browsers{
        CHROME,
        FIREFOX
    }

    public enum TipoExecucao{
        LOCAL,
        GRID
    }
}
