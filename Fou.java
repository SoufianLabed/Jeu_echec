public class Fou extends Piece{

  public Fou(int couleur, int i,int j){
    super("Fou",couleur,i,j,1);
  }

  /*le fou ne se déplace qu'en diagonale, ce qui sous enttend que le déplacement
   en colonne et en ligne est égale.
    Horizontal et vertical sont égaux. Il suffit donc de tester l'égalité entre les 2.*/

  public boolean mouvement_possible(int horizontal, int vertical){
    return Math.abs(horizontal) == Math.abs(vertical);
  }

  //cette fonction va nous dire si on peut gray ou pas

  public boolean manger_possible(Case case_dest ,int horizontal, int vertical){

    /* si le mouv est possible, et que la case est occupee, alors on teste si elle
	 est occupee par une piece adverse*/

    if ((mouvement_possible(horizontal,vertical) && case_dest.estOccupe()) == true ){

      if ( case_dest.getPiece().getCouleur() != this.getCouleur()){
    	  /* on test si c'est une piece adverse que tu veux manger*/

    	  return true;

      }
    }
    return false;
  }


  //cette fonction nous dira si la voie est libre pour faire notre deplacement

  // on va verifier si la voie est libre, en haut a droite, gauche, puis en en bas a droite, gauche

  //commencons par le haut a gauche
  public boolean chemin_libre(Case case_dest, Plateau p){
		if (case_dest.geti() < this.geti()) {   // En haut
			if (case_dest.getj() < this.getj()) { // Aa gauche
				for (int ind =1; this.geti()-ind>case_dest.geti(); ind++) {
					if (p.getCase(  (this.geti()-ind)*8 + (this.getj()-ind)).getPiece() != null) {
						return false;

					}
				}
			}
			if (case_dest.getj() > this.getj()) {    // A droite
				for (int ind =1; this.geti()-ind>case_dest.geti(); ind++) {
					if (p.getCase(  (this.geti()-ind)*8 + (this.getj()+ind)).getPiece() != null) {
						return false;
					}
				}

			}

		}

		if (case_dest.geti() > this.geti()) {  // En bas
			if (case_dest.getj() < this.getj()) {   // a gauche
				for (int ind =1; this.geti()+ind<case_dest.geti(); ind++) {
					if (p.getCase(  (this.geti()+ind)*8 + (this.getj()-ind)).getPiece() != null) {
						return false;
					}
				}
			}

			if (case_dest.getj() > this.getj()) {  // a droite
				for (int ind =1; this.geti()+ind<case_dest.geti(); ind++) {
					if (p.getCase((this.geti()+ind)*8 + (this.getj()+ind)).getPiece() != null) {
						return false;
					}

				}
			}

		}

		return true;
  }


  //La fonction qui va nous permettre de deplacer notre fou.

  public String deplacer(Case case_dest,Plateau p){
	  String historique = ""; /* historique de coup quon va renvoyer*/

	  /* mtn on va recuperer ce qui sépare notre case de la case darrivee*/

	  int horizontal = Math.abs(case_dest.geti()) - this.geti();
	  int vertical = Math.abs(case_dest.getj()) - this.getj();

	  // on test si on peut manger, si oui alors on se deplace.

	  if(chemin_libre(case_dest , p)){
		  if(manger_possible(case_dest,horizontal,vertical)){
			  Piece piece_manger = case_dest.getPiece(); // on sauve la piece manger
			  int current_position = this.getPosition(); // on sauve la position courrante

			  p.getCase(current_position).setPiece(null); //on vide la case de depart
			  case_dest.setPiece(this); // on remplace la piece manger par la notre

			  historique = "nous avons manger un " + piece_manger.getNom();
			  // on renvoit la piece manger

			  super.setI(case_dest.geti());
			  super.setJ(case_dest.getj());


		  }

		  /* on test si le mouvement est possible, si oui alors on se deplace*/

		  else if(mouvement_possible(horizontal,vertical) && (! case_dest.estOccupe()) ){
			  int current_position = this.getPosition(); /* on sauve la position courrante*/

			  p.getCase(current_position).setPiece(null);/* on vide la case de depart*/
			  case_dest.setPiece(this); /* on place notre pion dans la case darrivee*/

			  historique = "Tu tes deplacer a la position: " + case_dest.getPositon();
			  //on renvois la position darriver

			  super.setI(case_dest.geti());
			  super.setJ(case_dest.getj());



		  }


	  }
	  return historique; //on retourne lhistorique
  }


}
