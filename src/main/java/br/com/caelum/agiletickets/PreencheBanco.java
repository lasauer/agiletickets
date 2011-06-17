package br.com.caelum.agiletickets;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;
import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;
import br.com.caelum.vraptor.util.jpa.EntityManagerCreator;
import br.com.caelum.vraptor.util.jpa.EntityManagerFactoryCreator;

public class PreencheBanco {
	
	private final EntityManager manager;
	
	public PreencheBanco () {
		EntityManagerFactoryCreator creator= new EntityManagerFactoryCreator();
		creator.create();
		EntityManagerCreator managerCreator= new EntityManagerCreator(creator.getInstance());
		managerCreator.create();
		manager = managerCreator.getInstance();
		
	}

	public static void main(String[] args) {
		PreencheBanco preencheBanco = new PreencheBanco();
		preencheBanco.inicializaBanco();
	}

	private void inicializaBanco() {
		manager.getTransaction().begin();
		limpaBanco();

		String nomeEstabelecimento = "Casa de shows";
		String enderecoEstabelecimento = "Rua dos Silveiras, 12345";
		Estabelecimento estabelecimento = criaEstabelecimento(nomeEstabelecimento, enderecoEstabelecimento);

		String nomeEspetaculo = "Depeche Mode";
		Espetaculo espetaculo = criaEspetaculo(estabelecimento, nomeEspetaculo);
		
		criaSessoes(espetaculo, 10);
		
		manager.getTransaction().commit();
		manager.close();
	}

	private void criaSessoes(Espetaculo espetaculo, int numeroSessoes) {
		for (int i = 0; i < numeroSessoes; i++) {
			Sessao sessao = new Sessao();
			sessao.setEspetaculo(espetaculo);
			sessao.setInicio(new DateTime().plusDays(7+i));
			sessao.setDuracaoEmMinutos(60 * 3);
			sessao.setTotalIngressos(10);
			sessao.setIngressosReservados(10 - i);
			sessao.setPreco(new BigDecimal("10"));
			manager.persist(sessao);
		}
	}

	private Espetaculo criaEspetaculo(Estabelecimento estabelecimento, String nome) {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setEstabelecimento(estabelecimento);
		espetaculo.setNome(nome);
		espetaculo.setTipo(TipoDeEspetaculo.SHOW);
		manager.persist(espetaculo);
		return espetaculo;
	}

	private Estabelecimento criaEstabelecimento(String nomeEstabelecimento, String enderecoEstabelecimento) {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome(nomeEstabelecimento);
		estabelecimento.setEndereco(enderecoEstabelecimento);
		manager.persist(estabelecimento);
		return estabelecimento;
	}

	private void limpaBanco() {
		manager.createQuery("delete from Sessao").executeUpdate();
		manager.createQuery("delete from Espetaculo").executeUpdate();
		manager.createQuery("delete from Estabelecimento").executeUpdate();
	}
}
