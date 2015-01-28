package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;

import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.indoorEngine.spot.Spot;
import micc.beaconav.indoorEngine.spot.Vertex;

/**
 * 
 */
public class Room extends Drawable
{
    private Paint wallsPaint;

    private ArrayList<Vertex> _vertices = new ArrayList<Vertex>();
    private ArrayList<ConvexArea> _convexAreas = new ArrayList<>();
    private Floor _containerFloor;



	public Room() {
        super(0);
        this._containerFloor = null;
        this.wallsPaint = new Paint();
        wallsPaint.setColor(Color.BLACK);
        wallsPaint.setStrokeWidth(3);
	}



    @Override
    protected void _coreDraw(Canvas canvas) {
        Iterator<Vertex> vertexIter = _vertices.iterator();

        Vertex oldVertex = null;
        if(vertexIter.hasNext())
            oldVertex = vertexIter.next();

        while( vertexIter.hasNext())
        {
            Vertex vertex = vertexIter.next();
            canvas.drawLine(oldVertex.getX(), oldVertex.getY(), vertex.getX(), vertex.getY(), wallsPaint);
        }
    }


    public void pushVertex(Vertex newVertex){
        this._vertices.add(newVertex);
    }
    public int indexOfVertex(Vertex vertex){
        return this._vertices.indexOf(vertex);
    }
    public void addCorner(Vertex vertex, int index){
        this._vertices.add(index, vertex);
    }

    public Spot getCorner(int index){
        return this._vertices.get(index);
    }



    //gestione associazione bidirezionale Building - Floor

    public Floor getContainerFloor(){
        return this._containerFloor;
    }
    Room setContainerFloor(Floor floor) //package-private visibility
    {
        this._containerFloor = floor;
        return this;
    }
    void unsetContainerFloor() //package-private visibility
    {
        this._containerFloor = null;
    }
    public final void removeFromContainerFloor()
    {
        if(this._containerFloor!=null)
            this._containerFloor.removeRoom(this);
    }


}