package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dades.*;

public class UsaLlistaNivellsAigua {
	static Scanner teclat = new Scanner(System.in);

	private static void mostrarLlista(LlistaNivellsAigua l) {
		System.out.println(l.toStringl());
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Indica el número de línies a llegir del fitxer (màxim 78282)");
		int numLinies = Integer.parseInt(teclat.nextLine());
		String[] dataset = llegirLiniesFitxer(numLinies);
		LlistaNivellsAigua llista = new LlistaNivellsAigua(numLinies);

		/* Separem els atributs de l'objecte NivellAigua*/
		for (int i = 0; i < dataset.length; i++) {
			System.out.println("Linia " + (i + 1) + " conté " + dataset[i]);

			String[] dades = dataset[i].split(",");

			String[] dates = dades[0].split("/");
			int dia = Integer.parseInt(dates[0].trim());
			int mes = Integer.parseInt(dates[1].trim());
			int any = Integer.parseInt(dates[2].trim());
	
			String[] infoEmb = dades[1].split("\\(");
			String nomEmbassament = infoEmb[0].trim();
			String poblacio = infoEmb[1].trim();
	
			String provincia = dades[2].trim();
			double nivell = Double.parseDouble(dades[3].trim());
			double percentatgeVolum = Double.parseDouble(dades[4].trim());
			double volum = Double.parseDouble(dades[5].trim());
	
			Data data = new Data(dia,mes,any);
			NivellAigua emb = new NivellAigua(data, nomEmbassament, poblacio, provincia, nivell, percentatgeVolum, volum);
			llista.afegirDades(emb);
		}
		
		int opcio;
		mostrarMenu();
		opcio = Integer.parseInt(teclat.nextLine());
		while (opcio != 10) {	//Si l'usuari escull 10 surt del programa
			switch (opcio) {
				case 1:
					opcio1(llista);
					break;
				case 2:
					opcio2(llista);
					break;
				case 3:
					opcio3(llista);
					break;
				case 4:
					opcio4(llista);
					break;
				case 5:
					opcio5(llista);
					break;
				case 6:
					opcio6(llista);
					break;
				case 7:
					opcio7(llista);
					break;
				case 8:
					opcio8(llista);
					break;
				case 9:
					opcio9(llista);
					break;
			}

			mostrarMenu();
			opcio = Integer.parseInt(teclat.nextLine());
		}

	}

