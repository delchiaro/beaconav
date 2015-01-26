package micc.beaconav.indoorEngine.drawable;

import android.graphics.Canvas;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;


/**
* Created by Nagash on 26/12/2014.
*/

class DrawableComparator implements Comparator<Drawable> {
    @Override
    public int compare(Drawable lhs, Drawable rhs)
    {
        if( (lhs).getZIndex() > rhs.getZIndex() )
        {
            return 1;
        }
        else if( (lhs).getZIndex() < rhs.getZIndex() )
        {
            return -1;
        }
        else return 0;
    }

};



/**
 * Created by Nagash on 26/12/2014.
 */
public class DrawableManager
{
    private DrawableComparator      _comparator;
    private PriorityQueue<Drawable> _drawableQueue;

    public DrawableManager()
    {
        this._comparator  = new DrawableComparator();
        this._drawableQueue = new  PriorityQueue<Drawable>(10, this._comparator);
    }

    public final void add(Drawable newDrawable)
    {
        newDrawable.removeManager();
        _drawableQueue.add(newDrawable);
        newDrawable.setManager(this);
    }
    public final void remove(Drawable removedDrawable)
    {
        if(removedDrawable.getManager() != null && removedDrawable.getManager() == this)
        {
            this._drawableQueue.remove(removedDrawable);
            removedDrawable.unsetManager();
        }
    }
    public void refresh(Drawable modifiedDrawable)
    {
        this._drawableQueue.remove(modifiedDrawable);
        this._drawableQueue.add(modifiedDrawable);
    }

    public long getHigherZIndex()
    {
        return this._drawableQueue.peek().getZIndex();
    }




    public void drawAll(Canvas canvas)
    {
        Iterator<Drawable> iterator = this._drawableQueue.iterator();
        while(iterator.hasNext())
        {
            iterator.next().draw(canvas);
        }
    }




}




