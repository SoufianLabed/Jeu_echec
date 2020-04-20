public class Plateau implements java.io.Serializable{
  private Case[] tabjeu;

  // constructeur qui va creer les 64 case et les mettre dans le tab
  public Plateau(){
    this.tabjeu = new Case[64];
    int indice = 0;
    for(int i = 0; i < 8; i++){
    	for (int j = 0; j < 8; j++) {
    		this.tabjeu[indice] = new Case(j%2,null,i,j);
    		indice = indice + 1;
    	}
    	}
    }


  /*constructeur par recopie*/
  public Plateau(Plateau p){
      this.tabjeu = new Case[64];
      for(int i = 0; i < p.tabjeu.length; i++ ){
          this.tabjeu[i] = new Case(p.tabjeu[i]);

          if (p.tabjeu[i].getPiece() != null ) {
          if (p.tabjeu[i].getPiece().getNom().equals("Roi")) {
              this.tabjeu[i].setPiece(new Roi(p.tabjeu[i].getPiece().getCouleur(),p.tabjeu[i].getPiece().geti(),p.tabjeu[i].getPiece().getj()));
          }
          else if (p.tabjeu[i].getPiece().getNom().equals("Reine")) {
              this.tabjeu[i].setPiece(new Reine(p.tabjeu[i].getPiece().getCouleur(),p.tabjeu[i].getPiece().geti(),p.tabjeu[i].getPiece().getj()));
          }
          else if (p.tabjeu[i].getPiece().getNom().equals("Cavalier")) {
              this.tabjeu[i].setPiece(new Cavalier(p.tabjeu[i].getPiece().getCouleur(),p.tabjeu[i].getPiece().geti(),p.tabjeu[i].getPiece().getj()));
          }
          else if (p.tabjeu[i].getPiece().getNom().equals("Tour")) {
              this.tabjeu[i].setPiece(new Tour(p.tabjeu[i].getPiece().getCouleur(),p.tabjeu[i].getPiece().geti(),p.tabjeu[i].getPiece().getj()));
          }
          else if (p.tabjeu[i].getPiece().getNom().equals("Fou")) {
              this.tabjeu[i].setPiece(new Fou(p.tabjeu[i].getPiece().getCouleur(),p.tabjeu[i].getPiece().geti(),p.tabjeu[i].getPiece().getj()));
          }
          else if (p.tabjeu[i].getPiece().getNom().equals("Pion")) {
              this.tabjeu[i].setPiece(new Pion(p.tabjeu[i].getPiece().getCouleur(),p.tabjeu[i].getPiece().geti(),p.tabjeu[i].getPiece().getj()));
          }
      }

      }
  }


  //getter et setter
  public Case[] getTabjeu(){
	  return this.tabjeu;
  }

  public Case getCase(int i){
	  return this.tabjeu[i];
  }

  public void setCase(Case c, int i){
	  this.tabjeu[i] = c;
  }


  public void initPiece(){

    /*permet d'initialiser une partie en plaçant les différentes pieces sur l'echiquier*/

	//Creation des pions noirs


	  this.tabjeu[0].setPiece(new Tour(0,0,0));
	  this.tabjeu[1].setPiece(new Cavalier(0,0,1));
	  this.tabjeu[2].setPiece(new Fou(0,0,2));
	  this.tabjeu[3].setPiece(new Reine(0,0,3));
	  this.tabjeu[4].setPiece(new Roi(0,0,4));
	  this.tabjeu[5].setPiece(new Fou(0,0,5));
	  this.tabjeu[6].setPiece(new Cavalier(0,0,6));
	  this.tabjeu[7].setPiece(new Tour(0,0,7));
	  for(int i = 8; i<=15; i++ ) {
		  this.tabjeu[i].setPiece(new Pion(0,1,i-8));
	  }

	  //creation des pions blanc

	  this.tabjeu[56].setPiece(new Tour(1,7,0));
	  this.tabjeu[57].setPiece(new Cavalier(1,7,1));
	  this.tabjeu[58].setPiece( new Fou(1,7,2));
	  this.tabjeu[59].setPiece(new Reine(1,7,3));
	  this.tabjeu[60].setPiece(new Roi(1,7,4));
	  this.tabjeu[61].setPiece(new Fou(1,7,5));
	  this.tabjeu[62].setPiece(new Cavalier(1,7,6));
	  this.tabjeu[63].setPiece(new Tour(1,7,7));

	  int j = 0;
	  for(int i = 48; i<=55; i++ ){
		  this.tabjeu[i].setPiece(new Pion(1,6,j));
		  j++;
	  }


	  //test pour le pat
	  /*
	  this.tabjeu[0].setPiece(new Tour(0,0,0));
      this.tabjeu[1].setPiece(new Cavalier(0,0,1));
      this.tabjeu[2].setPiece(new Fou(0,0,2));
      this.tabjeu[3].setPiece(new Reine(0,0,3));
      this.tabjeu[4].setPiece(new Roi(0,0,4));
      this.tabjeu[5].setPiece(new Fou(0,0,5));
      this.tabjeu[6].setPiece(new Cavalier(0,0,6));
      this.tabjeu[7].setPiece(new Tour(0,0,7));
      for(int i = 8; i<=15; i++ ) {
          this.tabjeu[i].setPiece(new Pion(0,1,i-8));
      }

      //creation des pions blanc

      this.tabjeu[56].setPiece(new Cavalier(1,7,0));
    //  this.tabjeu[57].setPiece(new Cavalier(1,7,1));
     //this.tabjeu[58].setPiece( new Pion(1,7,2));
      //this.tabjeu[59].setPiece(new Reine(1,7,3));
      this.tabjeu[58].setPiece(new Roi(1,7,2));
      this.tabjeu[49].setPiece(new Tour(0,6,1));
      this.tabjeu[50].setPiece(new Pion(1,6,2));
      this.tabjeu[42].setPiece(new Pion(0,5,2));
      //this.tabjeu[50].setPiece(new Pion(0,5,4));
      this.tabjeu[51].setPiece(new Tour(0,6,2));
      this.tabjeu[41].setPiece(new Pion(1,5,1));
      this.tabjeu[33].setPiece(new Pion(0,4,1));
      this.tabjeu[63].setPiece(new Fou(1,7,7));
      this.tabjeu[46].setPiece(new Pion(0,5,6));
      this.tabjeu[54].setPiece(new Pion(1,6,6));
	  */


    //test pour le mat
	  /*
	  this.tabjeu[0].setPiece(new Tour(0,0,0));
      this.tabjeu[1].setPiece(new Cavalier(0,0,1));
      this.tabjeu[2].setPiece(new Fou(0,0,2));
      this.tabjeu[3].setPiece(new Reine(0,0,3));
      this.tabjeu[4].setPiece(new Roi(0,0,4));
      this.tabjeu[5].setPiece(new Fou(0,0,5));
      this.tabjeu[6].setPiece(new Cavalier(0,0,6));
      this.tabjeu[7].setPiece(new Tour(0,0,7));
      for(int i = 8; i<=15; i++ ) {
          this.tabjeu[i].setPiece(new Pion(0,1,i-8));
      }

      //creation des pions blanc

      //this.tabjeu[56].setPiece(new Cavalier(1,7,0));
    //  this.tabjeu[57].setPiece(new Cavalier(1,7,1));
     //this.tabjeu[58].setPiece( new Pion(1,7,2));
      //this.tabjeu[59].setPiece(new Reine(1,7,3));
      this.tabjeu[58].setPiece(new Roi(1,7,2));
      this.tabjeu[49].setPiece(new Tour(0,6,1));
      this.tabjeu[50].setPiece(new Reine(0,6,2));
      this.tabjeu[42].setPiece(new Pion(0,5,2));
      //this.tabjeu[50].setPiece(new Pion(0,5,4));
      this.tabjeu[51].setPiece(new Tour(0,6,2));
      this.tabjeu[41].setPiece(new Pion(1,5,1));
      this.tabjeu[33].setPiece(new Pion(0,4,1));
      this.tabjeu[63].setPiece(new Fou(1,7,7));
      this.tabjeu[46].setPiece(new Pion(0,5,6));
      this.tabjeu[54].setPiece(new Pion(1,6,6));
	  */


  }

  public void afficher(){
	  String[] tablettre = {"a","b","c","d","e","f","g","h"};
	  String ligneH="  ________________________________________";
	  int indice = 0;
	  System.out.println("    1    2    3    4    5    6    7    8");

	  for (int ligne = 0; ligne <8; ligne++) {
		  String lignepiece = tablettre[ligne]+" |";
		  System.out.println(ligneH);
		  for (int j = 0;j<8;j++) {
			  if (this.getCase(indice).getPiece() != null) {

				  if(this.getCase(indice).getPiece().getCouleur() == 0){
            if (this.getCase(indice).getPiece().getNom().equals("Fou")) {
					  lignepiece += "\u2657  | ";
				  	}
				  else if (this.getCase(indice).getPiece().getNom().equals("Cavalier")) {
					  lignepiece += "\u2658  | ";
				  }
				  else if (this.getCase(indice).getPiece().getNom().equals("Tour")) {
					  lignepiece += "\u2656  | ";
				  }
				  else if (this.getCase(indice).getPiece().getNom().equals("Roi")) {
					  lignepiece += "\u2654  | ";
				  }
				  else if (this.getCase(indice).getPiece().getNom().equals("Reine")) {
					  lignepiece += "\u2655  | ";
				  }
				  else if (this.getCase(indice).getPiece().getNom().equals("Pion")) {
					  lignepiece += "\u2659  | ";
				  }

          }

          if(this.getCase(indice).getPiece().getCouleur() == 1){
            if (this.getCase(indice).getPiece().getNom().equals("Fou")) {
					  lignepiece += "\u265D  | ";
				  	}
				  else if (this.getCase(indice).getPiece().getNom().equals("Cavalier")) {
					  lignepiece += "\u265E  | ";
				  }
				  else if (this.getCase(indice).getPiece().getNom().equals("Tour")) {
					  lignepiece += "\u265C  | ";
				  }
				  else if (this.getCase(indice).getPiece().getNom().equals("Roi")) {
					  lignepiece += "\u265A  | ";
				  }
				  else if (this.getCase(indice).getPiece().getNom().equals("Reine")) {
					  lignepiece += "\u265B  | ";
				  }
				  else if (this.getCase(indice).getPiece().getNom().equals("Pion")) {
					  lignepiece += "\u265F  | ";
				  }
          }

				  indice +=1 ;

		  }
			  else {
				  lignepiece += "   | ";
				  indice += 1;
			  }


		  }
		  lignepiece += tablettre[ligne];
		  System.out.println(lignepiece);


	  }

	  System.out.println(ligneH);
	  System.out.println("    1    2    3    4    5    6    7    8");

  }
  }
