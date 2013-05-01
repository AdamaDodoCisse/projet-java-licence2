package main;
import java.awt.Event;
import java.io.FileNotFoundException;
import java.io.IOException;
import structureDeDonnee.PlateauCirculaire;
import structureDeDonnee.PlateauFini;
import structureDeDonnee.PlateauInfini;
import structureDeDonnee.StructureDeDonneeFactory;
import jeuDeLaVie.HTMLGenerateur;
import jeuDeLaVie.JeuDeLaVie;
import jeuDeLaVie.ReconnaissanceType;
import jeuDeLaVie.Simulation;
import exception.CommandeException;
import exception.HTMLException;
import exception.LectureException;
import interface_.Matrice;

/**
 * <b>Commande est la classe qui gère les paramètre de lencement du jeu de la vie.</b>
 * <p>
 * Un objet Commande est caractérisé par :
 * <ul>
 * <li>Un plateau du jeu de la vie .</li>
 *</ul>
 * </p>
 * @see PlateauFini
 * @see PlateauInfini
 * @see PlateauCirculaire
 * @see JeuDeLaVie
 * @author kouyate
 *
 */
public class Commande {
	/**
	 * Pateau de jeu la vie
	 * @see Matrice
	 */
	private Matrice plateau;
	/**
	 * Constructeur Commande.
	 * @param args
	 * 				Un tableau de chaine de caractère conténant les commandes.
	 * @throws LectureException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @see Commande
	 */
	public Commande(String []args) throws LectureException, IOException, InterruptedException{
		
		try {
			executer(args);
			
		} catch (CommandeException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Affiche les options du programme.
	 * @throws FileNotFoundException 
	 */
	private void aider() throws FileNotFoundException{
		System.out.println((char)Event.ESCAPE + "[2J");
		System.out.print("JEU DE LA VIE : OPTIONS ET COMMANDES DU PROGRAMME\n\n" +
				"Afficher les noms et prénoms des concepteurs du jeu :\n" +
				"	java -jar JeuDeLaVie.jar -name\n\n" +
				"Afficher la liste des options du programme :\n" +
				"	java -jar JeuDeLaVie.jar -h\n\n" +
				"Exécuter une simulation du jeu d'une durée d :" +
				"	java -jar JeuDeLaVie.jar -s d fichier.lif\n\n" +
				"Calculer le type d'évolution du jeu avec ses caracteristiques \n" +
				"max répresente la durée maximal pour déduire les résultats du calcule :\n" +
				"	java -jar JeuDeLaVie.jar -c max fichier.lif\n\n" +
				"Calculer le type d'évolution de tous les jeux contenus dans le dossier passé \n" +
				"en paramètre et affiche les résultats sous forme d'un fichier HTML :\n" +
				"	java -jar JeuDeLaVie.jar -w max dossier\n\n" +
				"Pour les même actions dans un Plateau Fini ou Circulaire : \n" +
				"Taper la commande correspodant à votre action parmis les commandes çi-dessu suivi du type de plateau :\n" +
				"	1 = Plateau Infini\n" +
				"	2 = Plateau Fini\n" +
				"	3 = Plateau Circulaire\n\n" +
				"Exemple = Pour éffectuer une simulation du jeu dans un plateau fini d'une durée d \n" +
				"	java -jar JeuDeLaVie.jar -s d 2\n\n");
		
		
	}
	/**
	 * Affiche la liste des réalisateurs du jeu
	 */
	
	private void groupe(){
		//System.out.println((char)Event.ESCAPE + "[2J");
		System.out.println("Nom et Prénom : \n"+
				"- KOUYATE Sory\n" +
				"- MELAINE \n" +
				"- DIALLO Youssouf\n" +
				"- CISSE Adama Dodo\n");
	}
	/**
	 * Execute une simulation d'un jeu de la vie sur une durée donné.
	 * @param nomFichier
	 * 				Un nom fichier en chaine de caractère.
	 * @param temps
	 * 				Un entier correspondant au nombre d'évolution à éffectuer.
	 * @param typePlateau
	 * 				Un entier coorespondant au type de plateau du jeu de la vie.
	 * @throws LectureException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @see JeuDeLaVie
	 * @see PlateauFini
	 * @see PlateauInfini
	 * @see PlateauCirculaire
	 */
	private void simuler(String nomFichier,int temps,int typePlateau) throws LectureException, IOException, InterruptedException{
		JeuDeLaVie jeu;
		try {
			plateau = StructureDeDonneeFactory.getPlateau(typePlateau, nomFichier);
			jeu = new JeuDeLaVie(plateau);
			new Simulation(temps, jeu).simuler();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	
	}
	/**
	 * Genere une page HTML caractérisant l'ensemble des fichiers contenuent dans le dossier passer en paramètre.
	 * @param nomDossier
	 * 				Un nom de dossier en chaine de caractère.
	 * @param temps
	 * 				Un entier correspondant au nombre d'évolution à éffectuer.
	 * @param typePlateau
	 * 				Un entier coorespondant au type de plateau du jeu de la vie.
	 * 				
	 * @param nomFichierHtml
	 * 				Une chaine de caractère qui correspond au nom du fichier HTML à générer.
	 * @throws LectureException 
	 * @throws IOException 
	 * @see HTMLGenerateur
	 * @see JeuDeLaVie
	 */
	private void genererHTML(String nomDossier,int temps,int typePlateau,String nomFichierHtml) throws LectureException, IOException {
		try {
			new HTMLGenerateur(nomDossier, temps, typePlateau, nomFichierHtml).generer(nomDossier);
		} catch (HTMLException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Affiche les caractèristique d'un jeu sur une durée donner.
	 * @param typePlateau
	 * 				Un entier coorespondant au type de plateau du jeu de la vie.
	 * @param temps
	 * 				Un entier correspondant au nombre d'évolution à éffectuer.
	 * @param nomFichier
	 * 				Un nom fichier en chaine de caractère.
	 * @throws LectureException 
	 * @throws IOException 
	 * @see ReconnaissanceType
	 * @see JeuDeLaVie
	 * 				
	 */
	private void analyserFichier(int typePlateau,int temps,String nomFichier) 
			throws LectureException, IOException{
		try {
			plateau = StructureDeDonneeFactory.getPlateau(typePlateau, nomFichier);
			ReconnaissanceType re = new ReconnaissanceType(temps, plateau);
			re.calculerTypeEvolution(temps);
			System.out.println((char)Event.ESCAPE + "[2J");
			System.out.println(re+"\n");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Executer la commande passer en paramètre
	 * @param args
	 * 				Un tableau de chaine de caractère conténant les commandes.
	 * @throws CommandeException
	 * 				Lève une exception lorsque la commande est invalide.
	 * @throws LectureException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @see CommandeException
	 */
	
	private void executer(String []args)throws CommandeException,
	LectureException, IOException, InterruptedException{
		
		if(args.length==0){
			System.out.println("Veuillez saisir une des commandes");
			System.out.println();
				aider();
		}
			
		if(args.length ==1  ||( args.length ==3 || args.length ==4)){
			if(args.length==1){
				
				if(args[0].equals("-name")){
						groupe();
				} else if (args[0].equals("-h")){
						aider();
				} else {
					throw new CommandeException("Commande inconnu !");
				}
					
			} else if (args.length == 3 || args.length == 4){
				
				if(args[1].matches("^[0-9]+$")){
					int temps = Integer.parseInt(args[1]);
					String nomRepertoire = args[2];
					if(args[0].equals("-s") && args.length==3){
						simuler(nomRepertoire,temps,StructureDeDonneeFactory.PLATEAU_INFINI);
					} else if (args[0].equals("-s")){
						try{
							int typePlateau = Integer.parseInt(args[3]);
							
							if(typePlateau >=1 && typePlateau<=3){
								simuler(nomRepertoire,temps,typePlateau);
							} else {
								throw new CommandeException("Le type du plateau doit être compris entre 1 et 3 \n " +
								"Pour plus d'information taper : \n java -jar JeuDeLaVie.jar -h \n");
							}
						}catch(Exception e){
							
							throw new CommandeException("Le quatrième argument doit être un entier compris entre 1 et 3\n" +
									"Pour plus d'information taper : \n java -jar JeuDeLaVie.jar -h \n");
						}
					}
					
					if(args[0].equals("-c") && args.length==3){
						analyserFichier(StructureDeDonneeFactory.PLATEAU_INFINI, 
										temps, nomRepertoire);
					} else if(args[0].equals("-c")) {
						try{
							int typePlateau = Integer.parseInt(args[3]);
							if(typePlateau>=1 && typePlateau<=3){
								analyserFichier(typePlateau, temps, nomRepertoire);
							} else {
								throw new CommandeException("Le type du plateau doit être compris entre 1 et 3 \n " +
								"Pour plus d'information taper : \n java -jar JeuDeLaVie.jar -h \n");
							}
								
						}catch(Exception e){
							throw new CommandeException("Le quatrième argument doit être un entier compris entre 1 et 3\n" +
									"Pour plus d'information taper : \n java -jar JeuDeLaVie.jar -h \n");
						}
					}
					if(args[0].equals("-w") && args.length==3){
						genererHTML(nomRepertoire, temps, 
								StructureDeDonneeFactory.PLATEAU_INFINI, "Jeu de la vie");
					} else if (args[0].equals("-w")){
						try{
							int a = Integer.parseInt(args[3]);
							if(a >=1 && a<=3){
								genererHTML(nomRepertoire, temps, a, "Jeu de la vie");
							} else {
								throw new CommandeException("Le type du plateau doit être compris entre 1 et 3 \n " +
										"Pour plus d'information taper : \n java -jar JeuDeLaVie.jar -h \n");
							}
								
						}catch(Exception e){
							throw new CommandeException("Le quatrième argument doit être un entier compris entre 1 et 3\n" +
									"Pour plus d'information taper : \n java -jar JeuDeLaVie.jar -h \n");
						}
					}
				} else {
					throw new CommandeException("Le deuxième Argument n'est pas un entier");
				}
					
			} else {
				
			}
		} else {
			throw new CommandeException("Commande invalide");
		}
	}
}
