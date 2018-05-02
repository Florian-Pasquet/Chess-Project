public class Dame extends Pion
{
  public Dame(int couleur)
  {
    super("dame", couleur, 10);
  }

  public boolean estJouable(int x1, int y1, int x2, int y2, boolean ligne, boolean diagonale, boolean mange) //Détermine si un coup est jouable pour une dame
  {

    return (!diagonale || !ligne);
  }
}
