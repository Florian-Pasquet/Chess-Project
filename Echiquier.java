import java.util.*; //Scanner et ArrayList notamment
import java.io.*;

public class Echiquier
{
  protected Case[][] echiquier;
  protected int nombreCoups;
  protected ArrayList<Pion> pionsEliminesBlancs;
  protected ArrayList<Pion> pionsEliminesNoirs;
  private boolean echecBlancs;
  private boolean echecNoirs;

  public Echiquier()
  {
    nombreCoups = 0;
    pionsEliminesBlancs = new ArrayList<Pion>();
    pionsEliminesNoirs = new ArrayList<Pion>();
    echecBlancs = false;
    echecNoirs = false;
    echiquier = new Case[8][8];
    for(int i=0; i<8; i++)
    {
      for(int j=0; j<8; j++)
      {
        if((i+j)%2 == 0)
          echiquier[i][j] = new Case(1); //Case noire
        else
          echiquier[i][j] = new Case(0); //Case blanche

        if(i==6)
          echiquier[i][j].setPion(new SimplePion(1)); //On rajoute les pions noirs
        if(i==1)
          echiquier[i][j].setPion(new SimplePion(0)); //On rajoute les pions blancs
      }
    }

    echiquier[0][0].setPion(new Tour(0));
    echiquier[0][1].setPion(new Cavalier(0));
    echiquier[0][2].setPion(new Fou(0));
    echiquier[0][3].setPion(new Dame(0));
    echiquier[0][4].setPion(new Roi(0));
    echiquier[0][5].setPion(new Fou(0));
    echiquier[0][6].setPion(new Cavalier(0));
    echiquier[0][7].setPion(new Tour(0));

    echiquier[7][0].setPion(new Tour(1));
    echiquier[7][1].setPion(new Cavalier(1));
    echiquier[7][2].setPion(new Fou(1));
    echiquier[7][3].setPion(new Dame(1));
    echiquier[7][4].setPion(new Roi(1));
    echiquier[7][5].setPion(new Fou(1));
    echiquier[7][6].setPion(new Cavalier(1));
    echiquier[7][7].setPion(new Tour(1));

  }

  public void sauvegarderPartie(String nomFichier) throws IOException, ClassNotFoundException
  {
    //Un fichier a un int représentant le nombre de coups, puis pour chaque case un char (nom du pion) et un int (couleur du pion) ou si elle est vide Z et -1, puis un int représentant le nombre de pièces blanches éliminées, puis ces pièces (un char) puis un int représentant le nombre de pièces noires éliminées, puis ces pièces (un char)

    DataOutputStream ostream = null;

    try
    {
      ostream = new DataOutputStream(new FileOutputStream(new File(nomFichier)));
      ostream.writeInt(nombreCoups);

      for(int i=0; i<8; i++)
      {
        for(int j=0; j<8; j++)
        {
          if(echiquier[i][j].getPion()==null)
          {
            ostream.writeChar('Z');
            ostream.writeInt(-1);
          }
          else
          {
            ostream.writeChar(echiquier[i][j].getPion().getNom().charAt(0) -'a' + 'A');
            ostream.writeInt(echiquier[i][j].getPion().getCouleur());
          }
        }
      }
      
      ostream.writeInt(pionsEliminesBlancs.size());
      for(int a=0; a<pionsEliminesBlancs.size(); a++)
      {
        ostream.writeChar(pionsEliminesBlancs.get(a).getNom().charAt(0) - 'a' + 'A');
      }

      ostream.writeInt(pionsEliminesNoirs.size());
      for(int b=0; b<pionsEliminesNoirs.size(); b++)
      {
        ostream.writeChar(pionsEliminesNoirs.get(b).getNom().charAt(0) - 'a' + 'A');
      }

    }
    finally
    {
      if(ostream!=null)
        ostream.close();
    }
  }

