package br.com.caelum.agiletickets.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void criarUmaSessaoParaOEspetaculo() {
		Espetaculo esp = new Espetaculo();

		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate();
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;

		List<Sessao> listaDeSessoes = esp.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertThat(listaDeSessoes.isEmpty(), is(false));
		assertThat(listaDeSessoes.size(), is(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPodeCriarUmaSessaoComDataInicialMaiorOuIgualDataFinal() {
		Espetaculo esp = new Espetaculo();

		LocalDate fim = new LocalDate();
		LocalDate inicio = new LocalDate().plusDays(1);
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;

		List<Sessao> listaDeSessoes = esp.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertThat(listaDeSessoes.isEmpty(), is(true));
	}
	
	@Test
	public void deveCriar2SessaoNoIntervaloDe2DiaDeSessaoDiaria() {

		Espetaculo esp = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().plusDays(1);
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;

		List<Sessao> listaDeSessoes = esp.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertThat(listaDeSessoes.size(), is(2));
	}


	
}
