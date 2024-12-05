package dades;

public class LlistaNivellsAigua {
    private NivellAigua[] llista;
    private int numNivells;

    public LlistaNivellsAigua (int n) {
        llista = new NivellAigua[n];
        numNivells = 0;
    }

    public int getNumNiells(){
        return(numNivells);
    }

    /**
     * Afegim noves mesures al final de la llista
     * @param p
     */
    public void afegirDades(NivellAigua p){
        if(numNivells<llista.length)
        {
            llista[numNivells] = p.copia();
            numNivells++;
        }
    }

    /**
     * Retornem la primera mesura que trobem a la llista d'un cert embassament. Rebem el nom de l'embassament per paràmetre
     * @param embas
     * @return la primera mesura d'un embassament
     */
    public NivellAigua getEmbPrimr(String embas){
        int i;
        boolean trobat = false;
        for(i = 0; i < numNivells && trobat; i++)
        {
            if(llista[i].esAquestEmbassament(embas))
            {
                trobat = true;
            }
        }
        if (!trobat)
        {
            return(llista[i]);
        }
        else
        {
            return(null);
        }
    }

    /**
     * Retornem la mesura que te el percentatge de volum mes alt
     * @return El percentatge de volum mes alt de la llista
     */
    public NivellAigua duplicatPercentVolumMesAlt() {
    
        if (numNivells == 0){
            return null;
        }
        
        NivellAigua percentMesAlt = llista[0].copia();
    
        for (int i=1; i<numNivells; i++) {
            if(llista[i].getPercentVolum()>=percentMesAlt.getPercentVolum())
            {
                percentMesAlt = llista[i].copia();
            }
        }
    
        return percentMesAlt;
    }

    /**
     * Retornem la mesura que te el percentatge de volum mes baix
     * @return el percentatge de volum mes baix de la llista
     */
    public NivellAigua duplicatPercentVolumMesBaix() {
    
        if (numNivells == 0) {
            return null;
        }
        
        NivellAigua percentMesBaix = llista[0].copia();
    
        for (int i=1; i<numNivells; i++) {
            if(llista[i].getPercentVolum()<=percentMesBaix.getPercentVolum())
            {
                percentMesBaix = llista[i].copia();
            }
        }
    
        return percentMesBaix;
    }

    /**
     * Guarda una llista amb el nomb de cada embassament
     * @return llista amb noms dels embassaments
     */
    public String[] embAmbDades(){
        String[] tenim = new String[numNivells];
        int i;
        for(i = 0; i < numNivells; i++)
        {
            tenim[i] = llista[i].getNomEmbassament();
        }
        return (tenim);
    }

    /**
     * Funcio que retorna un duplicat amb la mesura que te el volum mes alt
     * @return mesura amb el volum mes alt
     */
    public NivellAigua duplicatVolumMesAlt() {

        if (numNivells == 0) {
            return null;
        } 
    
        NivellAigua volumMesAlt = llista[0].copia();
    
        for (int i=1; i<numNivells; i++) {
            if(llista[i].getVolum()>=volumMesAlt.getVolum())
            {
                volumMesAlt = llista[i].copia();
            }
        }
    
        return volumMesAlt;
    }

    /**
     * Busca les mesures de una certa provincia
     * @param provincia
     * @return llista amb mesures de la provincia
     */
    public LlistaNivellsAigua consultarEmbProvincia(String provincia) {

        if (numNivells == 0) {
            return null;
        }
        
        LlistaNivellsAigua llistaProvincia = new LlistaNivellsAigua(numNivells);
        for (int i=0; i<numNivells; i++) {
            if (llista[i].esTrobaEnAquestaProvincia(provincia)){
                llistaProvincia.afegirDades(llista[i]);
            }          
        }   

        return llistaProvincia;
    }

    /**
     * Consulta si dins de la llista es troben mesures dins d'unes dates
     * @param d1
     * @param d2
     * @return llista amb mesures dins del interval
     */
    public LlistaNivellsAigua consultaEmbEnTemps(Data d1, Data d2){
        LlistaNivellsAigua nova = new LlistaNivellsAigua(numNivells);
        int i;
        for(i = 0; i < numNivells; i++)
        {
            if(llista[i].esTrobaEnAquestPeriode(d1, d2))
            {
                nova.afegirDades(llista[i]);           
            }
        }
        return(nova);
    }

    /**
     * Elimina las mesures que es trobin a una certa provincia
     * @param nom
     */
    public void elimina(String nom){
        int i, j;
        j = 0;
        for(i = 0; i < numNivells; i++)
        {
            if(!llista[i].esTrobaEnAquestaProvincia(nom))
            {
                llista[j] = llista[i];
                j++;
            }
        }
        numNivells = j;
    }

    /**
     * Mostra per pantalla els nivells d'aigua
     * 
     * @return mesura de cada posicio de la llista
     */
    public String toStringl() {
        String aux = "Llista de "+numNivells+" nivells";
        for (int i = 0; i < numNivells; i++) {
            aux = aux + "\n\t"+llista[i];
        }
        return aux;
    }

    /**
     * Funcio que troba la mesura d'una certa posició
     * @param i
     * @return mesura que es troba a la posicio i de la llista
     */
    public NivellAigua getNivellAigua (int i) {
        if (i>= 0 && i< numNivells) {
            return llista[i];
        }
        else
        {
            return null;
        }
    }
}