package normalisation;

import static org.junit.Assert.assertEquals;

import modele.NormaliserAdresse;
import org.junit.Test;

public class normaliserPaysTest 
{
    String res;
    
    @Test
    public void testRemplacementMajuscule()
    {
        res = NormaliserAdresse.normaliserPays("LETZEBUERG");
        assertEquals("Luxembourg", res);

        res = NormaliserAdresse.normaliserPays("BELGIUM");
        assertEquals("Belgique", res);

        res = NormaliserAdresse.normaliserPays("SWITZERLAND");
        assertEquals("Suisse", res);

        res = NormaliserAdresse.normaliserPays("SCHWEIZ");
        assertEquals("Suisse", res);
    }  

    @Test
    public void testRemplacementMinuscule()
    {
        res = NormaliserAdresse.normaliserPays("letzebuerg");
        assertEquals("Luxembourg", res);

        res = NormaliserAdresse.normaliserPays("belgium");
        assertEquals("Belgique", res);

        res = NormaliserAdresse.normaliserPays("switzerland");
        assertEquals("Suisse", res);

        res = NormaliserAdresse.normaliserPays("schweiz");
        assertEquals("Suisse", res);
    } 

    @Test
    public void testChaineMinuscule()
    {
        res = NormaliserAdresse.normaliserPays("france");
        assertEquals("France", res);
    }

    @Test
    public void testChaineMajuscule()
    {
        res = NormaliserAdresse.normaliserPays("FRANCE");
        assertEquals("France", res);
    } 
    
    @Test
    public void testChaineNull()
    {
        res = NormaliserAdresse.normaliserPays(null);
        assertEquals(null, res);
    }

    @Test
    public void testChaineVide()
    {
        res = NormaliserAdresse.normaliserPays("");
        assertEquals("", res);
    }    
}
