package normalisation;

import static org.junit.Assert.assertEquals;

import modele.NormaliserAdresse;
import org.junit.Test;


public class normaliserVilleTest 
{
    String res;

    @Test
    public void testChaineVide()
    {
        res = NormaliserAdresse.normaliserVille("");
        assertEquals("", res);
    }

    @Test
    public void testChaineNull()
    {
        res = NormaliserAdresse.normaliserVille(null);
        assertEquals(null, res);
    }

    @Test
    public void testChaineNombre()
    {
        res = NormaliserAdresse.normaliserVille("4sqdq6843");
        assertEquals("4sqdq6843", res);
    }

    @Test
    public void testChaineMajuscule()
    {
        res = NormaliserAdresse.normaliserVille("BOULEVARD");
        assertEquals("Boulevard", res);

        res = NormaliserAdresse.normaliserVille("BOulEVard");
        assertEquals("Boulevard", res);

        res = NormaliserAdresse.normaliserVille("BOulEVard peXan");
        assertEquals("Boulevard Pexan", res);
    }

    @Test
    public void testChaineMinuscule()
    {
        res = NormaliserAdresse.normaliserVille("montigny");
        assertEquals("Montigny", res);
    }

    @Test
    public void testCasRemplacement()
    {
        // les
        res = NormaliserAdresse.normaliserVille("Marange lès metz");
        assertEquals("Marange-lès-Metz", res);

        res = NormaliserAdresse.normaliserVille("marange LèS metz");
        assertEquals("Marange-lès-Metz", res);

        res = NormaliserAdresse.normaliserVille("maRange lEs meTz");
        assertEquals("Marange-lès-Metz", res);
        
        // aux
        res = NormaliserAdresse.normaliserVille("marange aux metz");
        assertEquals("Marange-aux-Metz", res);

        // sous
        res = NormaliserAdresse.normaliserVille("marange sous metz");
        assertEquals("Marange-sous-Metz", res);

        res = NormaliserAdresse.normaliserVille("st marange sous metz");
        assertEquals("Saint-Marange-sous-Metz", res);

        res = NormaliserAdresse.normaliserVille("ste marange sous metz");
        assertEquals("Sainte-Marange-sous-Metz", res);
    }

    @Test
    public void testUniquementPreposition()
    {
        res = NormaliserAdresse.normaliserVille("lès");
        assertEquals("lès", res);
    }
}