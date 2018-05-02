public class Case
{
  protected Pion pion;
  protected int couleur; //0 pour blanc, 1 pour noire

  public Case(int c)
  {
    pion = null;
    couleur = c;
  }

  public Case(Pion p, int c)
  {
    pion = p;
    couleur = c;
  }

  public Pion getPion()
  {
    return pion;
  }

  public Pion removePion()
  {
    Pion p = pion;
    pion = null;
    return p;
  }

  public void setPion(Pion p)
  {
    pion = p;
  }

  public boolean estVide()
  {
    return (pion == null);
  }

  public String toString()
  {
    if(pion!=null)
      return pion.toString();
    else
      return "  ";
  }

}
