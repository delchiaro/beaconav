package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Iterator;

import micc.beaconav.indoorEngine.ProportionsHelper;
import micc.beaconav.indoorEngine.building.spot.DrawableSpotManager;
import micc.beaconav.indoorEngine.building.spot.MarkerSpot;
import micc.beaconav.indoorEngine.building.spot.PathSpot;
import micc.beaconav.indoorEngine.building.spot.RoomSpot;
import micc.beaconav.indoorEngine.building.spot.SpotManager;
import micc.beaconav.util.containerContained.ContainerContained;

/**
 * 
 */
public class Room  extends ContainerContained<Floor, SpotManager>
        //extends Drawable
{
    private static final int PPM = ProportionsHelper.PPM; // Pixel Per Miter
    private static final int WALL_WIDTH_CM = 15;
    private Paint wallsPaint;
    private Paint aperturePaint;
    private Paint doorPaint;
    private Paint floorPaint;


    private DrawableSpotManager<PathSpot> _pathSpotManager = new DrawableSpotManager<>(this);
    private ArrayList<Vertex> _vertices = new ArrayList<Vertex>();

    private boolean autogenRoomSpot = false;
    private RoomSpot _roomSpot;


    public Room(final RoomSpot roomSpot) {
        this.wallsPaint = MapPaint.wall_default_25.getPaint();
        this.doorPaint = MapPaint.door_default_25.getPaint();
        this.floorPaint = MapPaint.floor_default.getPaint();
        this.aperturePaint = new Paint(wallsPaint);
        aperturePaint.setColor(floorPaint.getColor());

        this._roomSpot = roomSpot;
    }

	public Room(float roomSpot_x, float roomSpot_y) {
        this(new RoomSpot(roomSpot_x, roomSpot_y) );
    }

    public Room() {
        this(null);
        autogenRoomSpot = true;
    }


    public RoomSpot getRoomSpot() {
        if(_roomSpot != null) return _roomSpot;
        else if(autogenRoomSpot == true )
        {
            return this._roomSpot = generateRoomSpot();
        }
        else return null; // generate exeption
    }
    private RoomSpot generateRoomSpot() {
        if(this._vertices.size() == 0) return null;
        else
        {
            float x = 0f;
            float y = 0f;
            Vertex v;
            int pointCount = _vertices.size();
            for (int i = 0; i < pointCount; i++)
            {
                v = _vertices.get(i);
                x += v.getX();
                y += v.getY();
            }

            x = x/pointCount;
            y = y/pointCount;

            return new RoomSpot(x, y);

        }

    }


    public void addMarkerSpot( MarkerSpot marker) {
        _pathSpotManager.add(marker);
    }
    public void addPathSpot( PathSpot pathSpot) {
        _pathSpotManager.add(pathSpot);
    }




    private void addVertex(Vertex vertex){
        this._vertices.add(vertex);
        if(this.autogenRoomSpot == true ) this._roomSpot = null;
    }
    public void pushVertex(Vertex newVertex){
        addVertex(newVertex);
    }
    public void pushAperture(PointF newVertexCoord) {
        addVertex(new Vertex(newVertexCoord, Vertex.Type.APERTURE));
    }
    public void pushDoor(PointF newVertexCoord) {
        addVertex(new Vertex(newVertexCoord, Vertex.Type.DOOR));
    }
    public void pushWall(PointF newVertexCoord) {
        addVertex(new Vertex(newVertexCoord, Vertex.Type.WALL));
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


    protected void drawWalls(Canvas canvas, PointF padding) {

        //final DrawFilter filter = new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, 0);
        //canvas.setDrawFilter(filter);

        Path wallpath = new Path();




        // Disegno PAVIMENTO e MURI:
        Vertex vertex;
        int nVertices = _vertices.size();

        if(_vertices.size() > 2)
        {
            Vertex firstVertex  = _vertices.get(0);
            wallpath.moveTo(firstVertex.getX()* PPM, firstVertex.getY()* PPM); // used for first point

            Vertex secondVertex = _vertices.get(1);
            wallpath.lineTo(secondVertex.getX() * PPM, secondVertex.getY() * PPM);

            for(int i = 2; i < nVertices; i ++ )
            {
                vertex = _vertices.get(i);
                wallpath.lineTo(vertex.getX() * PPM, vertex.getY() * PPM);
            }

            wallpath.lineTo(firstVertex.getX() * PPM, firstVertex.getY() * PPM);
            wallpath.lineTo(secondVertex.getX() * PPM, secondVertex.getY() * PPM);
            canvas.drawPath(wallpath, floorPaint);
            canvas.drawPath(wallpath, wallsPaint);
        }


    }
    protected void drawDoorsAndAperture(Canvas canvas, PointF padding) {

        Vertex vertex;
        int nVertices = _vertices.size();

        if(_vertices.size() > 2)
        {
            Vertex oldVertex = null;
            vertex = _vertices.get(0);

            for (int i = 1; i < nVertices; i++)
            {
                oldVertex = vertex;
                vertex = _vertices.get(i);
                if (vertex.getType() == Vertex.Type.APERTURE)
                {
                    canvas.drawLine(oldVertex.getX() * PPM, oldVertex.getY() * PPM,
                            vertex.getX() * PPM, vertex.getY() * PPM, this.wallsPaint);
                }
                switch (oldVertex.getType())
                {
                    case DOOR:
                        // PRIMA RIMUOVO IL MURO E POI INSERISCO LA PORTA..
                        // se non è necessario eliminare il disegno della apertura
                        canvas.drawLine(oldVertex.getX() * PPM, oldVertex.getY() * PPM,
                                vertex.getX() * PPM, vertex.getY() * PPM, this.aperturePaint);
                        canvas.drawLine(oldVertex.getX() * PPM, oldVertex.getY() * PPM,
                                vertex.getX() * PPM, vertex.getY() * PPM, this.doorPaint);
                        break;

                    case APERTURE:
                        canvas.drawLine(oldVertex.getX() * PPM, oldVertex.getY() * PPM,
                                vertex.getX() * PPM, vertex.getY() * PPM, this.aperturePaint);
                        break;

                }
            }
        }

    }


}