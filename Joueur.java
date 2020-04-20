import java.util.Scanner;

public class Joueur implements java.io.Serializable{
	private String nom;
	private int couleur;

	// constructeur
	public Joueur(String nom, int couleur){
		this.nom = nom;
		this.couleur = couleur;
	}

	//constructeur vide
	public Joueur(){

	}

	public String getNom(){
		return this.nom;
	}

	public int getCouleur(){
		return this.couleur;
	}

	public void setNom(String nom){
		this.nom = nom;
	}

	public void setCouleur(int couleur){
		this.couleur = couleur;
	}


	// permet au joueur de saisir manuellement son nom et sa couleur
	public void init(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Choisis ton blase mon gars");
		String sc_nom = sc.nextLine();
		System.out.println("Rayayay wallah lourd inchallah tu gagne");

		System.out.println("Maintenant choisit ta couleur stp, noir(0) ou blanc(1) ? Tout racisme sera sanctionner");
		int couleur = sc.nextInt();

		//on recommence tant que le joueur est relou et quil choisit pas la bonne couleur
		while(couleur != 0 && couleur != 1){

			System.out.println("Recommence, 0 pour noir, et 1 pour blanc");
			couleur = sc.nextInt();
		}

		this.setNom(sc_nom); // on modifie le nom courant par le nom choisis
		this.setCouleur(couleur); //on modifie la couleur courante par la couleur choisie
	}

}
