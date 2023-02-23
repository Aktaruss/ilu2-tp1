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
		private Etal[] etals;

		private Marche(int nbEtal) {
			etals = new Etal[nbEtal];
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			int indiceEtalLibre = -1;
			for (int i = 0; i < etals.length && indiceEtalLibre == -1; i++) {
				if (!etals[i].isEtalOccupe()) {
					indiceEtalLibre = i;
				}
			}
			return indiceEtalLibre;

		}

		private Etal[] trouverEtal(String produit) {
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

		private Etal trouverVendeur(Gaulois gaulois) {
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

		private String afficherMarche() {
			int nbEtalvide = 0;
			StringBuilder chaine = new StringBuilder();
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					nbEtalvide++;
				}
			}
			chaine.append("Il reste " + nbEtalvide + " etals non utilises dans le marche.\n");
			return chaine.toString();
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
		int indiceEtal = marche.trouverEtalLibre();
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		if (indiceEtal == -1) {
			chaine.append("Il n y a plus de place dans ce marche pour le vendeur " + vendeur.getNom()
					+ " il repars donc sans etal.\n");
		} else {
			marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
			chaine.append(
					"Le vendeur " + vendeur.getNom() + " vend des " + produit + " a l etal n° " + indiceEtal + ".\n");
		}
		return chaine.toString();
	}

	public String rechercherVendeurProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalproduit = marche.trouverEtal(produit);
		chaine.append("Les vendeurs qui proposent des " + produit + " sont:\n");
		for (int i = 0; i < etalproduit.length; i++) {
			chaine.append("- " + etalproduit[i].getVendeur().getNom() + "\n");
		}
		return chaine.toString();
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
		Etal etal = rechercherEtal(vendeur);
		StringBuilder chaine = new StringBuilder();
		chaine.append(etal.libererEtal());
		return chaine.toString();

	}

	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
}