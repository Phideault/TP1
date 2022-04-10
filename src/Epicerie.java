import java.util.Scanner;
import java.text.DecimalFormat;

public class Epicerie {

    //objet df permet de formatter les "floats" pour arrondir les cents dans les prix.
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {

        //Déclaration des variables.
        float taxTPS = 0.05f, taxTVQ = 0.1f, grandTotal = 0, prixProduit, total = 0;
        String nomProduit, typeProduit = "", listeProduit = "";

        Scanner userInput;

        //Début de l'épicerie, l'épicerie sera en fonction tant que cette valeur est vraie
        boolean epicerieEnCours = true;

        do {
            //Initialisation de l'objet Scanner à chaque début de loop pour éviter l'erreur du buffer de l'objet
            userInput = new Scanner(System.in);

            //Choix du type de produit par l'utilisateur
            System.out.println("\nVeuillez inscrire le type du produit:" +
                    "\nFruit: F" +
                    "\nLegume: L" +
                    "\nTaxable: T" +
                    "\nNon Taxable: N");
            switch (userInput.nextLine().toLowerCase().charAt(0)) {
                case 'f' -> typeProduit = "fruit";
                case 'l' -> typeProduit = "legume";
                case 't' -> typeProduit = "taxable";
                case 'n' -> typeProduit = "non-taxable";
                default -> typeProduit = "Erreur";
            }

            //typeProduit = userInput.nextLine();

            //Choix du nom de produit par l'utilisateur
            System.out.println("\nVeuillez inscrire le nom du produit: ");
            nomProduit = userInput.nextLine();

            //Détermination du prix par l'utilisateur
            System.out.println("\nVeuillez inscrire le prix du produit: ");
            prixProduit = userInput.nextFloat();

            //Sommaire du produit ajouter
            System.out.println("\nLe produit suivant a ete ajoute au panier:" +
                    "\nType du produit: " + typeProduit +
                    "\nNom du produit: " + nomProduit +
                    "\nPrix du produit: " + df.format(prixProduit) + "$");

            //Ajout du prix au total
            total += prixProduit;

            //Si les taxes sont applicable, calcul des taxes et les ajoute au total
            if(typeProduit.equalsIgnoreCase("Taxable")){
                System.out.println("TPS: " + df.format(prixProduit * taxTPS) + "$");
                total += prixProduit * taxTPS;
                System.out.println("TVQ: " + df.format(prixProduit * taxTVQ ) + "$");
                total += prixProduit * taxTVQ;
                System.out.println("Pour un total de: " + df.format(total) + "$");
            }

            //Efface le buffer du Scanner
            userInput.nextLine();

            //Ajout du produit à la liste d'achat
            listeProduit = listeProduit + "\nType de produit: " + typeProduit + " || Nom du produit: " + nomProduit + " || Prix: " + df.format(total) + "$";
            grandTotal += total;

            //Demande à l'utilisateur pour ajout d'un autre produit, si non, on termine les achats et on saute à la fin du programme
            System.out.println("\nDesirez vous ajouter un autre produit?");
            if(userInput.nextLine().equalsIgnoreCase("non")) {
                epicerieEnCours = false;
            }

        } while (epicerieEnCours);

        //Affiche la liste d'achat ainsi que le grand total
        System.out.println("\nVoici la liste d'achat: " + listeProduit);
        System.out.println("\nPour un grand total de: " + df.format(grandTotal) + "$");
        System.out.println("\nMerci d'avoir fait votre epicerie avec nous :)");

    }

}
