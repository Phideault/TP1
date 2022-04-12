import java.util.Scanner;
import java.text.DecimalFormat;

public class Epicerie {

    //objet df permet de formatter les "floats" pour arrondir les cents dans les prix.
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {

        //Déclaration des variables.
        float taxTPS = 0.05f, taxTVQ = 0.1f, grandTotal = 0, prixProduit = 0, total = 0;
        String nomProduit = "", typeProduit = "", listeProduit = "";

        Scanner userInput;

        //Début de l'épicerie, l'épicerie sera en fonction tant que epicerieEnCours est vrai, variable erreur pour le erreur catching
        boolean epicerieEnCours = true, erreur, confirmation;

        do {
            //Initialisation de l'objet Scanner à chaque début de loop pour éviter l'erreur du buffer de l'objet
            userInput = new Scanner(System.in);

            //Choix du type de produit par l'utilisateur + confirmation
            confirmation = false;
            //Début du loop, tant qu'on a pas de confirmation positive, on recommence
            while(!confirmation) {
                do {
                    erreur = false;
                    System.out.println("""

                            Veuillez inscrire le type du produit:
                            Fruit: F
                            Legume: L
                            Taxable: T
                            Non Taxable: N""");
                    switch (userInput.nextLine().toLowerCase().charAt(0)) {
                        case 'f' -> typeProduit = "Fruit";
                        case 'l' -> typeProduit = "Legume";
                        case 't' -> typeProduit = "Taxable";
                        case 'n' -> typeProduit = "Non-taxable";
                        default -> {
                            erreur = true;
                            System.out.println("\n /!\\ Le type de produit choisis est invalide. /!\\");
                        }
                    }
                } while (erreur); //Ce loop ce termine quand il n'y a pas d'erreur
                System.out.println("\nVeuillez confirmer le type du produit suivant: " + typeProduit +
                        "\nOui: O" +
                        "\nNon: N");
                if(userInput.nextLine().toLowerCase().charAt(0) == 'o'){
                    confirmation = true;
                }
            }

            //Choix du nom de produit par l'utilisateur + confirmation
            confirmation = false;
            while(!confirmation) {
                System.out.println("\nVeuillez inscrire le nom du produit: ");
                nomProduit = userInput.nextLine();

                //Début de confirmation
                System.out.println("\nVeuillez confirmer le nom du produit: " + nomProduit +
                        "\nOui: O" +
                        "\nNon: N");
                if(userInput.nextLine().toLowerCase().charAt(0) == 'o'){
                    confirmation = true;
                }
            }

            //Détermination du prix par l'utilisateur
            confirmation = false;
            while(!confirmation) {
                erreur = false;
                System.out.println("\nVeuillez inscrire le prix du produit: " +
                        "\n(Format: xx.xx)");

                //Error Catching: Vérifie si le prix indiqué est bien un float et non du text
                try {
                    prixProduit = userInput.nextFloat();
                }catch(Exception e){
                    System.out.println("\n/!\\ Entrée invalide /!\\");
                    erreur = true;
                }
                //Clear le buffer du scanner
                userInput.nextLine();

                //S'il n'y a pas d'erreur, faire la confirmation du prix
                if(!erreur){
                    System.out.println("\nVeuillez confirmer le prix du produit: " + df.format(prixProduit) + "$" +
                            "\nOui: O" +
                            "\nNon: N");
                    if(userInput.nextLine().toLowerCase().charAt(0) == 'o'){
                        confirmation = true;
                    }
                }
            }

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

            //Ajout du produit à la liste d'achat
            listeProduit = listeProduit + "\nType de produit: " + typeProduit + " || Nom du produit: " + nomProduit + " || Prix: " + df.format(total) + "$";
            grandTotal += total;

            //Demande à l'utilisateur pour ajout d'un autre produit, si non, on termine les achats et on saute à la fin du programme
            do {
                System.out.println("\nDesirez vous ajouter un autre produit?" +
                        "\nOui: O" +
                        "\nNon: N");

                switch (userInput.nextLine().toLowerCase().charAt(0)) {
                    case 'n' -> {
                        epicerieEnCours = false;
                        erreur = false;
                    }
                    case 'o' -> {
                        System.out.println("\nDébut de l'ajout d'un nouveau produit.");
                        erreur = false;
                    }
                    default -> {
                        System.out.println("\n/!\\ Entrée invalide /!\\");
                        erreur = true;
                    }
                }
            } while(erreur);

        } while (epicerieEnCours);

        //Affiche la liste d'achat ainsi que le grand total
        System.out.println("\nVoici la liste d'achat: " + listeProduit);
        System.out.println("\nPour un grand total de: " + df.format(grandTotal) + "$");
        System.out.println("\nMerci d'avoir fait votre epicerie avec nous :)");

    }

}
