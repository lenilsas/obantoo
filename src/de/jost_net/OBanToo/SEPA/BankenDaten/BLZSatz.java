/**
 * $Source$
 * $Revision$
 * $Date$
 * $Author$
 *
 * Copyright by Heiner Jostkleigrewe
 * Diese Datei steht unter LGPL - siehe beigef�gte lpgl.txt
 */
package de.jost_net.OBanToo.SEPA.BankenDaten;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;

public class BLZSatz
{

  private BufferedInputStream bi;

  /**
   * Die Bankleitzahl dient der eindeutigen Identifizierung eines
   * Zahlungsdienstleisters.
   */
  private String blz = null;

  /**
   * Merkmal, ob bankleitzahlf�hrender Zahlungsdienstleister ("1") oder nicht
   * ("2")
   */
  private String zahlungsdienstleister = null;

  /**
   * Bezeichnung des Zahlungsdienstleisters
   */
  private String bezeichnung = null;

  private String plz = null;

  private String ort = null;

  private String kurzbezeichnung = null;

  /**
   * F�r den Kartenzahlungsverkehr mittels Bankkundenkarten die am
   * girocard-System teilnehmen, haben die Spitzenverb�nde des Kreditgewerbes
   * eine gesonderte Institutsnummerierung festgelegt; danach erh�lt der
   * kartenausgebende Zahlungsdienstleister eine f�nfstellige Institutsnummer
   * f�r PAN (= Primary Account Number). Einer Institutsnummer f�r PAN ist immer
   * nur genau eine Bankleitzahl zugeordnet.
   */
  private String institutsnummerPAN = null;

  /**
   * Der Bank Identifier Code (BIC) besteht aus acht oder elf zusammenh�ngenden
   * Stellen und setzt sich aus den Komponenten BANKCODE (4 Stellen),
   * LÄNDERCODE (2 Stellen), ORTSCODE (2 Stellen) sowie ggf. einem FILIALCODE
   * (3 Stellen) zusammen.
   */
  private String bic = null;

  /**
   * Zahlungsdienstleister sind verpflichtet, zum Zahlungsverkehr
   * ausschließlich pr�fziffergesicherte Kontonummern gem�ߟ ihrer in der
   * Bankleitzahlendatei angegebenen Pr�fzifferberechnungsmethode zu verwenden.
   * Die Angabe der Pr�fzifferberechnungsmethode "09" (keine
   * Pr�fzifferberechnung) ist zul�ssig.
   */
  private String pruefziffernmethode = null;

  /**
   * Bei jeder Neuanlage eines Datensatzes wird automatisiert eine eindeutige
   * Nummer vergeben. Eine einmal verwendete Nummer wird nicht noch einmal
   * vergeben.
   */
  private String nummer = null;

  /**
   * Seit dem letzten Abschluss der Bankleitzahlendatei neu hinzugekommene
   * Datens�tze werden mit "A" (Addition), ge�nderte Datens�tze mit "M"
   * (Modified), unver�nderte Datens�tze mit "U" (Unchanged) gekennzeichnet.
   * Gel�schte Datens�tze werden mit "D" (Deletion) gekennzeichnet und sind -
   * als Hinweis - letztmalig in der Bankleitzahlendatei enthalten. Diese
   * Datens�tze sind ab dem G�ltigkeitstermin der Bankleitzahlendatei im
   * Zahlungsverkehr nicht mehr zu verwenden.
   */
  private String aenderungskennzeichen = null;

  /**
   * Zur fr�hzeitigen Information der Teilnehmer am Zahlungsverkehr und zur
   * Beschleunigung der Umstellung der Bankverbindung kann ein
   * Zahlungsdienstleister, der die L�schung einer Bankleitzahl mit dem Merkmal
   * "1" im Feld 2 beabsichtigt, die L�schung ank�ndigen. Die Ank�ndigung kann
   * erfolgen, sobald der Zahlungsdienstleister seine Kunden �ber die ge�nderte
   * Kontoverbindung informiert hat. Das Feld enth�lt das Merkmal "0" (keine
   * Angabe) oder "1" (Bankleitzahl im Feld 1 ist zur L�schung vorgesehen).
   */
  private String hinweisloeschung = null;

