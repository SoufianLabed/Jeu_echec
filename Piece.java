/* classe abstraite avec des methodes vide, on les remplira dans les diff types de pions ichallah*/

public abstract class Piece implements java.io.Serializable{
  private String nom;
  private int couleur;
  //private int position;
  private int j;
  private int i;

  private int num;

  public Piece(String nom, int couleur, int i, int j, int num){
      //this.position = this.i * 8 + this.j;
      this.couleur = couleur;
      this.nom = nom;
      this.i = i;
      this.j = j;

      this.num = num;
  }
  /* constructeur par recopie*/
  public Piece(Piece p){
	  this.nom = new String(p.nom);
	  this.couleur = p.couleur;
	  //this.position = p.position;
	  this.i = p.i;
	  this.j = p.j;
  }

  public boolean indice_valide(int i){
    return (i >= 0 && i <= 7);
  }
    /* si i et j sont bien compris entre 0 et 7*/

  public boolean case_valide(int i,int j){
    return (indice_valide(i) && indice_valide(j));
  }
    /* si i*8 + j est bien compris entre 0 et 63*/

  abstract boolean mouvement_possible(int horizontal, int vertical);
    /* si jamais le pion peut bouger dans cet direction refnvois true, false sinon.*/

  abstract boolean manger_possible(Case case_dest,int horizontal,int vertical);
    /* si jamais on peut manger dans cette direction renvois true, false sinon.
       On pourra utiliser la fonction precedente.*/

  abstract String deplacer(Case case_dest,Plateau p);
    /* if mouvement_valide ,alors on se déplace: Un bail du style "setPosition(nouvelle position)"*/

  public int getPosition(){
    /*donne la position de la piece*/
	  int position = this.i * 8 + this.j;
    return position;
  }

  //public void setPosition(int position){
    //this.position = position;
    /*permet de modifier la position d'une piece*/
  //}

  public String getNom(){
    return this.nom;
  }

  public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public int getCouleur(){
    return this.couleur;
  }

  public int geti(){
    return this.i;
  }

  public int getj(){
    return this.j;
  }

  public void setI(int i){
	  this.i = i;
  }

  public void setJ(int j){
	  this.j = j;
  }




}
