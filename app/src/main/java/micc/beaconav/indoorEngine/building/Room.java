package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;

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
    private static final int DPI = 300;
    private Paint wallsPaint;

    private ArrayList<Vertex> _vertices = new ArrayList<Vertex>();
    private ArrayList<ConvexArea> _convexAreas = new ArrayList<>();
    private Floor _containerFloor;



	public Room() {
        super(0);
        this._containerFloor = null;
        this.wallsPaint = new Paint();

        wallsPaint.setColor(Color.RED);
        wallsPaint.setStrokeWidth(DPI/100);
        wallsPaint.setStyle(Paint.Style.STROKE);

    }



    @Override
    protected void _coreDraw(Canvas canvas) {

        final DrawFilter filter = new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, 0);
        canvas.setDrawFilter(filter);

        Path wallpath = new Path();
        wallpath.reset(); // only needed when reusing this path for a new build
        Iterator<Vertex> vertexIter = _vertices.iterator();




        Vertex vertex;
        if(vertexIter.hasNext())
        {

            Vertex firstVertex  = vertexIter.next();
            wallpath.moveTo(firstVertex.getX(), firstVertex.getY()); // used for first point

            while (vertexIter.hasNext())
            {
                vertex = vertexIter.next();
                wallpath.lineTo(vertex.getX()* DPI, vertex.getY() * DPI);
            }
            wallpath.lineTo(firstVertex.getX()* DPI, firstVertex.getY() * DPI);

            canvas.drawPath(wallpath, wallsPaint);

            /* Vecchio modo di disegnare, peggio perchè non collega in modo decente gli angoli delle linee
                ma ha di pro che ogni linea può avere un suo spessore in teoria, e un suo stile.

                canvas.drawLine(oldVertex.getX() * DPI, oldVertex.getY() * DPI,
                vertex.getX() * DPI, vertex.getY() * DPI, wallsPaint);
            */
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



    //gestione associazione bidirezionale Floor - Room

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