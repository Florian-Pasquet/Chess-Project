public class Tour extends Pion
{
  public Tour(int couleur)
  {
    super("tour", couleur, 5);
  }

  public boolean estJouable(int x1, int y1, int x2, int y2, boolean ligne, boolean diagonale, boolean mange) //Détermine si un coup est jouable pour une tour
  {
    return (!ligne); // Un coup est jouable s'il n'y a pas d'obstacle sur la ligne / colonne entre les cases envisagées.
  }
}
