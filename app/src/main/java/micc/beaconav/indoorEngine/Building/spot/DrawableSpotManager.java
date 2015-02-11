package micc.beaconav.indoorEngine.building.spot;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

import java.util.Iterator;

import micc.beaconav.indoorEngine.building.ConvexArea;

/**
 * Created by nagash on 11/02/15.
 */
public class DrawableSpotManager extends SpotManager<DrawableSpot>
{


    private Drawable _wrapperDrawable = null;


    public DrawableSpotManager(ConvexArea containerConvexArea) {
        super(containerConvexArea);
    }



    /**
     *  Da richiamare quando si termina il pinch to zoom. Setta la translazione dovuta allo zoom
     *  (scaling) di ogni spot prendendo come scaling factor l'ultimo scaling factor in real time
     *  settato.
     */
    public final void holdScalingFactor() {
        Iterator<DrawableSpot> spotIter =  super.getIterator();
        while(spotIter.hasNext())
            spotIter.next().setFinalTouchScaleFactor();

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
        Iterator<DrawableSpot> spotIter =  super.getIterator();
        while(spotIter.hasNext())
            spotIter.next().setOnTouchRealTimeScaleFactor(realtimeScaleFactor);

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
        Iterator<DrawableSpot> spotIter =  super.getIterator();
        while(spotIter.hasNext())
            spotIter.next().setTranslation(translation_x, translation_y);

        this.invalidate();
    }

    public void invalidate() {
        if(this._wrapperDrawable != null )
            this._wrapperDrawable.invalidateSelf();
    }



    public Drawable newWrapperDrawable(){
        return _wrapperDrawable = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                Iterator<DrawableSpot> spotIter = getIterator();
                while(spotIter.hasNext())
                {
                    Spot spot = spotIter.next();
                    DrawableSpot dSpot = (DrawableSpot) spot;
                    dSpot.drawable().draw(canvas);
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

    public Drawable storedWrapperDrawable() {
        return this._wrapperDrawable;
    }




}
