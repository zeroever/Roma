package tests.userProgram;

import playingCards.*;
import tests.main.CardCode;
import Logic.Card;

public class CardCollection {
	
	public static Card getCard(int number) {
		Card card = null;
		if (number == CardCode.SICARIUS) {
			card = new Sicarius();
		} else if (number == CardCode.ARCHITECTUS) {
			card = new Architectus();
		} else if (number == CardCode.CONSILIARIUS) {
			card = new Consiliarius();
		} else if (number == CardCode.LEGAT) {
			card = new Legat();
		} else if (number == CardCode.GLADIATOR) {
			card = new Gladiator();
		} else if (number == CardCode.MERCATOR) {
			card = new Mercator();
		} else if (number == CardCode.CONSUL) {
			card = new Consul();
		} else if (number == CardCode.LEGIONARIUS) {
			card = new Legionarius();
		} else if (number == CardCode.NERO) {
			card = new Nero();
		} else if (number == CardCode.PRAETORIANUS) {
			card = new Praetorianus();
		} else if (number == CardCode.SCAENICUS) {
			card = new Scaenicus();
		} else if (number == CardCode.HARUSPEX) {
			card = new Haruspex();
		} else if (number == CardCode.SENATOR) {
			card = new Senator();
		} else if (number == CardCode.VELITES) {
			card = new Velites();
		} else if (number == CardCode.ESSEDUM) {
			card = new Essedum();
		} else if (number == CardCode.TRIBUNUS_PLEBIS) {
			card = new TribunusPlebis();
		} else if (number == CardCode.CENTURIO) {
			card = new Centurio();
		} else if (number == CardCode.AESCULAPINUM) {
			card = new Aesculapinum();
		} else if (number == CardCode.BASILICA) {
			card = new Basilica();
		} else if (number == CardCode.MACHINA) {
			card = new Machina();
		} else if (number == CardCode.FORUM) {
			card = new Forum();
		} else if (number == CardCode.MERCATUS) {
			card = new Mercatus();
		} else if (number == CardCode.ONAGER) {
			card = new Onager();
		} else if (number == CardCode.TEMPLUM) {
			card = new Templum();
		} else if (number == CardCode.TURRIS) {
			card = new Turris();
		}
		return card;
	}
}
