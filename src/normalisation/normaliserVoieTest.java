package normalisation;

import static org.junit.Assert.assertEquals;

import modele.NormaliserAdresse;
import org.junit.Test;


public class normaliserVoieTest 
{
    String res;

    @Test
    public void testChaineVide()
    {
        res = NormaliserAdresse.normaliserVoie("");
        assertEquals(", ", res);
    }

    @Test
    public void testChaineNull()
    {
        res = NormaliserAdresse.normaliserVoie(null);
        assertEquals(null, res);
    }

    @Test
    public void testChaineNombre()
    {
        res = NormaliserAdresse.normaliserVoie("4sqdq6843");
        assertEquals(", 4sqdq6843", res);
    }

    @Test
    public void testChaineMajuscule()
    {
        res = NormaliserAdresse.normaliserVoie("BOULEVARD PEXAN");
        assertEquals(", boulevard pexan", res);

        res = NormaliserAdresse.normaliserVoie("BOulEVard PexaN");
        assertEquals(", boulevard pexan", res);
    }

    @Test
    public void testChaineMinuscule()
    {
        res = NormaliserAdresse.normaliserVoie("faubourg honoré");
        assertEquals(", faubourg honoré", res);
    }

    @Test
    public void testCasRemplacement()
    {
        // test boulevard
        res = NormaliserAdresse.normaliserVoie("boUL Pexan");
        assertEquals(", boulevard pexan", res);

        res = NormaliserAdresse.normaliserVoie("boUl. pexan");
        assertEquals(", boulevard pexan", res);

        res = NormaliserAdresse.normaliserVoie("bd pexan");
        assertEquals(", boulevard pexan", res);

        // test faubourg
        res = NormaliserAdresse.normaliserVoie("Faub. pexan");
        assertEquals(", faubourg pexan", res);

        res = NormaliserAdresse.normaliserVoie("fG pexan");
        assertEquals(", faubourg pexan", res);

        // test avenue
        res = NormaliserAdresse.normaliserVoie("aV pExAn");
        assertEquals(", avenue pexan", res);

        res = NormaliserAdresse.normaliserVoie("av. pexan");
        assertEquals(", avenue pexan", res);

        // test place
        res = NormaliserAdresse.normaliserVoie("pL pExAn");
        assertEquals(", place pexan", res);

        res = NormaliserAdresse.normaliserVoie("pl. pexan");
        assertEquals(", place pexan", res);
    }
}