  public Echiquier(String nomFichier) throws IOException, ClassNotFoundException //Pour recréer une partie
  {
    //Un fichier a un int représentant le nombre de coups, puis pour chaque case un char (nom du pion) et un int (couleur du pion) ou si elle est vide Z et -1, puis un int représentant le nombre de pièces blanches éliminées, puis ces pièces (un char) puis un int représentant le nombre de pièces noires éliminées, puis ces pièces (un char)
    DataInputStream istream = null;

    try
    {
      istream = new DataInputStream(new FileInputStream(new File(nomFichier)));

      nombreCoups = istream.readInt();
      echiquier = new Case[8][8];
      pionsEliminesBlancs = new ArrayList<Pion>();
      pionsEliminesNoirs = new ArrayList<Pion>();

      char nomPiece = ' ';
      int couleurPiece;

      for(int i=0; i<8; i++)
      {
        for(int j=0; j<8; j++)
        {
          if((i+j)%2 == 0)
            echiquier[i][j] = new Case(1); //Case noire
          else
            echiquier[i][j] = new Case(0); //Case blanche

          nomPiece = istream.readChar();
          couleurPiece = istream.readInt();

          // S'il n'y a pas de pion : if(nomPiece=='Z' && couleur == -1)          
          if(nomPiece == 'P')
            echiquier[i][j].setPion(new SimplePion(couleurPiece));
          if(nomPiece == 'T')
            echiquier[i][j].setPion(new Tour(couleurPiece));
          if(nomPiece == 'C')
            echiquier[i][j].setPion(new Cavalier(couleurPiece));
          if(nomPiece == 'F')
            echiquier[i][j].setPion(new Fou(couleurPiece));
          if(nomPiece == 'D')
            echiquier[i][j].setPion(new Dame(couleurPiece));
          if(nomPiece == 'R')
            echiquier[i][j].setPion(new Roi(couleurPiece));

        }
      }
      int nbPiecesBlanchesEliminees = istream.readInt();

      for(int a=0; a<nbPiecesBlanchesEliminees; a++)
      {
        nomPiece = istream.readChar();
        if(nomPiece == 'P')
          pionsEliminesBlancs.add(new SimplePion(0));
        if(nomPiece == 'T')
          pionsEliminesBlancs.add(new Tour(0));
        if(nomPiece == 'C')
          pionsEliminesBlancs.add(new Cavalier(0));
        if(nomPiece == 'F')
          pionsEliminesBlancs.add(new Fou(0));
        if(nomPiece == 'D')
          pionsEliminesBlancs.add(new Dame(0));
        if(nomPiece == 'R')
          pionsEliminesBlancs.add(new Roi(0));
      }

      int nbPiecesNoiresEliminees = istream.readInt();
      for(int b=0; b<nbPiecesNoiresEliminees; b++)
      {
        nomPiece = istream.readChar();
        if(nomPiece == 'P')
          pionsEliminesNoirs.add(new SimplePion(1));
        if(nomPiece == 'T')
          pionsEliminesNoirs.add(new Tour(1));
        if(nomPiece == 'C')
          pionsEliminesNoirs.add(new Cavalier(1));
        if(nomPiece == 'F')
          pionsEliminesNoirs.add(new Fou(1));
        if(nomPiece == 'D')
          pionsEliminesNoirs.add(new Dame(1));
        if(nomPiece == 'R')
          pionsEliminesNoirs.add(new Roi(1));
      }

      echec(0);
      echec(1);

    }
    finally
    {
      if(istream!=null)
        istream.close();
    }
  }

  public void ecrirePartie(String nomFichier)
  {
    
  }

  public String avantage()
  {
    int pointsBlancs = 0, pointsNoirs = 0;
    for(int i = 0; i<8; i++)
    {
      for(int j = 0; j<8; j++)
      {
        if(echiquier[i][j].getPion()!=null && echiquier[i][j].getPion().getCouleur() == 0)
          pointsBlancs += echiquier[i][j].getPion().getValeur();
        if(echiquier[i][j].getPion()!=null && echiquier[i][j].getPion().getCouleur() == 1)
          pointsNoirs += echiquier[i][j].getPion().getValeur();
      }
    }
    if(pointsBlancs==pointsNoirs)
      return "Les blancs et les noirs sont à égalité en valeur de pièces.";
    if(pointsBlancs>pointsNoirs)
      return "Les blancs sont avantagés en valeur de pièces.";
    if(pointsBlancs<pointsNoirs)
      return "Les noirs sont avantagés en valeur de pièces.";
    return "Erreur de calcul d'avantage";
  }

  public void echec(int couleur)
  {
    if(couleur == 0)
    {

    }
    if(couleur == 1)
    {

    }

    //Provisoire :
    echecBlancs = false;
    echecNoirs = false;
    return;
  }

