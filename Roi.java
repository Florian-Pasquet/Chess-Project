public class Roi extends Pion
{
  protected boolean echec;

  public Roi(int couleur)
  {
    super("roi", couleur, 0);
    echec = false;
  }

  public void setEchec()
  {
    echec = true;
  }

  public void removeEchec()
  {
    echec = false;
  }

  public boolean estJouable(int x1, int y1, int x2, int y2, boolean ligne, boolean diagonale, boolean mange) //Détermine si un coup est jouable pour un roi
  {
    //RAJOUTER UN TEST D'ECHEC

    if(Math.abs(x1-x2)<=1.0 && Math.abs(y1-y2)<=1.0)
      return true;
    return false;
  }

}
