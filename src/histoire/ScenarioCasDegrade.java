package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois asterix = new Gaulois("Asterix",10);
//		etal.libererEtal();
		System.out.println(etal.acheterProduit(-1, asterix));
		System.out.println("Fin du test");
		}
}