  public String toString()
  {
    String str = "\n       a    b    c    d    e    f    g    h\n";
    for(int i=7; i>=0; i--)
    {
      str += "     -----------------------------------------\n" + (i+1) + "    |";
      for(int j=0; j<8; j++)
      {
        str += " " + echiquier[i][j].toString() + " |";
      }
      str += "   " + (i+1) + "\n";
    }
    str += "     -----------------------------------------\n       a    b    c    d    e    f    g    h\n\n";

    if(pionsEliminesBlancs.size()>0)
    {
      str += "Pions blancs éliminés : ";
      for(int i=0; i<pionsEliminesBlancs.size(); i++)
      {
        str += (pionsEliminesBlancs.get(i).getNom().charAt(0) + " ").toUpperCase();
      }
      str += "\n";
    }

    if(pionsEliminesNoirs.size()>0)
    {
      str += "Pions noirs éliminés : ";
      for(int i=0; i<pionsEliminesNoirs.size(); i++)
      {
        str += (pionsEliminesNoirs.get(i).getNom().charAt(0) + " ").toUpperCase();
      }
      str += "\n";
    }

    str += nombreCoups + " coups joués\n";
    if(nombreCoups%2==1)
    {
      str += "C'est au tour des noirs.\n";
    }
    else
    {
      str += "C'est au tour des blancs.\n";
    }
    return str;
  }

  public void jouerCoup(int x1, int y1, int x2, int y2)
  {
    if(coupEstJouable(x1, y1, x2, y2))
    {
      nombreCoups++;

      Pion depart = echiquier[x1][y1].getPion();
      Pion arrivee = echiquier[x2][y2].getPion();

      if(echecBlancs)
      {
        //On joue le coup.
        echiquier[x2][y2].setPion(echiquier[x1][y1].getPion());
        echiquier[x1][y1].removePion();
        if(echiquier[x2][y2].getPion().nom.equals("pion") && (x2==7 || x2==0))
          this.monteeDePion(x2, y2, echiquier[x2][y2].getPion().getCouleur());
        
        //On regarde s'il y a encore échec.
        echec(0);

       //On annule le coup qui ne servait qu'à tester s'il y avait encore échec.        
       echiquier[x1][y1].setPion(depart);
       echiquier[x2][y2].setPion(arrivee);

        if(echecBlancs) //Si les blancs sont en échec et qu'après le coup ils le sont encore, on annule le coup et les blancs rejouent
        {
          nombreCoups--;
          return;
        }
      }
      if(echecNoirs)
      {
        //On joue le coup.
        echiquier[x2][y2].setPion(echiquier[x1][y1].getPion());
        echiquier[x1][y1].removePion();
        if(echiquier[x2][y2].getPion().nom.equals("pion") && (x2==7 || x2==0))
          this.monteeDePion(x2, y2, echiquier[x2][y2].getPion().getCouleur());
        
        //On regarde s'il y a encore échec.
        echec(1);

       //On annule le coup qui ne servait qu'à tester s'il y avait encore échec.        
       echiquier[x1][y1].setPion(depart);
       echiquier[x2][y2].setPion(arrivee);

        if(echecNoirs) //Si les noirs sont en échec et qu'après le coup ils le sont encore, on annule le coup et les noirs rejouent
        {
          nombreCoups--;
          return;
        }
      }

      if(echiquier[x2][y2].getPion()!=null)
      {
        if(echiquier[x2][y2].getPion().getCouleur()==0)
        {
          pionsEliminesBlancs.add(echiquier[x2][y2].getPion());
          Collections.sort(pionsEliminesBlancs); //Grâce à compareTo() redéfini dans la classe Pion
        }
        else
        {
          pionsEliminesNoirs.add(echiquier[x2][y2].getPion());
          Collections.sort(pionsEliminesNoirs);
        }
      }

      echiquier[x2][y2].setPion(echiquier[x1][y1].getPion());
      echiquier[x1][y1].removePion();
      if(echiquier[x2][y2].getPion().nom.equals("pion") && (x2==7 || x2==0))
        this.monteeDePion(x2, y2, echiquier[x2][y2].getPion().getCouleur());

      echec((nombreCoups-1)%2);
    }
  }

