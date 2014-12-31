package micc.beaconav.indoor.localization;

/**
 * Created by Nagash on 26/12/2014.
 */
public class LocalizationManager
{
    private IndoorPosition gpsPosition;
    private long _gpsLastUpdate;

    private IndoorPosition beaconPosition;
    private long _beaconLastUpdate;

    private IndoorPosition qrCodePosition;
    private long _qrCodeLastUpdate;


    private int _localizationPrecision;


    private void updateGps()
    {
        _gpsLastUpdate = System.currentTimeMillis();
    }
    private void updateBeacons()
    {
        _beaconLastUpdate = System.currentTimeMillis();
    }
    private void updateQrCode()
    {
        _qrCodeLastUpdate = System.currentTimeMillis();
    }

    public void update()
    {
        this.updateGps();
        this.updateBeacons();
        this.updateQrCode();
    }


    public IndoorPosition getPosition()
    {
        //TODO: Algoritmo della scelta della posizione miscelando le informazioni su GPS, Beacons, qrCode, e i relativi istanti di ultimo aggiornamento
        _localizationPrecision = 101; // 101 = mock location, fake, for debug and testing.
        IndoorPosition newPos = new IndoorPosition(0,0,0);
        return newPos;
    }

    public int getLocalizationPrecision(){
        return _localizationPrecision;
    };

}
