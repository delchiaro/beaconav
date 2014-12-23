package micc.beaconav.indoor.building;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Iterator;
import java.util.List;

import micc.beaconav.indoor.Drawable;



public class Floor extends Drawable
{

    List<Room> roomList;


    Bitmap floorBmp;

	public Floor(Point position)
    {
        super(position);
    }
    public Floor(int x, int y)
    {
        super(x,y);
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(floorBmp, position.x, position.y, null );
    }


    public void drawVectorial(Canvas canvas)
    {
        Iterator<Room> roomIterator = roomList.iterator();
        while(roomIterator.hasNext())
        {
            Room room = roomIterator.next();
            room.draw(canvas);
        }

    }



	public int getRooms() {
		// TODO implement here
		return 0;
	}


	public Room getRoom() {
		// TODO implement here
		return null;
	}

}