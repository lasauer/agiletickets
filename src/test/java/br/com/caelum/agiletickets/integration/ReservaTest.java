package br.com.caelum.agiletickets.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.agiletickets.integration.driver.EstabelecimentosDriver;

public class ReservaTest {
	
	public static String BASE_URL = "http://localhost:8080";
	private static WebDriver browser;
	private ReservaDriver reservas;

	@Before
	public void setUp() throws Exception {
		browser = new FirefoxDriver();
		reservas = new ReservaDriver(browser);
	}
	
	@After
	public void tearDown() throws Exception {
		browser.close();
	}
	
	@Test
	public void testaReservaDeEntradaComDesconto() {
		reservas.abreAPaginaComAsSessoes();
		reservas.selecioneQueQuerDoisIngressosDeEstudante();
		reservas.reserveOsDoisIngressos();
		reservas.precoDeDoisIngressosCom70PorcentoDoPrecoIntegralSera(14);		
	}

}
