public class Fou extends Pion
{
  public Fou(int couleur)
  {
    super("fou", couleur, 3);
  }

  public boolean estJouable(int x1, int y1, int x2, int y2, boolean ligne, boolean diagonale, boolean mange) //Détermine si un coup est jouable pour un fou
  {

    return (!diagonale);
  }
}

