package suite;

import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import page.LoginPage;
import tests.ResumoTest;
import tests.SaldoTest;

@Suite
@SelectClasses({
//        ContaTest.class,
//        MovimentacaoTest.class,
//        RemoverMovimentacaoContaTest.class,
        SaldoTest.class,
        ResumoTest.class
        })
public class SuiteGeral {

        private static LoginPage page = new LoginPage();

        @BeforeAll
        static void tetete(){
                System.out.println("Inside the before all");
                page.acessarTelaInicial();
                page.setEmail("gabriel@junduriann");
                page.setSenha("123456");
                page.entrar();
                page.resetar();
        }
//
//        @BeforeEach
//        public void BeforeEachSuite(){
//                System.out.println("before each da suite");
//        }
//
//        @AfterEach
//        public void AfterEachSuite(){
//                System.out.println("after each da suite");
//        }
//
//        @AfterAll
//        static void AfterAllSuite(){
//                System.out.println("After all da suite");
//        }
}
