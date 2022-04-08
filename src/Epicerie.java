import java.util.Scanner;
import java.text.DecimalFormat;

public class Epicerie {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {

        float taxTPS = 0.05f, taxTVQ = 0.1f, grandTotal = 0, prixProduit, total = 0;
        String nomProduit, typeProduit, listeProduit = "";

        Scanner userInput;

        boolean epicerieEnCours = true;

        do {
            userInput = new Scanner(System.in);

            System.out.println("""

                    Veuillez inscrire le type du produit
                    (Fruit/Legume/Taxable/Non Taxable)""");
            typeProduit = userInput.nextLine();

            System.out.println("\nVeuillez inscrire le nom du produit: ");
            nomProduit = userInput.nextLine();

            System.out.println("\nVeuillez inscrire le prix du produit: ");
            prixProduit = userInput.nextFloat();

            System.out.println("\nLe produit suivant a ete ajoute au panier:" +
                    "\nType du produit: " + typeProduit +
                    "\nNom du produit: " + nomProduit +
                    "\nPrix du produit: " + df.format(prixProduit) + "$");

            total += prixProduit;

            if(typeProduit.equalsIgnoreCase("Taxable")){
                System.out.println("TPS: " + df.format(prixProduit * taxTPS));
                total += prixProduit * taxTPS;
                System.out.println("TVQ: " + df.format(prixProduit * taxTVQ));
                total += prixProduit * taxTVQ;
                System.out.println("Pour un total de: " + df.format(total) + "$");
            }

            userInput.nextLine();

            listeProduit = listeProduit + "\nType de produit: " + typeProduit + " || Nom du produit: " + nomProduit + " || Prix: " + df.format(total) + "$";
            grandTotal += total;

            System.out.println("\nDesirez vous ajouter un autre produit?");

            if(userInput.nextLine().equalsIgnoreCase("non")) {
                epicerieEnCours = false;
            }

        } while (epicerieEnCours);

        System.out.println("\nVoici la liste d'achat: " + listeProduit);
        System.out.println("\nPour un grand total de: " + df.format(grandTotal));
        System.out.println("\n Merci d'avoir fait votre epicerie avec nous :)");

    }

}
