// Tes positions sont des nombres et pas des valeurs, check directement

public class Case implements java.io.Serializable{
  private int couleur;
  private Piece piece;
  private int i;
  private int j;
  private int position;

  public Case(int couleur, Piece piece,int i, int j){
    this.couleur = couleur;
    this.piece = piece;
    this.i = i;
    this.j = j;
    this.position = (this.i*8) + this.j;
    /* calcul permettant de connaitre la position de la case, ce nombre sera entre 0 et 63*/
  }

  public Case(Case c){
	  this.couleur = c.couleur;
	  this.piece = c.piece;
	  this.i = c.i;
	  this.j = c.j;
	  this.position = c.position;
  }

  public int geti(){
    return this.i;
  }

  public int getj(){
    return this.j;
  }

  public int getPositon(){
    return this.position;
  }

  public Piece getPiece(){
    return this.piece;
  }

  public void setPiece(Piece piece){
    this.piece = piece;
  }

  public boolean estOccupe(){
    /* permet de savoir si une case est occupee ou non*/
    return (piece != null);
  }

}
