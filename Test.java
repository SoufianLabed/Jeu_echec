
public class Test {
	public static void main(String[] args){
		// test pour la class fou

		/*
		Plateau p = new Plateau(); // on cree un plateau,
		Fou fou = new Fou(0,7,5); // un fou quon place en bas sur la diagonal,
		Case case_dep = new Case(0,fou,7,5); //on place le fou sur la case,
		p.setCase(case_dep, 61); //enfin, on met cette case sur le plateau.

		Pion juif = new Pion(1,6,4);
		Case case_dest = new Case(1,juif,6,4);
		p.setCase(case_dest, 52);

		//avant
		System.out.println(p.getCase(61).getPiece().getNom());
		System.out.println(p.getCase(52).getPiece().getNom());

		System.out.println(fou.deplacer(case_dest,p));

		//apres
		System.out.println(p.getCase(61).getPiece());
		System.out.println(p.getCase(52).getPiece());
		*/


		// Test pour la Tour

		/*
		Plateau p = new Plateau(); // on cree un plateau,
		Tour tour = new Tour(0,7,7); // une tour quon place en bas sur la diagonal,
		Case case_dep = new Case(0,tour,7,7); //on place la tour sur la case,
		p.setCase(case_dep, 63); //enfin, on met cette case sur le plateau.

		Pion juif = new Pion(1,6,6);
		Case case_dest = new Case(1,juif,6,7);
		p.setCase(case_dest, 55);

		//avant
		System.out.println(p.getCase(63).getPiece());
		System.out.println(p.getCase(55).getPiece());

		System.out.println(tour.deplacer(case_dest,p));

		//apres
		System.out.println(p.getCase(63).getPiece());
		System.out.println(p.getCase(55).getPiece());
		*/

		// Test de cavalier

		/*
		Plateau p = new Plateau(); // on cree un plateau,
		Cavalier cavalier = new Cavalier(0,7,6); // un cavalier quon place en bas sur la diagonal,
		Case case_dep = new Case(0,cavalier,7,6); //on place le Cavalier sur la case,
		p.setCase(case_dep, 62); //enfin, on met cette case sur le plateau.

		Pion juif = new Pion(1,5,7);
		Case case_dest = new Case(1,juif,5,7);
		p.setCase(case_dest, 47);

		//avant
		System.out.println(p.getCase(62).getPiece());
		System.out.println(p.getCase(47).getPiece());

		System.out.println(cavalier.deplacer(case_dest,p));

		//apres
		System.out.println(p.getCase(62).getPiece());
		System.out.println(p.getCase(47).getPiece());

		*/

		// Test pour le Roi

		/*
		Plateau p = new Plateau(); // on cree un plateau,
		Roi roi = new Roi(0,7,4); // un cavalier quon place en bas sur la diagonal,
		Case case_dep = new Case(0,roi,7,4); //on place le Cavalier sur la case,
		p.setCase(case_dep, 60); //enfin, on met cette case sur le plateau.

		Pion juif = new Pion(1,6,4);
		Case case_dest = new Case(1,juif,6,4);
		p.setCase(case_dest, 52);

		//avant
		System.out.println(p.getCase(60).getPiece());
		System.out.println(p.getCase(52).getPiece());

		System.out.println(roi.deplacer(case_dest,p));

		//apres
		System.out.println(p.getCase(60).getPiece());
		System.out.println(p.getCase(52).getPiece());

		*/

		//Test de initPiece

		/*
		Plateau p = new Plateau();
		p.initPiece();

		System.out.println(p.getCase(60).getPiece());
		System.out.println(p.getCase(59).getPiece());
		System.out.println(p.getCase(61).getPiece());
		*/

		//Test pour la Reine

		/*
		Plateau p = new Plateau(); // on cree un plateau,
		Reine reine = new Reine(0,7,3); // un cavalier quon place en bas sur la diagonal,
		Case case_dep = new Case(0,reine,7,3); //on place le Cavalier sur la case,
		p.setCase(case_dep, 59); //enfin, on met cette case sur le plateau.

		Pion juif = new Pion(1,5,1);
		Case case_dest = new Case(1,juif,5,1);
		p.setCase(case_dest, 41);

		//avant
		System.out.println(p.getCase(59).getPiece());
		System.out.println(p.getCase(41).getPiece());
		p.afficher();

		System.out.println(reine.deplacer(case_dest,p));

		//apres
		System.out.println(p.getCase(59).getPiece());
		System.out.println(p.getCase(41).getPiece());
		p.afficher();
		*/

		// test pour le pion

		/*
		Plateau p = new Plateau(); // on cree un plateau,
		Pion pion1 = new Pion(1,7,7); // un fou quon place en bas sur la diagonal,
		Case case_dep = new Case(1,pion1,7,7); //on place le fou sur la case,
		p.setCase(case_dep, 63); //enfin, on met cette case sur le plateau.

		Pion juif = new Pion(0,6,6);
		Case case_dest = new Case(1,juif,6,6);
		p.setCase(case_dest, 54);

		//avant
		System.out.println(p.getCase(63).getPiece().getNom());
		System.out.println(p.getCase(54).getPiece());
		p.afficher();

		System.out.println(pion1.deplacer(case_dest,p));

		//apres
		System.out.println(p.getCase(63).getPiece());
		System.out.println(p.getCase(54).getPiece());
		p.afficher();
		*/

		/*
		 Partie partie = new Partie();
		 partie.demarrer();
		 Pion pion2= new Pion(1,2,2);
	     partie.getPlateau().getCase(18).setPiece(pion2);

	     partie.getPlateau().afficher();
	     partie.jouerCoup();
	     */

		// test de verifEchec


		Partie partie = new Partie();

		partie.demarrer();
		partie.getPlateau().afficher();

		Tour tour = new Tour(1,5,7);
		Case case_dep = new Case(1,tour,5,7);
		partie.getPlateau().setCase(case_dep,47);

		Roi roi = new Roi(0,2,7);
		Case case_dest = new Case(0,roi,2,7);
		partie.getPlateau().setCase(case_dest,23);

		partie.getPlateau().afficher();

		//System.out.println(partie.verifEchec(partie.getJoueur(1)));

		partie.jouerCoup();



	}



}
