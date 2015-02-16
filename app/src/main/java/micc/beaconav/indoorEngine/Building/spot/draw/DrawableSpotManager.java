package micc.beaconav.indoorEngine.building.spot.draw;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

import java.util.Iterator;
import java.util.List;

import micc.beaconav.indoorEngine.building.spot.SpotManager;


/**
 * Created by nagash on 11/02/15.
 */
public class DrawableSpotManager<DS extends DrawableSpot> extends SpotManager<DS>
{


    private Drawable _wrapperDrawable = null;


    public DrawableSpotManager() {
        super();
    }


    public DrawableSpotManager( List<DS> initList) {
        super( initList);
    }

    /**
     *  Da richiamare quando si termina il pinch to zoom. Setta la translazione dovuta allo zoom
     *  (scaling) di ogni spot prendendo come scaling factor l'ultimo scaling factor in real time
     *  settato.
     */
    public final void holdScalingFactor() {
        Iterator<DS> spotIter =  super.iterator();
        while(spotIter.hasNext())
            spotIter.next().setFinalScaleTranlationFactor();

        this.invalidate();
    }

    /**
     *  Da richiamare quando si esegue un rescale in tempo reale, come per esempio un pinch to zoom.
     *  modificherá la posizione di ogni spot in modo che le proprie coordinate seguano il rescale della mappa.
     *  La dimensione dei DrawableSpot non verrá influenzata dal rescale, solo la posizione verrá influenzata.
     *  In  memoria ogni drawable ha una posizione relativa allo scale factor ed una posizione assoluta (in metri)
     *  che non è influenzata in alcun modo dallo scale factor.
     */
    public final void translateByRealtimeScaling(float realtimeScaleFactor) {
        Iterator<DS> spotIter =  super.iterator();
        while(spotIter.hasNext())
            spotIter.next().setRealtimeScaleTranslationFactor(realtimeScaleFactor);

        this.invalidate();
    }

    /**
     * Trasla la posizione modificando le coordiante relative all'immagine, le coordinate assolute
     * (che potrebbero essere espresse in metri o altro) non sono toccate.
     * Si può pensare che trasli la posizione di ogni spot sul telefono, ma non trasla la posizione
     * degi spot dal punto di vista di coordinate reali degli oggetti che gli spot rappresentano
     * (come markers su una mappa).
     * Per modificare la posizione reale si usino sui singoli spot i setter x(float newX) e y(float newY)
     * e per apprezzare tali modifiche sul disegno si dovrá provvedere a richiamare invalidateSelf() di ogni
     * spot a cui abbiamo modificato tali valori.
     *
     * @param translation_x
     * @param translation_y
     */
    public final void translate(float translation_x, float translation_y) {
        Iterator<DS> spotIter =  super.iterator();
        while(spotIter.hasNext())
            spotIter.next().setTranslation(translation_x, translation_y);

        this.invalidate();
    }

    public void invalidate() {
        if(this._wrapperDrawable != null )
            this._wrapperDrawable.invalidateSelf();
    }


    protected Drawable generateWrapperDrawable() {
        return new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                Iterator<DS> spotIter = iterator();
                while(spotIter.hasNext())
                {
                    DS spot = spotIter.next();
                    spot.drawable().draw(canvas);
                }
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter cf) {

            }

            @Override
            public int getOpacity() {
                return 0;
            }
        };
    }

    public final Drawable newWrapperDrawable(){
        return _wrapperDrawable = generateWrapperDrawable();
    }

    public final Drawable storedWrapperDrawable() {
        return this._wrapperDrawable;
    }




}
