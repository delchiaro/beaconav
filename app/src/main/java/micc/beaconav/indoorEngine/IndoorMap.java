package micc.beaconav.indoorEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;

import java.util.HashMap;

import micc.beaconav.db.dbHelper.DbManager;
import micc.beaconav.db.dbHelper.artwork.ArtworkRow;
import micc.beaconav.db.dbJSONManager.JSONHandler;
import micc.beaconav.indoorEngine.building.Building;
import micc.beaconav.indoorEngine.building.Floor;
import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.indoorEngine.building.spot.Spot;
import micc.beaconav.indoorEngine.building.spot.custom.ArtSpot;
import micc.beaconav.localization.beaconHelper.GoodBadBeaconProximityManager;

/**
 * Created by Riccardo Del Chiaro & Franco Yang (25/02/2015)
 */
public class IndoorMap
{
    private static int PPM = 300; // Pixel Per Meter

    private Building building;
    //private LocalizationSpotManager _localizationSpot;




    HashMap<Integer, Spot> spot_beacon_map = new HashMap<>();
    HashMap<Integer, Spot> spot_QR_map = new HashMap<>();







    public IndoorMap( Building building )
    {
        this.building = building;


//
//        // BUILDING DEFINITION
//        building = new Building(50*PPM, 50*PPM);
//
//        // FLOOR DEFINITION
//        Floor floor = new Floor(0);
//        building.add(floor);
//
//
//        // ROOM DEFINITION
//        Room corridoio = new Room();
//        floor.add(corridoio);
//
//        Room ingresso1 = new Room();
//        floor.add(ingresso1);
//
//        Room stanzaFerracani = new Room();
//        floor.add(stanzaFerracani);
//
//
//
//        // SPOT DEFINITIONS
//        final ArtSpot spot1;
//        final ArtSpot spot2;
//        final ArtSpot spot3;
//
//        spot1 = new ArtSpot( 2f, 2f);
//        spot2 = new ArtSpot( 8f, 28);
//        spot3 = new ArtSpot( 11f, 27f);
//        corridoio.add(spot1);
//        stanzaFerracani.add(spot2);
//        stanzaFerracani.add(spot3);
//
//
//
//
//        DbManager.getLastArtworkDownloader().addHandler(new JSONHandler<ArtworkRow>() {
//            @Override
//            public void onJSONDownloadFinished(ArtworkRow[] result) {
//                if (result.length > 3) {
//                    spot1.setArtworkRow(result[2]);
//                    spot2.setArtworkRow(result[3]);
//                    spot3.setArtworkRow(result[4]);
//                    beacon_spot_map.put(GoodBadBeaconProximityManager.getID(31950, 39427), spot1);
//
//                }
//            }
//        });
//
//
//
//
//
//
//        // VertexDefinitions and Spot Definitions
//        corridoio.pushWall(new PointF(1f,   1f));
//        corridoio.pushWall(new PointF(4.5f, 1f));
//        corridoio.pushWall(new PointF(4.5f, 30f));
//        corridoio.pushWall(new PointF(1f,   30f));
//
//
//
//
//        ingresso1.pushWall(new PointF(4.5f, 25f));
//        ingresso1.pushWall(new PointF(7.5f, 25f));
//        ingresso1.pushAperture(new PointF(7.5f, 26f));
//        Room.addDoorSpot(ingresso1, 7f, 27, true, stanzaFerracani, 8f, 27, true );
//        ingresso1.pushWall(new PointF(7.5f, 28f));
//
//        ingresso1.pushWall(new PointF(7.5f, 30f));
//        ingresso1.pushWall(new PointF(4.5f, 30f));
//        ingresso1.pushAperture(new PointF(4.5f, 28f));
//        Room.addDoorSpot(ingresso1, 4f, 27, true, corridoio, 5f, 27, true );
//        ingresso1.pushWall(new PointF(4.5f, 26f));
//
//
//        stanzaFerracani.pushWall(new PointF(7.5f, 25f));
//        stanzaFerracani.pushWall(new PointF(13f,  25f));
//        stanzaFerracani.pushWall(new PointF(13f,  30f));
//        stanzaFerracani.pushWall(new PointF(7.5f, 30f));
//
//
//
//        // DRAWABLES DEFINITION
//
//
////        BeaconHelper beaconHelper = new BeaconHelper(this.getActivity());
////        beaconHelper.addProximityListener(this);
////        beaconHelper.scanBeacons();
//
//
//
//        // DRAWING:
//        Bitmap bmp = generateBackgroundBmp(building);
//        //backgroundImgView.setImageDrawable(indoorMap); // disegno background in vettoriale
//        // disegno background stampando il vettoriale su un bitmap
//        backgroundImgView.setImageBitmap(bmp );
//
//
//        //indoorMap = new IndoorMap(building);
//        markerManager = building.getActiveMarkerManager();
//        markerManager.setOnMarkerSpotSelectedListener(this);
//
//        foregroundImgView.setImageDrawable(markerManager.newWrapperDrawable());
//
//        //foregroundImgView.setOnTouchListener(markerManager);
//
//        pathSpotManager = building.drawBestPath(corridoio.getRoomSpot(), stanzaFerracani.getRoomSpot());
//
//        navigationImgView.setImageDrawable(pathSpotManager.newWrapperDrawable());
//
//
//        translateAll(200, 200);
//        navigationImgView.invalidate();
    }












}
