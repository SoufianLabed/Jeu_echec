import java.util.Scanner;

public class Pion extends Piece {

	private boolean abouger;

	public Pion(int couleur, int i,int j){
	    super("Pion",couleur,i,j,6);
	    this.abouger = false;
	  }
	public void setabouger() {
		this.abouger = true;
	}

	public boolean mouvement_possible(int horizontal, int vertical){

		if (this.abouger == false) {

			if (this.getCouleur() == 0) {
				this.setabouger();

				return ((horizontal == 2 && Math.abs(vertical) == 0) || ( horizontal == 1 && Math.abs(vertical) == 0));
			}
			else {
				this.setabouger();
				return ((horizontal == -2 && Math.abs(vertical) == 0) || ( horizontal == -1 && Math.abs(vertical) == 0));
			}


		}
		else {
			if (this.getCouleur() == 0) {
				return ( Math.abs(horizontal) == 1 && Math.abs(vertical) == 0);
			}
			else {
				return (horizontal == -1 && Math.abs(vertical) == 0);
			}


		}
	  }

	public boolean manger_possible(Case case_dest ,int horizontal, int vertical){

		if (!case_dest.estOccupe() ) {

			return false;
		}
		if (this.getCouleur() == 0 && case_dest.getPiece().getCouleur() ==1 ) {
			if (case_dest.estOccupe()) {
				if ( (horizontal == 1 && vertical == 1) ) {
					return true;
				}

			}
			return false;
		}
		if (this.getCouleur() == 1  && case_dest.getPiece().getCouleur() == 0 ) {
			if (case_dest.estOccupe() )  {

				if ( (horizontal == -1  && vertical == 1)) {
					return true;
				}

			}
			return false;
		}
	return false;
	}



	//methode qui va permettre au pion devoluer

	public void evolution(Case case_actuelle) {
		 int current_position = case_actuelle.getPositon();
		 if (this.getCouleur()==0) {
		 if (current_position> 55 && current_position < 64) {

		 Scanner sc = new Scanner(System.in);

		       System.out.println("Promotion possible : au choix une dame(1), une tour(2), un fou(3) ou un cavalier(4). ");

		       int sc_chiffre = sc.nextInt();
		       while(sc_chiffre != 1 && sc_chiffre != 2 && sc_chiffre != 3 && sc_chiffre != 4){

		       	System.out.println("Promotion possible : au choix une dame(1), une tour(2), un fou(3) ou un cavalier(4). ");
		       	sc_chiffre= sc.nextInt();

		       //on recommence tant que le joueur est relou et quil choisit pas la bonne couleur
		       }
		       if (sc_chiffre == 1) {
		       	Reine dadju = new Reine(0,this.geti(),this.getj());
		       	case_actuelle.setPiece(dadju);

		       }
		       if (sc_chiffre == 2) {
		       	Tour tour = new Tour(0,this.geti(),this.getj());
		       	case_actuelle.setPiece(tour);

		       }
		       if (sc_chiffre == 3) {
		       	Fou fou = new Fou(0,this.geti(),this.getj());
		       	case_actuelle.setPiece(fou);

		       }
		       if (sc_chiffre == 4) {
		       	Cavalier cheval = new Cavalier(0,this.geti(),this.getj());
		       	case_actuelle.setPiece(cheval);

		       }


		 }
		 }
		 else {
		 if (current_position> 0 && current_position < 8) {
		 Scanner sc = new Scanner(System.in);

		       System.out.println("Promotion possible : au choix une dame(1), une tour(2), un fou(3) ou un cavalier(4). ");
		       int sc_chiffre = sc.nextInt();
		       while(sc_chiffre != 1 && sc_chiffre != 2 && sc_chiffre != 3 && sc_chiffre != 4){

		       	System.out.println("Promotion possible : au choix une dame(1), une tour(2), un fou(3) ou un cavalier(4). ");
		       	sc_chiffre= sc.nextInt();

		       //on recommence tant que le joueur est relou et quil choisit pas la bonne couleur
		       }
		       if (sc_chiffre == 1) {
		       	Reine dadju = new Reine(1,this.geti(),this.getj());
		       	case_actuelle.setPiece(dadju);

		       }
		       if (sc_chiffre == 2) {
		       	Tour tour = new Tour(1,this.geti(),this.getj());
		       	case_actuelle.setPiece(tour);

		       }
		       if (sc_chiffre == 3) {
		       	Fou fou = new Fou(1,this.geti(),this.getj());
		       	case_actuelle.setPiece(fou);

		       }
		       if (sc_chiffre == 4) {
		       	Cavalier cheval = new Cavalier(1,this.geti(),this.getj());
		       	case_actuelle.setPiece(cheval);

		       }


		 }
		 }

		 }






	  public String deplacer(Case case_dest,Plateau p){
		  String historique = ""; /* historique de coup quon va renvoyer*/

		  /* mtn on va recuperer ce qui separe notre case de la case darrivee*/
		  int horizontal = case_dest.geti() - this.geti();
		  int vertical = Math.abs(case_dest.getj() - this.getj());

		  if(manger_possible(case_dest,horizontal,vertical)){
			  //System.out.println("tu rentre dans gray possible");
			  Piece piece_manger = case_dest.getPiece(); // on sauve la piece manger
			  int current_position = this.getPosition(); // on sauve la position courrante


			  p.getCase(current_position).setPiece(null); //on vide la case de depart
			  case_dest.setPiece(this); // on remplace la piece manger par la notre

			  historique = "Le pion a mange le " + piece_manger.getNom(); // on renvoit la piece manger
			  //historique = "nous avons manger un pion trop bien.";

			  super.setI(case_dest.geti());
			  super.setJ(case_dest.getj());

		  }

		  /* on test si le mouvement est possible, si oui alors on se deplace*/
		  else if(mouvement_possible(horizontal,vertical) && (! case_dest.estOccupe()) ){
			  //System.out.println("tu rentre dans mouv possible");
			  int current_position = this.getPosition(); /* on sauve la position courrante*/

			  p.getCase(current_position).setPiece(null);/* on vide la case de depart*/
			  case_dest.setPiece(this); /* on place notre pion dans la case darrivee*/

			  historique = "" + case_dest.getPositon(); //on renvois la position darriver

			  this.evolution(case_dest);
              //System.out.println("Promotion possible ");

              super.setI(case_dest.geti());
			  super.setJ(case_dest.getj());

		  }

		  else{
			  //System.out.println(" le deplacement na pas ete fait");
		  }

		  return historique; //on retourne lhistorique
	  }

	}
