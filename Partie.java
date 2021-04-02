import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Partie implements java.io.Serializable{

	private Joueur[] tabjoueur;
	private Plateau p;
	private boolean encours;
	//le truc qui va nous servir a sauvegarder et charger inchallah;


	// Constructeur
	public Partie() {

		this.p = null;
		this.encours = false;

	}

	//constructeur par recopie
	public Partie(Partie partie) {
        this.tabjoueur = partie.tabjoueur;
        this.p = new Plateau(partie.p);
        this.encours = partie.encours;
    }


	// fonction permettant de demarrer une nouvelle partie
	public void demarrer() {
		//creation et initialisation des joueurs

		this.tabjoueur = new Joueur[2];
		Joueur joueur1 = new Joueur();
		Joueur joueur2 = new Joueur();

		this.tabjoueur[0] = joueur1;
		this.tabjoueur[1] = joueur2;

		System.out.println("Joueur 1 :");
		joueur1.init();

		System.out.println("Joueur 2 :");
		joueur2.init();

		// si le joueur 1 choisi la couleur blanche, et que lautre joueur choisit aussi blanc, on recommence
		while(joueur2.getCouleur() == joueur1.getCouleur()){
			System.out.println("cette couleur est deja choisie par le joueur 1, donc recommence stp");
			joueur2.init();
		}

		System.out.println("Okk tout est bon");

		//creation et initialisation de lechiquier
		this.p = new Plateau();
		this.p.initPiece();
		this.encours = true;


	}


	public Plateau getPlateau() {
		return this.p;
	}

	public void setPlateau(Plateau p){
		this.p = p;
	}

	public Joueur getJoueur(int i){
		return this.tabjoueur[i];
	}

	//fonction permettant de poser une piece, et jouer un coup

	public void jouerCoup(/*Joueur joueur,*/) {

		//variable dont on aura besoin

		Plateau copiePlateau = new Plateau(this.p);
		boolean pasEchec = false;

		while( pasEchec == false){
			Partie partietest = new Partie(this);
			partietest.getPlateau().afficher();


			System.out.println("Quelle piece voulez-vous utiliser ? Entrez les coordoonees ligne et colonne.");
			System.out.println("Et pour le menu, tapez 4, pour continuer tapez entrer");

			// on demande au joueur de saisir les coordonees de la piece a deplacer.
			Scanner sc = new Scanner(System.in);

			String coord= sc.nextLine();
	        int lettre = coord.charAt(0); // conversion de la lettre en entier dans la table ascii
	        int chiffre = coord.charAt(1); // idem
	        String menu = coord; // on recup si le joueur veut demmander le menu
	        //System.out.println(lettre);
	        //System.out.println(chiffre);

	        while ((lettre <97 || lettre >104) || (chiffre < 48 || chiffre >56 ) || ( menu.equals("4"))) {
	        	// tant que la lettre nest pas comprise entre A et H, et que le chiffre nest pas compris entre 1 et 8
	        	// alors on recommence

	        	System.out.println("Saisie invalide, recommencez.");
	        	// on recommence la saisie

	    		sc = new Scanner(System.in);

	    		coord= sc.nextLine();
	            lettre = coord.charAt(0);
	            chiffre = coord.charAt(1);
	            menu = coord;

	        }


	        System.out.println("position recu 5 sur 5");
	       //System.out.println(lettre);
	       // System.out.println(chiffre);

	        // on enrengistre la position de depart.
	        int departChoisie = ((lettre-97) * 8) + chiffre-49;

	        System.out.println("vous avez choisit la piece : " + this.p.getCase(departChoisie).getPiece().getNom());
	        System.out.println("a la position " + departChoisie);


	        // maintenant, demandons au joueur dentrer la position de la piece a manger
	        System.out.println("Entrez les coordoonees ligne et colonne de la cible.");

			Scanner sc2 = new Scanner(System.in);

			String coord2= sc2.nextLine();
			int lettre2 = coord2.charAt(0); // conversion
			int chiffre2 = coord2.charAt(1); // idem
			String menu2 = coord; // on recup si le joueur veut demmander le menu
	        //System.out.println(lettre2);
	        //System.out.println(chiffre2);

	     // tant que la lettre nest pas comprise entre A et H, et que le chiffre nest pas compris entre 1 et 8
	     // alors on recommence

	        while ((lettre2 <97 || lettre2 >104) || (chiffre2 < 48 || chiffre2 >56 ) || ( menu2.equals("4"))) {
	        	System.out.println("Entrez les coordoonees ligne et colonne de la cible.");

	        	// on recommence
	    		sc2 = new Scanner(System.in);

	    		coord2= sc2.nextLine();
	    		lettre2 = coord2.charAt(0);
	    		chiffre2 = coord2.charAt(1);
	    		menu2 = coord;

	        }


	        int destChoisie = ((lettre2-97) * 8) + chiffre2-49; // on sauve la position de la case de destination.
			int couleurJactuel = this.p.getCase(departChoisie).getPiece().getCouleur(); // on recup la pos
	        // effectuons a present le deplacement a laide de cette position.
			partietest.getPlateau().getCase(departChoisie).getPiece().deplacer(partietest.getPlateau().getCase(destChoisie), partietest.getPlateau());
	        System.out.println("Ciblllage : " + destChoisie);

					if(couleurJactuel == 1) {
							if (partietest.verifEchec(this.tabjoueur[1])==false){
										pasEchec = true;
										this.p = new Plateau(partietest.getPlateau());

							}

						}

				else {
					if (partietest.verifEchec(this.tabjoueur[0])==false){
							pasEchec = true;
							this.p = new Plateau(partietest.getPlateau());
						}
					}


					System.out.println("Pour le menu, tapez 4, pour continuer tapez entrer");

					Scanner scanMenu = new Scanner (System.in);
					String menu3 = scanMenu.nextLine();

					if ( menu3.equals("4") ){
						menu();
					}

		}

		//System.out.println("Ici le vrai plateau");
		//this.p.afficher();

	}



	//cette fonction va nous dire si le roi est en echec ou pas
	public boolean verifEchec(Joueur joueur){
		boolean res = false;

		for(int i = 0; i < 64; i++) {
            if(this.p.getCase(i).getPiece() == null) {
                continue; // retourne a l'instruction de la boucle
            }

            if(this.p.getCase(i).getPiece().getNom() == "Roi") {
            	//si on tombe sur le roi, on sauve sa position
                int position_roi = p.getCase(i).getPositon();

                //on verifie si le roi trouver, est une couleur adverse au joueur
                if(joueur.getCouleur() != p.getCase(position_roi).getPiece().getCouleur()){

                	// on parcour le tab pour voir quelle piece il reste
                	for(int j = 0; j < 64; j++){
                		if(this.p.getCase(j).estOccupe()){
                			int piece_trouver = p.getCase(j).getPositon(); // on sauve sa position
                			//puis on calcul les coordonnees qui separe le roi de la piece trouver
                			int horizontal = Math.abs(this.p.getCase(position_roi).geti() - this.p.getCase(piece_trouver).geti());
                			int vertical = Math.abs(this.p.getCase(position_roi).getj() - this.p.getCase(piece_trouver).getj());

                			//ainsi, si lune des pieces peut manger le roi, alors celui ci est mis en echec
                			if(this.p.getCase(piece_trouver).getPiece().manger_possible(p.getCase(position_roi),horizontal,vertical)){
                				res = true;
                			}
                		}
                	}

                }
            }
        }

		return res;


	}


	// la fonction qui va nous permettre de sauvegarder une partie

	public void sauvegarder(String nomFichier) throws IOException{
		//On "ouvre" le fichier txt
		File fichier = new File(nomFichier);
		//Permet l'ecriture du text
		BufferedWriter ecrire = new BufferedWriter(new FileWriter(fichier));

		//Les deux premieres lignes du fichier sont consacrees aux joueurs
		ecrire.write(this.tabjoueur[0].getNom() + "," + this.tabjoueur[0].getCouleur() +"\n");
		ecrire.write(this.tabjoueur[1].getNom() + "," + this.tabjoueur[1].getCouleur() +"\n");

		for(int i = 0; i < 8; i++ ){
			for(int j = 0; j < 8; j++){
				String chaine = "";
				//Permet d'avoir la piece de la case courante
				Piece piece = this.p.getCase((i*8) + j).getPiece();
				if(j != 0) {
					//On rajoute une virgule pour separer les pieces
					chaine += ",";
				}

				if( piece != null ){
					//on recupere le nom et la couleur qu'on separe par un '-'
					String nom = piece.getNom();
					nom += "-"+piece.getCouleur();
					chaine += nom;
				}

				else {
					//On met dans la chaine 'vide' si la case == null
					chaine += "vide";
				}
				//On ecrit dans le fichier ce qu'on a recupere
				ecrire.write(chaine);
			}
			//On fait un retour a la ligne
			ecrire.write("\n");
		}
		ecrire.close();
		//Pour savoir ou le fichier de la partie a etait save
		System.out.println("La partie va etre sauvegarder ici: " + fichier.getAbsolutePath());
	}




	public void charger(String nomFichier) throws IOException {
		//On cree un plateau
		Plateau plateau = new Plateau();
		//On "ouvre" le fichier
		FileReader fichier = new FileReader(new File(nomFichier));
		//On permet la lecture du fichier
		BufferedReader lireFichier = new BufferedReader(fichier);
		//Comme les donnees des joueurs sont separees par une ","
		//Alors grace au split on va pouvoir separees les donnees dans un tableau
		String line = lireFichier.readLine();
		String[] parts = line.split(",");

		//On recupere le nom et la couleur du joueur
		String nom1 = parts[0];
		String couleur = parts[1];
		//On convertie la couleur en int
		int couleur1 = Integer.parseInt(couleur);
		Joueur joueur1 = new Joueur(nom1,couleur1);


		line = lireFichier.readLine();
		parts = line.split(",");

		String nom2 = parts[0];
		couleur = parts[1];
		int couleur2 = Integer.parseInt(couleur);
		Joueur joueur2 = new Joueur(nom2,couleur2);

		this.tabjoueur[0] = joueur1;
		this.tabjoueur[1] = joueur2;


		for(int i = 0; i < 8; i++) {
			line = lireFichier.readLine();
			//Les Pieces sont aussi separees par des ","
			//Va permettre de separer toutes les pieces d'une ligne
			String[] partsPiece = line.split(",");

			for(int j = 0; j < 8; j++){
				//Va permettre de placer la piece
				Case placementPiece = plateau.getCase((i*8)+j);
				//Comme les pieces sont separees on va maintenant recuperer
				//le nom de la piece et la couleur de la piece
				String[] partsCouleur = partsPiece[j].split("-");
				String piece = partsCouleur[0];

				//On regarde si piece est vide pour pouvoir remplacer ca par null dans le tableau
				if(piece.equals("vide")) {
					plateau.getCase((i*8)+j).setPiece(null);
				}

				else {
					//On convertis la couleur en int
					String couleurP = partsCouleur[1];
					int couleurPiece = Integer.parseInt(couleurP);

					//On verifie le nom de la piece
					//Puis on l'a place
					if(piece == "tour") {
						placementPiece.setPiece(new Tour(couleurPiece,i,j));
					}

					else if(piece == "cavalier") {
						placementPiece.setPiece(new Cavalier(couleurPiece,i,j));
					}

					else if(piece == "fou") {
						placementPiece.setPiece(new Fou(couleurPiece,i,j));
					}

					else if(piece == "reine") {
						placementPiece.setPiece(new Reine(couleurPiece,i,j));
					}

					else if(piece == "roi") {
						placementPiece.setPiece(new Roi(couleurPiece,i,j));
					}

					else if(piece == "pion"){
						placementPiece.setPiece(new Pion(couleurPiece,i,j));
					}
				}
			}
		}

		lireFichier.close();
		fichier.close();

		//setPlateau(plateau);
		//echec();
	}






	//methode permettant de verifier si le roi est en echec et mat
	public boolean mat( Joueur joueur){
		boolean res = false;

		Joueur joueuradverse;
		if (joueur.getCouleur()== 1)  {
			joueuradverse = tabjoueur[0];
			}
		else {
			joueuradverse = tabjoueur[1];
			}

		if (this.verifEchec(joueuradverse)&& this.pat(joueur)){
			res = true;
		}

		return res;
	}


	//methode qui va dire si il y a pat
	public boolean pat(Joueur joueur){
		// La on isntance tout ce qu'on aura besoin, c'est a dire le joueur adverse ( pck notre verifEchec il verifie si toi tu met en echec le joueur adverse), un booleen pour savoir si t'as plus queton roi ou aps, un pour savoir
		// si ton roi est bloque,  autrepiece lui c 'est pour savoir si t'as une piece en dehors de ton roi qui peut bouger, si oui bah t'es pas en PAT. Jroi i roi sa sera pour les test de deplacement du roi, positioncourantepiece
		// sa sera pour les pieces autre que le roi, pour savoir si elles ont pu bouger ou aps pendant les test.
		Joueur joueuradverse;
		if (joueur.getCouleur()== 1)  {
			joueuradverse = tabjoueur[0];
			}
		else {
			joueuradverse = tabjoueur[1];
			}

		boolean roiseul = true;
		boolean roiImmobiliser = true;
		boolean autrepiece = false;
		int positionApresDeplacement = 0;
		int positionpiececourante = 0;
		int position_roi = 0;
		int iroi = 0;
		int jroi = 0;
		//boolean pat = false;
		Partie partietest = new Partie(this);  // La on creer une copie complete du plateau actuel, on va faire tous les test dessus.


		for (int t = 0; t<64;t++){  // La on va deja commencer par chercher la position de notre roi et enregistrer ses coordonnees.
			if(this.p.getCase(t).estOccupe()== true) {
				if(this.p.getCase(t).getPiece().getNom().equals("Roi") && this.p.getCase(t).getPiece().getCouleur()==joueur.getCouleur()) {
					position_roi = this.p.getCase(t).getPositon();
					//System.out.println("Iroi"+iroi+"  jroi"+jroi+" jouer couleur ="+joueur.getCouleur());
					if(joueur.getCouleur() == this.p.getCase(position_roi).getPiece().getCouleur()) {

						iroi = this.p.getCase(position_roi).geti();
						jroi = this.p.getCase(position_roi).getj();


						}
					}
				}
			}


		int[] TabCol = {-1,0,1};   // Une fois qu'on a trouver ses positions on va commencer par essayer de le depalcer dans toutes les direcitons et voir si on est en echec quand on le fait.
		// Le TabCol sa sera pour tester les differents deplacement , par exemple un moment i dans le for juste en bas, sera egal a 1 et j passera par -1,0,1 donc sa testera en bas a gauche, en bas tout droit, en bas a droite etc.
		for (int i =0; i <3 ; i++){
			for (int j = 0 ; j<3 ; j++){
				partietest = new Partie(this); // La on va recreer le tableau de test comme si on avait rien bouger, entre chaque test, pck si il a reussi a bouger une fois et qu'on veut refaire un autre mouvement bah sa sera plus du bon endroit.
				//this.p = new Plateau(plateauCopie);

				if (jroi == 0 && TabCol[j]== -1) {  // La c pour dire en gros, si le roi il est tout a gauche deja, bah on va pas faire les test qui vont vers la gauche , pck si on le depalce en dehors du tableau, tout plante.


					}
				else if (TabCol[j]== 1 && jroi == 7) {  // La c pour dire en gros, si le roi il est tout a droite deja, bah on va pas faire les test qui vont vers la droite , pck si on le depalce en dehors du tableau, tout plante.


					}
				else if (TabCol[i]== -1 && iroi == 0) {

					  // La c pour dire en gros, si le roi il est tout en haut deja, bah on va pas faire les test qui vont vers le haut , pck si on le depalce en dehors du tableau, tout plante.

					}
				else if (TabCol[i]== 1 && iroi == 7 ) {

					  // La c pour dire en gros, si le roi il est tout a bas deja, bah on va pas faire les test qui vont vers la bas , pck si on le depalce en dehors du tableau, tout plante.

					}
				else { // C la qu'on samuse mdrlol.   Donc si on arrive la, cest qu'on peut essayer de faire un deplacement sans sortir du tableau.



					// La on va essayer de faire le deplacement du roi dans le tableau copie de test.
					partietest.getPlateau().getCase(position_roi).getPiece().deplacer(partietest.getPlateau().getCase(((iroi + TabCol[i])*8)+jroi+TabCol[j]),partietest.getPlateau());

					for (int t = 0; t<64;t++){
						// La on aurait pu faire plus simple mais bref. On va maintenant chercher la nouvelle position du roi apres le deplacement , dans le tableau test.
						if(partietest.getPlateau().getCase(t).estOccupe()== true) {
							if(partietest.getPlateau().getCase(t).getPiece().getNom().equals("Roi") && partietest.getPlateau().getCase(t).getPiece().getCouleur()==joueur.getCouleur()) {
								positionApresDeplacement =  partietest.getPlateau().getCase(t).getPositon();





								}
							}
						}


					// Une fois qu'on a trouver sa position, on regarde si elle differente de celle du debut du test, donc en gros sil a pu se deplacer ou pas,  et s'il a reussi a se deplacer, est ce que maintenant a sa nouvelle position il est en echec ou pas.
					if (partietest.getPlateau().getCase(position_roi).getPositon() != positionApresDeplacement && partietest.verifEchec(joueuradverse)== false) {
						//partietest.p.afficher();

						// S'il a bien bouge et qu'il est pas en echec a sa nouvelle position, bah il est pas immobilier et donc y'aura PAS PAT.
						roiImmobiliser = false;

						}
					}
				}


			}



		// Et c partiiiit. Maintenant on regarde si en dehors du roi , on a d'autre piece de notre couleur.
		Partie partietest2 = new Partie(partietest); // Donc la on recreer un nouveau tableau de test qui lui effectuera les test, DES AUTRES PIECES pas du roi.
		for (int t = 0; t<64;t++){

			if(partietest.getPlateau().getCase(t).estOccupe()== true) {
				if((! partietest.getPlateau().getCase(t).getPiece().getNom().equals("Roi") ) && partietest.getPlateau().getCase(t).getPiece().getCouleur()==joueur.getCouleur()) { // Donc la on cherche une piece differente de notre roi et qui a notre couleur.
					roiseul = false; // Si on a trouver , bah on sait qu'il est pas seul c deja sa.

					positionpiececourante =  partietest.getPlateau().getCase(t).getPositon(); //On enregistre la position de la piece qu'on va tester.


					// ARRIVER LA, vous avez deje surement mal au crane oklm c plus tres long les explications.
					if ( partietest.getPlateau().getCase(positionpiececourante).getPiece().getNom().equals("Pion")) { // Donc, la on regarde si la piece trouver, eest un pion.
						// Si oui , on va enregistrer ses coordonnees.
						int pioni = partietest.getPlateau().getCase(t).getPiece().geti();
						int pionj = partietest.getPlateau().getCase(t).getPiece().getj();


						for (int i =0; i <3 ; i++){       // BON LA SA SE REPETE , on va tester les depalcements du pion cette fois c tout.
							for (int j = 0 ; j<3 ; j++){
								partietest = new Partie(this);
								//this.p = new Plateau(plateauCopie);

								if (pionj == 0 && TabCol[j]== -1) {


									}
								else if (TabCol[j]== 1 && pionj == 7) {



									}
								else if (TabCol[i]== -1 && pioni == 0) {


									}
								else if (TabCol[i]== 1 && pioni == 7 ) {


									}
								else {
									// Bon la si on arrive jusque la, c qu'il peut tenter le deplacement sans sortir du tableau.
									//partietest.p.afficher();
									partietest.getPlateau().getCase(positionpiececourante).getPiece().deplacer(partietest.getPlateau().getCase(((pioni + TabCol[i])*8)+pionj+TabCol[j]),partietest.getPlateau());

									//System.out.println("GIGATACOSSSSSSSSSSSSSSSSSSSSSSSSSS");

									if(partietest.getPlateau().getCase(positionpiececourante).getPiece() == null && partietest.verifEchec(joueuradverse)== false) {

										// La on verifie juste si il a bien bouge en regardant si sa case d'origine ( donc au debut du test) est bien vide. Si oui c qu'il a bouger, si non c qu'il es tjs dedans.
										// On regarde aussi si avec sa nouvelle position , il provoque un echec ou pas dans son camp.


										autrepiece= true; // Si il provoque aps dechec et qu'il a bien bouger, bah c qu'on peut encore jouer,  autrepiece passe a true,  YA PAS DE PAT.

										}

									}

								}
							}
						}

					// La on va faire les memes test c juste qu'on regarde si cette fois c une reine.

					else if ( partietest.getPlateau().getCase(positionpiececourante).getPiece().getNom().equals("Reine")) {
						int reinei = partietest.getPlateau().getCase(t).getPiece().geti();
						int reinej = partietest.getPlateau().getCase(t).getPiece().getj();
						int[] TabCol2 = {-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7};
						//int[] TabLin = {-1,0,1};
						for (int i =0; i <15 ; i++){
							for (int j = 0 ; j<15 ; j++){
								partietest = new Partie(this);
								//this.p = new Plateau(plateauCopie);

								if (reinej == 0 && TabCol2[j]<= -1) {


									}
								else if (TabCol2[j] >= 1 && reinej == 7) {



									}
								else if (TabCol2[i] <= -1 && reinei == 0) {


									}
								else if (TabCol2[i] >= 1 && reinei == 7 ) {



									}
								else if (((((reinei + TabCol2[i])*8)+reinej+TabCol2[j]) < 0) || ((((reinei + TabCol2[i])*8)+reinej+TabCol2[j]) >63)) {

									}
								else {

									//partietest.p.afficher();
									partietest.getPlateau().getCase(positionpiececourante).getPiece().deplacer(partietest.getPlateau().getCase(((reinei + TabCol2[i])*8)+reinej+TabCol2[j]),partietest.getPlateau());



									if(partietest.getPlateau().getCase(positionpiececourante).getPiece() == null && partietest.verifEchec(joueuradverse)== false) {




										autrepiece= true;

										}

									}

								}
							}
						}


					// La memes test si c'est une tour.
					else if ( partietest.getPlateau().getCase(positionpiececourante).getPiece().getNom().equals("Tour")) {
						int touri = partietest.getPlateau().getCase(t).getPiece().geti();
						int tourj = partietest.getPlateau().getCase(t).getPiece().getj();
						int[] TabCol2 = {-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7};
						//int[] TabLin = {-1,0,1};
						for (int i =0; i <15 ; i++){
							for (int j = 0 ; j<15 ; j++){
								partietest = new Partie(this);
								//this.p = new Plateau(plateauCopie);

								if (tourj == 0 && TabCol2[j]<= -1) {




									}
								else if (TabCol2[j] >= 1 && tourj == 7) {




									}
								else if (TabCol2[i] <= -1 && touri == 0) {



									}
								else if (TabCol2[i] >= 1 && touri == 7 ) {



									}
								else if (((((touri + TabCol2[i])*8)+tourj+TabCol2[j]) < 0) || ((((touri + TabCol2[i])*8)+tourj+TabCol2[j]) >63)) {

									}
								else {

									//partietest.p.afficher();
									partietest.getPlateau().getCase(positionpiececourante).getPiece().deplacer(partietest.getPlateau().getCase(((touri + TabCol2[i])*8)+tourj+TabCol2[j]),partietest.getPlateau());



									if(partietest.getPlateau().getCase(positionpiececourante).getPiece() == null && partietest.verifEchec(joueuradverse)== false) {




										autrepiece= true;

										}

									}

								}
							}
						}

					// La meme test si c'est un fou.
					else if ( partietest.getPlateau().getCase(positionpiececourante).getPiece().getNom().equals("Fou")) {
						int foui = partietest.getPlateau().getCase(t).getPiece().geti();
						int fouj = partietest.getPlateau().getCase(t).getPiece().getj();
						int[] TabCol2 = {-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7};
						//int[] TabLin = {-1,0,1};
						for (int i =0; i <15 ; i++){
							for (int j = 0 ; j<15 ; j++){
								partietest = new Partie(this);
								//this.p = new Plateau(plateauCopie);

								if (fouj == 0 && TabCol2[j]<= -1) {



									}
								else if (TabCol2[j] >= 1 && fouj == 7) {




									}
								else if (TabCol2[i] <= -1 && foui == 0) {



									}
								else if (TabCol2[i] >= 1 && foui == 7 ) {



									}
								else if (((((foui + TabCol2[i])*8)+fouj+TabCol2[j]) < 0) || ((((foui + TabCol2[i])*8)+fouj+TabCol2[j]) >63)) {

									}
								else {

									//partietest.p.afficher();
									partietest.getPlateau().getCase(positionpiececourante).getPiece().deplacer(partietest.getPlateau().getCase(((foui + TabCol2[i])*8)+fouj+TabCol2[j]),partietest.getPlateau());



									if(partietest.getPlateau().getCase(positionpiececourante).getPiece() == null && partietest.verifEchec(joueuradverse)== false) {




										autrepiece= true;

										}

									}

								}
							}
						}

					// La meme test si c'est un cavalier.
					else if ( partietest.getPlateau().getCase(positionpiececourante).getPiece().getNom().equals("Cavalier")) {
						int cavalieri = partietest.getPlateau().getCase(t).getPiece().geti();
						int cavalierj = partietest.getPlateau().getCase(t).getPiece().getj();
						int[] TabCol3 = {-2,-1,0,1,2};
						//int[] TabLin = {-1,0,1};
						for (int i =0; i <5 ; i++){
							for (int j = 0 ; j<5 ; j++){
								partietest = new Partie(this);
								//this.p = new Plateau(plateauCopie);

								if (cavalierj == 0 && TabCol3[j]<= -1) {




									}
								else if (TabCol3[j] >= 1 && cavalierj == 7) {




									}
								else if (TabCol3[i] <= -1 && cavalieri == 0) {



									}
								else if (TabCol3[i] >= 1 && cavalieri == 7 ) {



									}
								else if (((((cavalieri + TabCol3[i])*8)+cavalierj+TabCol3[j]) < 0) || ((((cavalieri + TabCol3[i])*8)+cavalierj+TabCol3[j]) >63)) {

									}
								else {

									//partietest.p.afficher();
									partietest.getPlateau().getCase(positionpiececourante).getPiece().deplacer(partietest.getPlateau().getCase(((cavalieri + TabCol3[i])*8)+cavalierj+TabCol3[j]),partietest.getPlateau());



									if(partietest.getPlateau().getCase(positionpiececourante).getPiece() == null && partietest.verifEchec(joueuradverse)== false) {




										autrepiece= true;

										}

									}

								}
							}
						}




					}

				}

			}






		// Ensuite on arrive ici , on regarde si autrepiece est valide ou aps , donc si une autre piece a pu bouger ou non, et si le roi est immobiliser , si les deux sont true , bah c que y'a pat, si y'en a que 1 des deux , ou aucun de true, bah c PAS PAT.
		//System.out.println("Il arrive la ");
		if (autrepiece == false && roiImmobiliser == true) {

			//System.out.println("La il a pat");

			return true;
			}
		else {
			//System.out.println("La il a pas pat");
			return false;
			}
		}












	//methode qui va dire si la partie est finit
	public boolean partieFini(){
		boolean fin = false;
		// si il y a pat ou mat, alors la partie est finit
		if(mat(this.tabjoueur[0]) || pat(this.tabjoueur[0]) || mat(this.tabjoueur[1]) || pat(this.tabjoueur[1])){
			fin = true;
		}
		return fin;
	}


	//affiche le gagnant ou match nul
	public void gagnant(){

		//si le joueur 1 a gg
		if(mat(this.tabjoueur[0])){
			System.out.println("Le gagnant est..." + " " + this.tabjoueur[0].getNom());
		}

		//si le joueur 2 a gg
		if(mat(this.tabjoueur[1])){
			System.out.println("Le gagnant est..." + " " + this.tabjoueur[0].getNom());
		}

		//si ya match nul
		if(pat(this.tabjoueur[0]) || pat(this.tabjoueur[1]) && ((! mat(this.tabjoueur[0])) && (! mat(this.tabjoueur[1])) ) ){
			System.out.println("Match nul les kheys ! Bravo a vous");
		}
	}


	//fonction qui va permettre le changement de tour et permettre au joueurs de jouer
	public void changer_tour(){
		//p.afficher();

		while(! partieFini()){
			System.out.println("cest au tour du joueur 1 de joueur");
			jouerCoup();
			p.afficher();
			System.out.println("cest au tour du joueur 2 de joueur");
			jouerCoup();
			p.afficher();
		}

	}


	// pour jouer aux echec tas capter
	public void echec(){
		changer_tour();
		gagnant();
	}


	//sauver version serializable
	public void sauver(String fichier){
		try {

			FileOutputStream fos = new FileOutputStream(fichier);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			//oos.writeObject(this.p);
			oos.writeObject(this);

			oos.flush();
			oos.close();

		}

		catch (java.io.IOException exept) {
			exept.printStackTrace();

		}
	}


	//charger version serializable
	public void charger2(String fichier) {

		try {
			FileInputStream fis = new FileInputStream(fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);
			//this.p = (Plateau) ois.readObject();
			Partie part = (Partie) ois.readObject();
			ois.close();

			part.p.afficher();
			part.echec();

		}

		catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//p.afficher();
	}


	public void menu(){
		System.out.println("Pour demarrer une nouvelle partie, tapez 1");
		System.out.println("Pour charger une partie, tapez 2");
		System.out.println("Pour sauvegarder une partie, tapez 3");
		System.out.println("Pour abbandonner , tapez pas ");

		Scanner sc = new Scanner(System.in);
		int saisie = sc.nextInt();

		while( saisie != 1 &&  saisie != 2 && saisie != 3) {
			System.out.println("t'es ultra chiant recommence");
			System.out.println("Pour demarrer une nouvelle partie, tapez 1");
			System.out.println("Pour charger une partie, tapez 2");
			System.out.println("Pour sauvegarder une partie, tapez 3");
			System.out.println("Pour abbandonner , tapez pas ");

			saisie = sc.nextInt();

		}

		if( saisie == 1){
			this.demarrer();
			this.echec();
		}

		if( saisie == 2 ){

			//try{
				this.charger2("PartieEchec");
				//echec();
			//}

			//catch(IOException e){
				//System.out.println(e);
			//}
		}

		if( saisie == 3 ){
			//try{
				this.sauver("PartieEchec");
			//}

			//catch(IOException e){
				//System.out.println(e);
			//}
		}
	}

//test de la fonction sauvegarder et charger


	public static void main(String[] args) throws IOException{
		Plateau plateau = new Plateau();
		Partie partie = new Partie();
		//partie.demarrer();

		partie.menu();

		//System.out.println(partie.mat(partie.tabjoueur[1]));

		//partie.sauver("PartieEchec.txt");
		//partie.charger2("PartieEchec.txt");

		/*
		partie.pat(partie.tabjoueur[1]);
		for(int i = 0; i < 64; i++){
			if(partie.p.getCase(i).estOccupe()){

				System.out.println(partie.p.getCase(i).getPiece().getCouleur());
			}

		}
		*/

		//partie.echec();


		//partie.sauvegarder("PartieEchec.txt");


		//partie.charger("PartieEchec.txt");

	}



}
