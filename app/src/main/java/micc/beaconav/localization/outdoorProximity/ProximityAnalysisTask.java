package micc.beaconav.localization.outdoorProximity;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by nagash on 23/01/15.
 */
public class ProximityAnalysisTask extends AsyncTask<ProximityObject, String, ProximityObject>
{

    private static boolean isAnalyzing = false;
    private static boolean hadAnalyzed = false;

    private ProximityManager manager;
    private LatLng myPosition;
    private ProximityObject[] proxObjects;

    ProximityAnalysisTask(ProximityManager manager, LatLng myPosition, ProximityObject... proximityObjects)
    {
        this.manager = manager;
        this.proxObjects = proximityObjects;
        this.myPosition = myPosition;
    }

    public void startAnalysis()
    {
        this.execute(proxObjects);
    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.isAnalyzing = true;
    }

    @Override
    protected ProximityObject doInBackground(ProximityObject... proximityObjects)
    {


        /*  Calcola il proximityObject più vicino tra quelli interni al proximityRadius e lo restituisce al chiamante.
            ProximitySquare: semilato del quadrato che circonda la mia posizione entro il quale prendiamo in considerazione
                                il controllo della distanza (si effettua un controllo preliminare diretto su lat e long
                                senza scomodare la distanza euclidea).

            proximityRadius: raggio del cerchio entro il quale si considera essere in prossimitá di un oggetto.
                                solitamente il raggio dovrá essere compreso tra 2 / 3  metri fino a 30 circa.

            TODO: ordinare i proximityObjects in una matrice in cui le righe  sono ordinate per LAT, le colonne per LONG (o il contrario).
                    in questo modo possiamo, scelto il proximitySquare e data la posizione, estrarre la sottomatrice di interesse
                    escludendo tutto il resto, ed effettuare poi il controllo sul proximityRadius solo su queste senz afare altri
                    controlli.
                 La pesantezza dell'algoritmo è ridotta all'ordinamento iniziale di tutti gli oggetti del database (potrebbero essere
                 memorizzati giá ordinati in questo modo, si consiglia la struttura di albero binario duplicato in altezza e larghezza ).

                 Altrimenti baseterá nel database fare la query appropriata
                 (... WHERE latitude < X.XXXXXX and latitude > x.xxxx and longitude < Y.YYYY and longitude > y.yyyyy)

         */


        // FAKE PROXIMITY
        if(proximityObjects != null)
        {
            return proximityObjects[0];
        }
        else return null;
    }

    @Override
    protected void onPostExecute(ProximityObject proximityObject) {
        super.onPostExecute(proximityObject);
        this.isAnalyzing = false;
        this.hadAnalyzed = true;
        manager.onProximityAnalysisExecuted(proximityObject);

    }
}
