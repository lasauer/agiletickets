package br.com.caelum.agiletickets.integration;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservaDriver {

	private static final String BASE_URL = "http://localhost:8080";
	private final WebDriver driver;

	public ReservaDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void abreAPaginaComAsSessoes() {
		driver.get(BASE_URL + "/reservas");
		
	}

	public void selecioneQueQuerDoisIngressosDeEstudante() {
		form().findElement(By.name("reserva.quantidade")).sendKeys("2");
	}

	public void reserveOsDoisIngressos() {
		form().submit();
	}

	public void precoDoIngressoDeveSer70PorcentoDoPrecoIntegral() {
		String message = driver.findElement(By.id("message")).getText();
		String words[] = message.split(" ");
		String price = words[words.length];
		
		assertThat(price, is("10,00"));
	}

	public void precoDeDoisIngressosCom70PorcentoDoPrecoIntegralSera(float valor) {
	}
	
	private WebElement form() {
		return driver.findElement(By.id("addForm"));
	}


}
