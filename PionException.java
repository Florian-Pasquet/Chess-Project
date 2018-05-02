public class PionException extends Exception
{
  public PionException(String message)
  {
    super("Problème de pion : " + message);
  }

}
