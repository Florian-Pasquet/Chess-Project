import java.util.Scanner;

public class TestEchiquier
{
  public static void main(String[] args)
  {
    Echiquier echiquier = new Echiquier();
    System.out.println(echiquier.toString());

    Scanner scanner = new Scanner(System.in);
    String xy1, xy2;
    int x1, y1, x2, y2;

    while(true)
    {
      System.out.println("Saisir les coordonnées de la pièce à jouer (a1, b3, c8...) ou A pour arrêter de jouer :");
      xy1 = scanner.nextLine();
      if(xy1.equals("A"))
        break;
      System.out.println("Saisir les coordonnées de la case d'arrivée :");
      xy2 = scanner.nextLine();
      y1 = (int)(xy1.charAt(0) - 'a');
      x1 = ((int)(xy1.charAt(1)) - 49);
      y2 = (int)(xy2.charAt(0) - 'a');
      x2 = ((int)(xy2.charAt(1)) - 49);
      System.out.println("x1 : " + x1 + " y1 : " + y1 + " x2 : " + x2 + " y2 : " + y2);
      echiquier.jouerCoup(x1, y1, x2, y2);
      System.out.println(echiquier.toString());
      System.out.println(echiquier.avantage() + "\n");
    }
  }
}