  public void monteeDePion(int x, int y, int couleur)
  {
    Scanner scanner = new Scanner(System.in);
    String reponse;
    do
    {
    System.out.println("Remplacer le pion par une dame (D), une tour (T), un fou (F) ou un cavalier (C) ?");
    reponse = scanner.nextLine();
    }while(!(reponse.equals("C") || reponse.equals("T") || reponse.equals("D") || reponse.equals("F")));

    if(reponse.equals("C"))
    {
      echiquier[x][y].setPion(new Cavalier(couleur));
    }
    if(reponse.equals("F"))
    {
      echiquier[x][y].setPion(new Fou(couleur));
    }
    if(reponse.equals("D"))
    {
      echiquier[x][y].setPion(new Dame(couleur));
    }
    if(reponse.equals("T"))
    {
      echiquier[x][y].setPion(new Tour(couleur));
    }
    return;
  }

  public boolean coupEstJouable(int x1, int y1, int x2, int y2)
  {
    if(x1>7 || y1>7 || x2>7 || y2>7 || x1<0 || y1<0 || x2<0 || y2<0)
    {
      System.out.println("Coup non jouable : sortie du plateau");
      return false;
    }
    if(echiquier[x1][y1].getPion()==null || (echiquier[x2][y2].getPion()!=null && echiquier[x2][y2].getPion().getNom().equals("roi")))
    {
      System.out.println("Coup impossible");
      return false;
    }
    if(x1==x2 && y1==y2)
    {
      System.out.println("Coup non jouable : c'est la même case.");
      return false;
    }
    if(echiquier[x2][y2].getPion()!=null && echiquier[x1][y1].getPion().getCouleur() == echiquier[x2][y2].getPion().getCouleur())
    {
      System.out.println("Coup non jouable : les pièces sont de la même couleur.");
      return false;
    }
    if(nombreCoups%2!=echiquier[x1][y1].getPion().getCouleur())
    {
      System.out.println("Coup non jouable : ce n'est pas au tour de ce joueur.");
      return false;
    }
    if(!echiquier[x1][y1].getPion().estJouable(x1, y1, x2, y2, obstacleLigneEntre(x1, y1, x2, y2), obstacleDiagonaleEntre(x1, y1, x2, y2), echiquier[x2][y2].getPion()!=null))
    {
      System.out.println("Coup non jouable pour cette pièce");
      return false;
    }

    return true;
  }

  public boolean obstacleDiagonaleEntre(int x1, int y1, int x2, int y2)
  {
    int i;

    if(Math.abs(x1-x2)!=Math.abs(y1-y2))
    {
      return true; //Si les cases ne sont pas sur la même diagonale
    }

    if(x1<x2 && y1<y2)
    {
      for(i=1; i<(x2-x1); i++)
      {
        if(echiquier[x1+i][y1+i].getPion()!=null)
        {
          return true;
        }
      }
      return false;
    }

    if(x1>x2 && y1>y2)
    {
      for(i=1; i<(x1-x2); i++)
      {
        if(echiquier[x2+i][y2+i].getPion()!=null)
        {
          return true;
        }
      }
      return false;
    }

    if(x1<x2 && y1>y2)
    {
      for(i=1; i<(x2-x1); i++)
      {
        if(echiquier[x1+i][y1-i].getPion()!=null)
        {
          return true;
        }
      }
      return false;
    }
    if(x1>x2 && y1<y2)
    {
      for(i=1; i<(x1-x2); i++)
      {
        if(echiquier[x1-i][y1+i].getPion()!=null)
        {
          return true;
        }
      }
      return false;
    }
    return true; //Cette ligne ne sera jamais executée.
  }

  public boolean obstacleLigneEntre(int x1, int y1, int x2, int y2)
  {
    int i;
    if(x1==x2)
    {
      if(y1>y2)
      {
        for(i=y1-1; i>y2; i--)
        {
          if(echiquier[x1][i].getPion()!=null)
            return true;
        }
      }
      else
      {
        for(i=y1+1; i<y2; i++)
        {
          if(echiquier[x1][i].getPion()!=null)
            return true;
        }
      }
      return false;
    }
    if(y1==y2)
    {
      if(x1>x2)
      {
        for(i=x1-1; i>x2; i--)
        {
          if(echiquier[i][y1].getPion()!=null)
            return true;
        }
      }
      else
      {
        for(i=x1+1; i<x2; i++)
        {
          if(echiquier[i][y1].getPion()!=null)
            return true;
        }
      }
      return false;
    }
    return true; // Par convention, considérons qu'il y a un obstacle si les cases envisagées ne sont pas sur la même ligne ou colonne ; le cas sera utile pour les mouvements des tours et dames.
  }

}