	private static String[] llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
		String[] result;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 78282)
			nLinies = 78282;
		result = new String[nLinies];
		Scanner f = new Scanner(new File("Quantitat_aigua_embassaments_20231025.csv"));

		String capcalera = f.nextLine();
		System.out.println("El format de les dades en cada línia és el següent\n" + capcalera);
		for (int i = 0; i < nLinies; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		return result;
	}
	/* Mostra un menú amb les diferents opcions que pot escollir l'usuari */
	public static void mostrarMenu() {
		System.out.println("\n\nOpcions del menu:");
		System.out.println("\n\t1. Mostrar el conjunt de mesures de la llista");
		System.out.println("\t2. Mostrar el conjunt de mesures d'una província entre una franja de dates");
		System.out.println("\t3. Consultar les dades de la primera mesura que hi ha a la llista d'un cert embassament");
		System.out.println("\t4. Consultar les dades de la mesura que té un percentatge de volum més alt dels embassaments de cada una de les províncies");
		System.out.println("\t5. Consultar les dades de la mesura que té un percentatge de volum més baix de totes les mesures que tenim a la llista");
		System.out.println("\t6. Consultar i mostrar de quants embassaments tenim dades en una certa província");
		System.out.println("\t7. Calcular en quina de les dues províncies s'ha trobat aquell any amb un volum d'aigua més alt");
		System.out.println("\t8. Consultar les dades de mesura en un període de temps");
		System.out.println("\t9. Eliminar el conjunt de mesures dels embassaments d'una província");
		System.out.println("\t10. Sortir del programa");
		System.out.print("\n\t\t\tIndica opcio:\n");
	}

	/* Validem l'us del mostrarLlista, on es mostren totes les mesures de al llista */
	public static void opcio1(LlistaNivellsAigua llis) {
		mostrarLlista(llis);
	}

	/* Funció que retorna les mesures d'una certa provincia dins d''unes dates, validant aixi les funcions
	 * consultarEmbProvincia i consultaEmbEnTemps
	 */
	public static void opcio2(LlistaNivellsAigua llis){
		String prov;
		int dia1, mes1, any1, dia2, mes2, any2;
		System.out.println("Introdueix la provincia que vols");
		prov = teclat.nextLine();
		System.out.println("Ara digues el dia del primer interval");
		dia1 = Integer.parseInt(teclat.nextLine());
		System.out.println("Ara digues el mes del primer interval");
		mes1 = Integer.parseInt(teclat.nextLine());
		System.out.println("Ara digues el any del primer interval");
		any1 = Integer.parseInt(teclat.nextLine());
		System.out.println("Ara digues el dia del segon interval");
		dia2 = Integer.parseInt(teclat.nextLine());
		System.out.println("Ara digues el mes del segon interval");
		mes2 = Integer.parseInt(teclat.nextLine());
		System.out.println("Ara digues el any del segon interval");
		any2 = Integer.parseInt(teclat.nextLine());

		Data data1 = new Data(dia1, mes1, any1);
		Data data2 = new Data(dia2, mes2, any2);

		mostrarLlista(llis.consultarEmbProvincia(prov).consultaEmbEnTemps(data1, data2));
	}
	
	/* Funció que retorna la primera mesura de la llista validant així la funció
	 * getEmbPrimr
	 */
	public static void opcio3(LlistaNivellsAigua llis){
		String nom;
		System.out.println("Introdueix el nom del embassament");
		nom = teclat.nextLine();
		NivellAigua emb = llis.getEmbPrimr(nom);
		System.out.println(emb);
	}

	/* Funció que retorna les mesures de les provincies de Catalunya amb el percentatge de volum mes alt, validant aixi les funcions
	 * consultarEmbProvincia i duplicatPercentVolumMesAlt
	 */
	public static void opcio4(LlistaNivellsAigua llis){
		LlistaNivellsAigua embTarragona = llis.consultarEmbProvincia("Tarragona");
		LlistaNivellsAigua embBarcelona = llis.consultarEmbProvincia("Barcelona");
		LlistaNivellsAigua embLleida = llis.consultarEmbProvincia("Lleida");
		LlistaNivellsAigua embGirona = llis.consultarEmbProvincia("Girona");
		NivellAigua PercentAltTarragona = embTarragona.duplicatPercentVolumMesAlt();
		NivellAigua PercentAltBarcelona = embBarcelona.duplicatPercentVolumMesAlt();
		NivellAigua PercentAltLleida = embLleida.duplicatPercentVolumMesAlt();
		NivellAigua PercentAltGirona = embGirona.duplicatPercentVolumMesAlt();
		System.out.println("Percentatge de volum més alt de Tarragona:"+PercentAltTarragona);
		System.out.println("Percentatge de volum més alt de Barcelona:"+PercentAltBarcelona);
		System.out.println("Percentatge de volum més alt de Lleida:"+PercentAltLleida);
		System.out.println("Percentatge de volum més alt de Girona:"+PercentAltGirona);

	}

	/* Funció que retorna la mesura que te el percentatge de volum mes baix, validant aixi les funcions
	 * duplicatPercentMesBaix
	 */
	public static void opcio5(LlistaNivellsAigua llis){
		NivellAigua emb = llis.duplicatPercentVolumMesBaix();
		System.out.println(emb);
	}

	/* Funció que retorna el numero de mesures d'una certa provincia i el nomb d'embassaments  que hi ha, validant aixi les funcions
	 * cconsultarEmbProvincia 
	 */
	public static void opcio6(LlistaNivellsAigua llis){
		System.out.println("Introdueix la provincia que vols");
        String prov;
        prov = teclat.nextLine();
        LlistaNivellsAigua embProv = llis.consultarEmbProvincia(prov);
        System.out.println("Hi han "+embProv.getNumNiells()+" embassaments");
        System.out.println("Els noms d'aquests embassaments son:");
        for(int i=0; i< embProv.getNumNiells(); i++) {
            String nomActual = embProv .getNivellAigua(i).getNomEmbassament();
            boolean repetido = false;

            for (int j= 0; j<i; j++) 
			{
                if(embProv.getNivellAigua(j).getNomEmbassament().equals(nomActual)) {
                    repetido = true;
                    break;
                }
            }
        	if(!repetido) {
         	   System.out.println(nomActual);
       		}
        }
	}

	/* Funció que retorna quina de dos provincias en un any concret te la mesura amb el volum mes alt, validant aixi la funcio
	 * consultarEmbProvincia, consultaEmbEnTemps i duplicatVolumMesAlt
	 */
	public static void opcio7(LlistaNivellsAigua llis){
		int any;
		String prov1, prov2;
		System.out.println("Indica el any que vols");
		any = Integer.parseInt(teclat.nextLine());
		System.out.println("Indica la primera provincia");
		prov1 = teclat.nextLine();
		System.out.println("Indica la segona provincia");
		prov2 = teclat.nextLine();
		Data data1 = new Data(1,1,any);
		Data data2 = new Data(31, 12, any);

		LlistaNivellsAigua embProv1 = llis.consultarEmbProvincia(prov1).consultaEmbEnTemps(data1, data2);
		LlistaNivellsAigua embProv2 = llis.consultarEmbProvincia(prov2).consultaEmbEnTemps(data1, data2);

		if(embProv1.duplicatVolumMesAlt().getVolum() < embProv2.duplicatVolumMesAlt().getVolum())
		{
			System.out.println("La provincia " +prov2+ " te el volum d'aigua mes alt en l'any " +any);
		}
		else if(embProv1.duplicatVolumMesAlt().getVolum() > embProv2.duplicatVolumMesAlt().getVolum())
		{
			System.out.println("La provincia " +prov1+ " te el volum d'aigua mes alt en l'any " +any);
		}
		if(embProv1.duplicatVolumMesAlt().getVolum() == embProv2.duplicatVolumMesAlt().getVolum())
		{
			System.out.println("Les dues provincies tene el mateix volum d'aigua en l'any " +any);
		}
	}

	/* Funció que retorna les mesures dins d' un interval de dates, validant aixi la funcio
	 * consultaEmbEnTemps
	 */
	public static void opcio8(LlistaNivellsAigua llis){
		int dia1, mes1, any1, dia2, mes2, any2;

        System.out.println("Digues el dia del primer interval");
        dia1 = Integer.parseInt(teclat.nextLine());
        System.out.println("Ara digues el mes del primer interval");
        mes1 = Integer.parseInt(teclat.nextLine());
        System.out.println("Ara digues el any del primer interval");
        any1 = Integer.parseInt(teclat.nextLine());
        System.out.println("Ara digues el dia del segon interval");
        dia2 = Integer.parseInt(teclat.nextLine());
        System.out.println("Ara digues el mes del segon interval");
        mes2 = Integer.parseInt(teclat.nextLine());
        System.out.println("Ara digues el any del segon interval");
        any2 = Integer.parseInt(teclat.nextLine());

        Data data1 = new Data(dia1, mes1, any1);
        Data data2 = new Data(dia2, mes2, any2);

        mostrarLlista(llis.consultaEmbEnTemps(data1, data2));
	}

	/* Funció que que elimina les mesures d'una certa provincia, validant aixi la funcio
	 * elimina
	 */
	public static void opcio9(LlistaNivellsAigua llis){
		System.out.println("Llista actual:");
		mostrarLlista(llis);
		System.out.println("Introdueix la provincia que vols");
		String prov;
		prov = teclat.nextLine();
		llis.elimina(prov);
		mostrarLlista(llis);
	}
}