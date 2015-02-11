package micc.beaconav.indoorEngine.building.spot;


import micc.beaconav.indoorEngine.ProportionsHelper;
import micc.beaconav.indoorEngine.building.Building;
import micc.beaconav.indoorEngine.building.ConvexArea;
import micc.beaconav.indoorEngine.building.Floor;
import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.util.containerContained.Contained;

/**
 * 
 */
public class Spot extends Contained<SpotManager>
{

    private static float PPM = ProportionsHelper.PPM;
    private float _last_final_scale_factor = 1;
    private float _realtime_scale_factor   = 1;

    private float _absolute_x; //in meters
    private float _absolute_y;


    private float _translation_x = 0;
    private float _translation_y = 0;

    private float _scaled_translation_indipendent_x ;
    private float _scaled_translation_indipendent_y ;


    public final float x_for_drawing() {
        return _scaled_translation_indipendent_x + _translation_x;
    }
    public final float y_for_drawing() {
        return _scaled_translation_indipendent_y + _translation_y;
    }




    private final void _setScaledCoords(){
        _scaled_translation_indipendent_x = x_pixel() * _last_final_scale_factor * _realtime_scale_factor;
        _scaled_translation_indipendent_y = y_pixel() * _last_final_scale_factor * _realtime_scale_factor;
    }

    /**
     * Questo metodo deve essere richiamato mentre si sta eseguendo il pinch to zoom.
     * Servirá per settare un fattore di scala momentaneo in tempo reale senza sovrascrivere
     * il vecchio valore di scala settato per l'immagine al termine del precedente pinch to zoom
     * (si andranno a moltiplicare in tempo reale)
     * @param scale fattore di scala istantaneo al momento del pinch to zoom
     */
    public void setOnTouchRealTimeScaleFactor(float scale) {
        _realtime_scale_factor = scale;
        _setScaledCoords();
    }

    /**
     * Questo metodo deve essere richiamato appena termina l'esecuzione del pinch to zoom
     * (ovvero quando le dita sono staccate dallo schermo, evento ACTION_UP).
     * Ingloberá il fattore di scala provvisorio nel fattore di scala totale dell'immagine
     * (che adesso è ferma e non necessita di un costante aggiornamento del fattore di scala)
     * e resetterá ad 1 (nullo) il fattore di scala in tempo reale.
     */
    public void setFinalTouchScaleFactor(){
        this._last_final_scale_factor *= _realtime_scale_factor;
        this._realtime_scale_factor = 1;
        _setScaledCoords();
    }




    //protected IndoorPosition _indoorPosition;
    private ConvexArea _containerConvexArea = null;

    public Spot(){
        this(0,0);
    }
    public Spot(float x, float y){
        this._absolute_x = x;
        this._absolute_y = y;
        _scaled_translation_indipendent_x = x_pixel();
        _scaled_translation_indipendent_y = y_pixel();


    }


    public float x(){ return _absolute_x; }
    public float y(){ return _absolute_y; }

    public void x(float x){
        this._absolute_x = x;
    }
    public void y(float y){
        this._absolute_y = y;
    }

    public float x_pixel(){ return _absolute_x * PPM; }
    public float y_pixel(){ return _absolute_y * PPM; }

    public void setTranslation(float x, float y) {
        this._translation_x = x;
        this._translation_y = y;
    }



    public SpotManager<Spot> getContainerManager()    { return super.getContainer(); }
    public ConvexArea        getConvexAreaContainer() { return getContainerManager().getContainer(); }
    public Room              getContainerRoom()       { return getConvexAreaContainer().getContainerRoom(); }
    public Floor             getContainerFloor()      { return getContainerRoom().getContainerFloor(); }
    public Building          getBuildingContainer()   { return getContainerFloor().getContainerBuilding(); }




}