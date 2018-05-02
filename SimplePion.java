public class SimplePion extends Pion
{
  public SimplePion(int couleur)
  {
    super("pion", couleur, 1);
  }

  public boolean estJouable(int x1, int y1, int x2, int y2, boolean ligne, boolean diagonale, boolean mange) //Détermine si un coup est jouable pour un pion
  {
    if(this.couleur==0)
    {
      if(x2==x1+1 && y1==y2 && !mange) //S'il avance d'une case sans manger
        return true;
      if(x2==x1+2 && x2==3 && y1==y2 && !mange) //S'il avance de deux cases au début sans manger
        return true;
      if(mange && Math.abs(y2-y1)==1.0 && x2==x1+1) //S'il avance en diagonale pour manger
        return true;
      return false; //Sinon le pion ne peut avancer
    }
    if(this.couleur==1)
    {
      if(x1==x2+1 && y1==y2 && !mange) //S'il avance d'une case sans manger
        return true;
      if(x1==x2+2 && x2==4 && y1==y2 && !mange) //S'il avance de deux cases au début sans manger
        return true;
      if(mange && Math.abs(y2-y1)==1.0 && x1==x2+1) //S'il avance en diagonale pour manger
        return true;
      return false; //Sinon le pion ne peut avancer

    }
    return false; //Ligne jamais exécutée.
  }
}
