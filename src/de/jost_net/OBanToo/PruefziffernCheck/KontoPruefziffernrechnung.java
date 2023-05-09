/*
 * $Source$
 * $Revision$
 * $Date$
 *
 * Copyright 2013 by Heiner Jostkleigrewe
 * Diese Datei steht unter LGPL - siehe beigef�gte lpgl.txt
 */
package de.jost_net.OBanToo.PruefziffernCheck;

import java.lang.reflect.Method;

public final class KontoPruefziffernrechnung
{

  private static Method getAccountCRCMethodByAlg(String alg) throws Exception
  {
    Class<?> cl = null;
    Method method = null;

    try
    {
      cl = AccountCRCAlgs.class;
      method = cl.getMethod("alg_" + alg, new Class[] { int[].class,
          int[].class});
    }
    catch (Exception e)
    {
      throw new Exception("CRC algorithm " + alg + " not yet implemented");
    }

    return method;
  }

  /**
   * <p>
   * �berpr�ft, ob gegebene BLZ und Kontonummer zueinander passen. Bei diesem
   * Test wird wird die in die Kontonummer "eingebaute" Pr�ziffer verifiziert.
   * Anhand der BLZ wird ermittelt, welches Pr�fzifferverfahren zur �berpr�fung
   * eingesetzt werden muss.
   * </p>
   * <p>
   * Ein positives Ergebnis dieser Routine bedeutet <em>nicht</em>, dass das
   * entsprechende Konto bei der Bank <em>existiert</em>, sondern nur, dass die
   * Kontonummer bei der entsprechenden Bank prinzipiell g�ltig ist.
   * </p>
   * 
   * @param blz
   *        die Bankleitzahl der Bank, bei der das Konto gef�hrt wird
   * @param number
   *        die zu �berpr�fende Kontonummer
   * @return <code>true</code> wenn die Kontonummer nicht verifiziert werden
   *         kann (z.B. weil das jeweilige Pr�fzifferverfahren noch nicht in
   *         <em>HBCI4Java</em> implementiert ist) oder wenn die Pr�fung
   *         erfolgreich verl�uft; <code>false</code> wird immer nur dann
   *         zur�ckgegeben, wenn tats�chlich ein Pr�fzifferverfahren zum
   *         �berpr�fen verwendet wurde und die Pr�fung einen Fehler ergab
   */
  public static PZRet checkAccountCRC(String alg, String blz, String number)
      throws Exception
  {
    PZRet ret;

    ret = checkAccountCRCByAlg(alg, blz, number);
    return ret;
  }

  /**
   * �berpr�fen einer Kontonummer mit einem gegebenen CRC-Algorithmus. Diese
   * Methode wird intern von HBCIUtils#checkAccountCRC(String,String)
   * aufgerufen und kann f�r Debugging-Zwecke auch direkt benutzt werden.
   * 
   * @param alg
   *        Nummer des zu verwendenden Pr�fziffer-Algorithmus (siehe Datei
   *        <code>blz.properties</code>).
   * @param blz
   *        zu �berpr�fende Bankleitzahl
   * @param number
   *        zu �berpr�fende Kontonummer
   * @return <code>false</code>, wenn der Pr�fzifferalgorithmus f�r die
   *         angegebene Kontonummer einen Fehler meldet, sonst <code>true</code>
   *         (siehe dazu auch checkAccountCRC(String, String))
   */
  public static PZRet checkAccountCRCByAlg(String alg, String blz, String number)
      throws Exception
  {
    PZRet ret = null;

    if (blz == null || number == null)
    {
      throw new NullPointerException("blz and number must not be null");
    }

    if (number.length() <= 10)
    {
      Method method = getAccountCRCMethodByAlg(alg);

      if (method != null)
      {
        try
        {
          int[] blz_digits = string2Ints(blz, 8);
          int[] number_digits = string2Ints(number, 10);

          Object[] args = new Object[] { blz_digits, number_digits};
          ret = (PZRet) method.invoke(null, args);
        }
        catch (Exception e)
        {
          return new PZRet(false, e);
        }
      }
    }
    return ret;
  }

  /**
   * Used to convert a blz or an account number to an array of ints, one array
   * element per digit.
   */
  private static int[] string2Ints(String st, int target_length)
  {
    int[] numbers = new int[target_length];
    int st_len = st.length();
    char ch;

    for (int i = 0; i < st_len; i++)
    {
      ch = st.charAt(i);
      numbers[target_length - st_len + i] = ch - '0';
    }

    return numbers;
  }

}
