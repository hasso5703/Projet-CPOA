package modele;

public class NormaliserAdresse
{
    
    public static Adresse normaliser(Adresse adresse)
    {
        adresse.setVoie(normaliserVoie(adresse.getVoie()));
        adresse.setCodePostal(normaliserCodePostal(adresse.getCodePostal()));
        adresse.setVille(normaliserVille(adresse.getVille()));
        adresse.setPays(normaliserPays(adresse.getPays()));
        return adresse;
    }

    
    public static String normaliserPays(String pays)
    {
        if (pays != null) 
        { 
            pays = pays.trim().toLowerCase(); 
            if (!pays.equals(""))
            {
                switch (pays)
                {
                    case "letzebuerg":
                    {
                        pays = "luxembourg";
                        break;
                    }
                    case "belgium":
                    {
                        pays = "belgique";
                        break;
                    }

                    case "switzerland", "schweiz":
                    {
                        pays = "suisse";
                        break;
                    }
                }
                pays = String.valueOf(pays.charAt(0)).toUpperCase() + pays.substring(1);
                
            }    
        }
        return pays;
    }
    
    
    public static String normaliserCodePostal(String codePostal) 
    {
        if (codePostal != null)
        {
            codePostal = codePostal.trim();  

            if (!codePostal.equals("")) 
            {
                boolean etranger = false;
                boolean lettrePresente = false;  
                do 
                {
                    if (lettrePresente)        
                    {
                        codePostal = codePostal.substring(1);       
                    }
    
                    try 
                    {
                        int nombre = Integer.parseInt(codePostal);
                        
                        if (nombre < 0)         
                        {
                            lettrePresente = true;
                            etranger = true;
                        }
                        else
                        {
                            lettrePresente = false;
                        }
                    } 
                    catch (IllegalArgumentException e) 
                    {
                        lettrePresente = true;
                    }
                } while (lettrePresente && !codePostal.equals(""));     

                if (codePostal.length() == 4 && !etranger)    
                {
                    codePostal = "0" + codePostal;
                }
            }
        }
        return codePostal;
    }
    
    
    public static String normaliserVoie(String voie)
    {
        if (voie != null)
        {
            voie = voie.trim().toLowerCase();
            if (!voie.equals(""))
            {
                String[] tableauMot = voie.split(" ");
                String newVoie = "";

                for (String string : tableauMot)
                {
                    switch (string)
                    {
                        case "boul", "boul.", "bd" :
                        {
                            string = "boulevard";
                            break;
                        }
                        case "av.", "av" :
                        {
                            string = "avenue";
                            break;
                        }
                        case "faub.", "fg" :
                        {
                            string = "faubourg";
                            break;
                        }
                        case "pl.", "pl" :
                        {
                            string = "place";
                            break;
                        }
                    }

                    if (!newVoie.equals(""))
                    {
                        newVoie = newVoie + " ";
                    }

                    newVoie = newVoie + string;
                }

                if (!(newVoie.charAt(0) == ','))
                {
                    newVoie = ", " + newVoie;
                }

                return (newVoie);
            }
            else
            {
                return ", ";
            }
        }
        else
        {
            return null;
        }
    }
    
    
    public static String normaliserVille(String ville) 
    {
        if(ville != null && !ville.equals(""))
        {
            ville = ville.trim().toLowerCase();             // Formattage en tout minuscule

            String[] tableauMot = ville.split(" ");         // Separation dans un tableau
            boolean testPlus1Mot = (tableauMot.length > 1);

            String nouveauVille = "";

            for (String string : tableauMot) //verification pour chaque mot du nom de la ville
            { 
                boolean modifAbrege = true;
                // /!\ au cas ou la ville n'a qu'1 seul mot et c'est un pronom
                switch (string)
                {
                    case "sous" :
                    {
                        string = "-sous-";
                        break;
                    }
                    case "lès", "les" :
                    {
                        string = "-lès-";
                        break;
                    }
                    case "sur" :
                    {
                        string = "-sur-";
                        break;
                    }
                    case "aux" :
                    {
                        string = "-aux-";
                        break;
                    }
                    case "le" :
                    {
                        string = "-le-";
                        break;
                    }
                    case "à", "a" :
                    {
                        string = "-à-";
                        break;
                    }
                    case "st" :
                    {
                        string = "Saint-";
                        break;
                    }
                    case "ste" :
                    {
                        string = "Sainte-";
                    }
                    default :
                    {
                        // Si pas de mot a remplacer, alors on suppose que c'est un nom de ville et pas un pronom

                        modifAbrege = false;
                        string = String.valueOf(string.charAt(0)).toUpperCase() + string.substring(1);   // Premiere lettre majuscule


                        // Si on a deja composé la nvle chaine et que le dernier carac n'est pas un - alors on met un espace
                        if (!nouveauVille.equals("") && nouveauVille.charAt(nouveauVille.length() - 1) != '-')
                        {
                            string = " " + string;
                        }
                    }
                }

                if (modifAbrege && !testPlus1Mot) 
                {
                    string = string.replace("-", "");
                }

                nouveauVille = nouveauVille + string;
            }
            ville = nouveauVille.trim();
        }             
        return ville;
    }
    
}
