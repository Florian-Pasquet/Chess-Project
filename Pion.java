public abstract class Pion implements Comparable<Pion> //Pour redefinir compareTo() et gérer la fonction de tri de la liste des pièces éliminées
{
  protected int couleur; // 0 pour blanc, 1 pour noir
  protected int valeur; //Pour le calcul d'avantage
  protected String nom;

  public Pion(String nom, int couleur, int valeur)
  {
    try
    {
      if(nom.equals("cavalier") || nom.equals("pion") || nom.equals("tour") || nom.equals("fou") || nom.equals("dame") || nom.equals("roi"))
      {
        this.couleur = couleur;
        this.nom = nom;
        this.valeur = valeur;
      }
      else
      {
        throw new PionException("nom de la pièce");
      }
    }
    catch(PionException e)
    {
      System.exit(1);
    }
  }

  public int getCouleur()
  {
    return couleur;
  }

  public int getValeur()
  {
    return valeur;
  }

  public String getNom()
  {
    return nom;
  }

  public int compareTo(Pion p) //On redéfinit la méthode pour le tri alphabétique
  {
    if(this.nom.charAt(0) > p.nom.charAt(0))
      return 1;
    if(this.nom.charAt(0) < p.nom.charAt(0))
      return -1;
    return 0;
  }

  public String toString() //Renvoie la première lettre de la pièce et B ou N selon la couleur
  {
    if(couleur == 0)
      return ("" + nom.charAt(0)).toUpperCase() + "b";
    return ("" + nom.charAt(0)).toUpperCase() + "n";
  }

  public abstract boolean estJouable(int x1, int y1, int x2, int y2, boolean ligne, boolean diagonale, boolean mange);

}