  /**
   * Das Feld enth�lt entweder den Wert "00000000" (Bankleitzahl ist nicht zur
   * L�schung vorgesehen bzw. der Zahlungsdienstleister hat keine
   * Nachfolge-Bankleitzahl ver�ffentlicht) oder die Angabe einer
   * "Bankleitzahl". Eine Bankleitzahl kann angegeben sein, wenn das Feld 2 das
   * Merkmal "1" enth�lt und entweder die bevorstehende L�schung der
   * Bankleitzahl angek�ndigt wurde (Feld 12 = "1") oder die Bankleitzahl zum
   * aktuellen Gültigkeitstermin gel�scht wird (Feld 11 = "D"). Auf Grund der
   * Ver�ffentlichung einer Nachfolge-Bankleitzahl k�nnen Anwender diese in
   * Zahlungsverkehrsdateien verwenden. Dazu wird in den Kontostammdaten –
   * unter Beibehaltung der Kontonummer - die zur L�schung angek�ndigte
   * Bankleitzahl bzw. die gel�schte Bankleitzahl im Feld 1 der
   * Bankleitzahlendatei durch die Nachfolge-Bankleitzahl dauerhaft ersetzt.
   * Zahlungsdienstleister sind nicht berechtigt, in Zahlungsverkehrsdateien
   * Bankleitzahlen durch Nachfolge-Bankleitzahlen zu ersetzen.
   */
  private String nachfolgeblz = null;

  private String ibanregel = null;

  private String zeile;

  private int pos = 0;

  public BLZSatz(BufferedReader br) throws IOException
  {
    zeile = br.readLine();
    if (zeile == null)
    {
      return;
    }
    blz = getString(8);
    zahlungsdienstleister = getString(1);
    bezeichnung = getString(58).trim();
    plz = getString(5);
    ort = getString(35);
    kurzbezeichnung = getString(27);
    institutsnummerPAN = getString(5);
    bic = getString(11);
    pruefziffernmethode = getString(2);
    nummer = getString(6);
    aenderungskennzeichen = getString(1);
    hinweisloeschung = getString(1);
    nachfolgeblz = getString(8);
    ibanregel = getString(6);
  }

  private String getString(int len)
  {
    String ret = zeile.substring(pos, pos + len);
    pos += len;
    return ret;
  }

  public String getBlz()
  {
    return blz;
  }

  public String getZahlungsdienstleister()
  {
    return zahlungsdienstleister;
  }

  public String getBezeichnung()
  {
    return bezeichnung;
  }

  public String getPlz()
  {
    return plz;
  }

  public String getOrt()
  {
    return ort;
  }

  public String getKurzbezeichnung()
  {
    return kurzbezeichnung;
  }

  public String getInstitutsnummerPAN()
  {
    return institutsnummerPAN;
  }

  public String getBic()
  {
    return bic;
  }

  public String getPruefziffernmethode()
  {
    return pruefziffernmethode;
  }

  public String getNummer()
  {
    return nummer;
  }

  public String getAenderungskennzeichen()
  {
    return aenderungskennzeichen;
  }

  public String getHinweisloeschung()
  {
    return hinweisloeschung;
  }

  public String getNachfolgeblz()
  {
    return nachfolgeblz;
  }

  public String getIBANRegel()
  {
    return ibanregel;
  }

  public boolean hasNext() throws IOException
  {
    return bi.available() > 0;
  }

  @Override
  public String toString()
  {
    return blz + ", " + bic + ", " + bezeichnung + ", Änderungskennzeichen="
        + aenderungskennzeichen + ", Hinweis L�schung=" + hinweisloeschung
        + ", Zahlungsdienstleister=" + zahlungsdienstleister;
  }
}
