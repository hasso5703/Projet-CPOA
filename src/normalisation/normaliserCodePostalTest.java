package normalisation;

import static org.junit.Assert.assertEquals;

import modele.NormaliserAdresse;

import org.junit.Test;

public class normaliserCodePostalTest 
{
    String res;

    @Test
    public void testChaineVide()
    {
        res = NormaliserAdresse.normaliserCodePostal("");
        assertEquals("", res);
    }

    @Test
    public void testChaineNull()
    {
        res = NormaliserAdresse.normaliserCodePostal(null);
        assertEquals(null, res);
    }

    @Test
    public void testChaineNombre5()
    {
        res = NormaliserAdresse.normaliserCodePostal("57320");
        assertEquals("57320", res);
    }

    @Test
    public void testChaineNombre4IdPays()
    {
        res = NormaliserAdresse.normaliserCodePostal("L-6450");
        assertEquals("6450", res);
    }

    @Test
    public void testChaineNombre5IdPays()
    {
        res = NormaliserAdresse.normaliserCodePostal("L-4532");
        assertEquals("4532", res);
    }

    @Test
    public void testChaineLettre()
    {
        res = NormaliserAdresse.normaliserCodePostal("MNOP");
        assertEquals("", res);
    }
}
