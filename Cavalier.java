public class Cavalier extends Pion
{
  public Cavalier(int couleur)
  {
    super("cavalier", couleur, 3);
  }

  public boolean estJouable(int x1, int y1, int x2, int y2, boolean ligne, boolean diagonale, boolean mange) //Détermine si un coup est jouable pour cette pièce
  {
    if((Math.abs(x1-x2)==1.0 && Math.abs(y1-y2)==2.0) || (Math.abs(x1-x2)==2.0 && Math.abs(y1-y2)==1.0))
      return true;
    return false;
  }
}
