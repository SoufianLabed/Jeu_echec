
public class Roi extends Piece {
	public Roi(int couleur, int i,int j){
	    super("Roi",couleur,i,j,4);
	  }

	public boolean mouvement_possible(int horizontal, int vertical){
		/* on test si on se deplace que de une case, et que ya pas de
		   mec chiant qui vont vouloir bouger de 2 ou 3 ou 4 case...
		 */

		return (vertical == 0 && horizontal == 1) || (vertical == 1 && horizontal == 0) || (vertical == 1 && horizontal == 1);


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



	  //La fonction qui va nous permettre de deplacer notre Roi.

	  public String deplacer(Case case_dest,Plateau p){
		  String historique = ""; /* historique de coup quon va renvoyer*/

		  /* mtn on va recuperer ce qui sépare notre case de la case darrivee*/

		  int horizontal = Math.abs(case_dest.geti()) - this.geti();
		  int vertical = Math.abs(case_dest.getj()) - this.getj();

		  // on test si on peut manger, si oui alors on se deplace.

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

				  historique = "Tu tes deplacer a la position:" + case_dest.getPositon();
				  //on renvois la position darriver

				  super.setI(case_dest.geti());
				  super.setJ(case_dest.getj());



			  }

		  return historique; //on retourne lhistorique
	  }


	}
