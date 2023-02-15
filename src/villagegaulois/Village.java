package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbPlaceMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbPlaceMarche);
	}

	public class Marche {
		public Etal[] etals;

		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		public int trouverEtalLibre() {
			int indiceEtalLibre = -1;
			for (int i = 0; i < etals.length && indiceEtalLibre == -1; i++) {
				if (!etals[i].isEtalOccupe()) {
					indiceEtalLibre = i;
				}
			}
			return indiceEtalLibre;

		}

		public Etal[] trouverEtal(String produit) {
			int nbEtalProduit = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					nbEtalProduit++;

				}
			}
			Etal[] etalsProduits = new Etal[nbEtalProduit];
			if (nbEtalProduit != 0) {
				int j = 0;
				for (int i = 0; i < etals.length; i++) {
					if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
						etalsProduits[j] = etals[i];
						j++;

					}
				}
			}
			return etalsProduits;
		}

		public Etal trouverVendeur(Gaulois gaulois) {
			int indiceEtalVendeur = -1;
			for (int i = 0; i < etals.length && indiceEtalVendeur == -1; i++) {
				if (etals[i].getVendeur() == gaulois) {
					indiceEtalVendeur = i;
				}
			}
			if (indiceEtalVendeur == -1) {
				return null;
			} else {
				return etals[indiceEtalVendeur];
			}
		}

		public void afficherMarche() {
			for (int i = 0; i < etals.length; i++) {
				etals[i].afficherEtal();
			}
		}

	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		return chaine.toString();
	}
}