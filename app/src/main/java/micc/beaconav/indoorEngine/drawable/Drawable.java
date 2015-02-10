package micc.beaconav.indoorEngine.drawable;

import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * Created by Nagash on 26/12/2014.
 */
public abstract class Drawable
{
    private DrawableManager _manager;
    private long _zIndex;
    public boolean visible = true;



    public Drawable(DrawableManager manager, long zIndex){
        this._zIndex = zIndex;
        this._manager = manager;
        manager.add(this); //Attach to manager (observer) TODO: manager.add will call this.setManager.. will works? TEST!!
    }
    public Drawable(long zIndex){
        this._zIndex = zIndex;
        this._manager = null;
    }
    public Drawable(DrawableManager manager){
        this(manager, manager.getHigherZIndex() + 1);
    }




    final void setManager(DrawableManager manager) // visibilitá package-private (sarebbe meglio Friend di DrawableManager ma java non lo supporta.
    {
       this._manager = manager;
    }
    final void unsetManager()
    {
        this.setManager(null);
    }
    public final void removeManager() {
        if(this._manager!=null)
            this._manager.remove(this);
    }
    public DrawableManager getManager() {
        return this._manager;
    }



    public final long getZIndex() {
        return _zIndex;
    }
    public final void setZIndex(long newZIndex){
        this._zIndex = newZIndex;
        this._notifyZIndexChange();
    }
     private final void _notifyZIndexChange(){
        _manager.refresh(this);
    }


    //template method design pattern
    public final void draw(Canvas canvas, PointF position)
    {
        if(visible)
        {
            this._coreDraw(canvas, position);
        }
    }
    protected abstract void _coreDraw(Canvas canvas, PointF position);



}
