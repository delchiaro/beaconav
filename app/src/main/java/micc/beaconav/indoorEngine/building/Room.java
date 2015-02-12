package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Iterator;

import micc.beaconav.indoorEngine.ProportionsHelper;
import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.util.containerContained.ContainerContained;

/**
 * 
 */
public class Room  extends ContainerContained<Floor, ConvexArea>
        //extends Drawable
{
    private static final int PPM = ProportionsHelper.PPM; // Pixel Per Miter
    private static final int WALL_WIDTH_CM = 15;
    private Paint wallsPaint;
    private Paint floorPaint;

    private ArrayList<Vertex> _vertices = new ArrayList<Vertex>();
    private ArrayList<ConvexArea> _convexAreas = new ArrayList<>();



	public Room() {
        this.wallsPaint = new Paint();
        wallsPaint.setColor(Color.rgb(94, 97, 76));
        wallsPaint.setStrokeWidth((PPM*WALL_WIDTH_CM)/100);
        wallsPaint.setStyle(Paint.Style.STROKE);
        wallsPaint.setAntiAlias(true);

        this.floorPaint = new Paint();
        floorPaint.setColor(Color.rgb(255, 248, 176));
        floorPaint.setStyle(Paint.Style.FILL);
        floorPaint.setAntiAlias(true);

    }



    protected void draw(Canvas canvas, PointF padding) {

        final DrawFilter filter = new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, 0);
        canvas.setDrawFilter(filter);

        Path wallpath = new Path();
        wallpath.reset(); // only needed when reusing this path for a new build
        Iterator<Vertex> vertexIter = _vertices.iterator();




        Vertex vertex;
        if(vertexIter.hasNext())
        {

            Vertex firstVertex  = vertexIter.next();
            wallpath.moveTo(firstVertex.getX()* PPM, firstVertex.getY()* PPM); // used for first point

            if(vertexIter.hasNext())
            {
                Vertex secondVertex = vertexIter.next();
                wallpath.lineTo(secondVertex.getX() * PPM, secondVertex.getY() * PPM);

                while (vertexIter.hasNext()) {
                    vertex = vertexIter.next();
                    wallpath.lineTo(vertex.getX() * PPM, vertex.getY() * PPM);
                }
                wallpath.lineTo(firstVertex.getX() * PPM, firstVertex.getY() * PPM);
                wallpath.lineTo(secondVertex.getX() * PPM, secondVertex.getY() * PPM);
                canvas.drawPath(wallpath, floorPaint);
                canvas.drawPath(wallpath, wallsPaint);



                /* Vecchio modo di disegnare: peggio perchè non collega in modo decente gli angoli delle linee
                    ma ha di pro che ogni linea può avere un suo spessore in teoria, e un suo stile.

                    canvas.drawLine(oldVertex.getX() * DPI, oldVertex.getY() * DPI,
                    vertex.getX() * DPI, vertex.getY() * DPI, wallsPaint);
                */
            }
        }



        Iterator<ConvexArea> areaIter = _convexAreas.iterator();
        while(areaIter.hasNext()) {
            areaIter.next().draw(canvas, new PointF(0, 0)); //delego disegno delle porte ad ogni convex area
        }

    }






    public void pushVertex(Vertex newVertex){
        this._vertices.add(newVertex);
    }
    public int indexOfVertex(Vertex vertex){
        return this._vertices.indexOf(vertex);
    }
    public void addVertex(Vertex vertex, int index){
        this._vertices.add(index, vertex);
    }
    public Vertex getVertex(int index){
        return this._vertices.get(index);
    }




    final public Building getCointainerBuilding() {
        return super.getContainer().getContainerBuilding();
    }

    final public Floor getContainerFloor() {
        return super.getContainer();
    }

}