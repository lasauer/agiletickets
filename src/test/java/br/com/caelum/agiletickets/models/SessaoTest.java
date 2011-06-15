package br.com.caelum.agiletickets.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class SessaoTest {


	@Test
	public void naoDeveVender3ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		assertThat(sessao.podeReservar(3), is(false));
	}
	
	@Test
	public void deveVender2ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		assertThat(sessao.podeReservar(2), is(true));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		assertThat(sessao.getIngressosDisponiveis().intValue(), equalTo(2));
	}
	

}
