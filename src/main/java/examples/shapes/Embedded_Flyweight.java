package examples.shapes;

import java.util.HashMap;

public class Embedded_Flyweight {

        private static HashMap<String, EmbeddedPicture> hm =
                new HashMap<String, EmbeddedPicture>();

        public static EmbeddedPicture getEmbeddedPicture(String type)
        {
            EmbeddedPicture embeddedPicture = null;

            if (hm.containsKey(type))
                embeddedPicture = hm.get(type);
            else
            {
                switch(type)
                {
                    case "cat":
                        embeddedPicture = new EmbeddedPicture("Output/cat.jpg");
                        break;
                    case "usu":
                        embeddedPicture = new EmbeddedPicture("Output/dog.jpg");
                        break;
                    default :
                        System.out.println("Unreachable code!");
                }
                hm.put(type, embeddedPicture );
            }
            return embeddedPicture;
        }
    }
