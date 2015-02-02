package micc.beaconav.db.dbJSONManager;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.TableSchema;
/**
 * Created by nagash on 21/01/15.
 */

public class JSONDownloader<TR extends TableRow> extends AsyncTask<String, String, List<TR>>
{

    private TableSchema schema;
    private List<TR> downloadedRows;
    private List<JSONHandler> handlerList;
    private final String URL;

    private final int DOWNLOAD_NOT_STARTED = -1;
    private final int DOWNLOAD_STARTED = 0;

    long downloadIstant = DOWNLOAD_NOT_STARTED;



    public JSONDownloader(TableSchema schema, String url) {
        this.schema = schema;
        this.downloadedRows = null;
        this.handlerList = new ArrayList<>();
        this.URL = url;
    }

    public void addHandler(JSONHandler newHandler){
        this.handlerList.add(newHandler);
        if(isDownloadFinished())
            newHandler.onJSONDownloadFinished(this.getDownloadedRows());
    }


    public boolean isDownloadFinished(){
        if(downloadIstant > DOWNLOAD_STARTED ) return true;
        else return false;
    }
    public boolean isDownloadStarted(){
        if(downloadIstant > DOWNLOAD_NOT_STARTED) return true;
        else return false;
    }
    public long getDownloadIstant(){
        return downloadIstant;
    }



    public List<TR> getDownloadedRows()
    {
        if(downloadedRows != null)
            return downloadedRows;
        else return null;
    }





    public boolean startDownload() {
        if(isDownloadStarted()==false) {
            this.execute(URL, schema.getName());
            return true;
        }
        else return false;
    }

    @Override
    protected final void onPreExecute() {
        super.onPreExecute();
        this.downloadIstant = DOWNLOAD_STARTED;
    }


    protected final List<TR> doInBackground(String... args) {
        // TODO: Gestire eccezione nel caso in cui non si trovi l'oggetto json, e nel caso incuinon ci si possa connettere

        String url = args[0];
        String schemaName = args[1];

        ArrayList<TR> tableRows = new ArrayList<TR>();
        JSONArray jsonArray = null;

        JSONParser jParser = new JSONParser();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        JSONObject json = jParser.makeHttpRequest(url, "GET", params);


        try {
            jsonArray = json.getJSONArray(schemaName);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (jsonArray != null)
        {
            int arrayLenght = jsonArray.length();
            for (int i = 0; i < arrayLenght; i++)
            {
                JSONObject jsonObject = null;

                try
                {
                    jsonObject = jsonArray.getJSONObject(i);
                    TableRow row = this.schema.newRow();

                    int nCols = this.schema.size();
                    for (int j = 0; j < nCols; j++)
                    {
                        ColumnField field = row.field(j);
                        String colName = field.columnName();
                        String jsonValue = jsonObject.getString(colName);
                        row.field(j).set(jsonValue);
                    }

                    //tod: funziona??
                    tableRows.add((TR)row);

                }
                catch (JSONException e) {  e.printStackTrace(); }
            }
        }

        return tableRows;
    }


    protected final void onPostExecute(List<TR> result) {
        this.downloadedRows = result;
        downloadIstant = System.nanoTime();

        Iterator<JSONHandler> iter = this.handlerList.iterator();
        while(iter.hasNext())
            iter.next().onJSONDownloadFinished(result);
            // richiama i gestori di tutti gli handler
    }

}
