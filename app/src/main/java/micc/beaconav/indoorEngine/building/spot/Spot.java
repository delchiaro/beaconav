package micc.beaconav.indoorEngine.building.spot;


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

    private float _last_final_scale_factor = 1;
    private float _realtime_scale_factor   = 1;

    private float _absolute_x = 0;
    private float _absolute_y = 0;


    private float _translation_x = 0;
    private float _translation_y = 0;

    private float _scaled_translation_indipendent_x = _absolute_x;
    private float _scaled_translation_indipendent_y = _absolute_y;


    private final void _setScaledCoords(){
        _scaled_translation_indipendent_x = _absolute_x * _last_final_scale_factor * _realtime_scale_factor;
        _scaled_translation_indipendent_y = _absolute_x * _last_final_scale_factor * _realtime_scale_factor;
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

    public Spot(){}
    public Spot(float x, float y){
        this._absolute_x = x;
        this._absolute_y = y;
    }


    public float x(){ return _absolute_x; }
    public float y(){ return _absolute_y; }

    public void x(float x){
        this._absolute_x = x;
    }
    public void y(float y){
        this._absolute_y = y;
    }


    public void translate(float x, float y) {
        this._translation_x += x;
        this._translation_x += y;
    }



    public SpotManager<Spot> getContainerManager()    { return super.getContainer(); }
    public ConvexArea        getConvexAreaContainer() { return getContainerManager().getContainer(); }
    public Room              getContainerRoom()       { return getConvexAreaContainer().getContainerRoom(); }
    public Floor             getContainerFloor()      { return getContainerRoom().getContainerFloor(); }
    public Building          getBuildingContainer()   { return getContainerFloor().getContainerBuilding(); }




}