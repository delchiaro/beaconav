package micc.beaconav.db.dbJSONManager;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import micc.beaconav.db.dbJSONManager.schema.ColumnField;
import micc.beaconav.db.dbJSONManager.schema.TableRow;
import micc.beaconav.db.dbJSONManager.schema.TableSchema;

/**
 * Created by nagash on 21/01/15.
 */

public class JSONDownloader extends AsyncTask<String, String, TableRow[]>
{

    private TableSchema schema;
    private TableRow[] downloadedRows;
    private List<JSONHandler> handlerList;
    private final String URL;

    private final int DOWNLOAD_NOT_STARTED = -1;
    private final int DOWNLOAD_STARTED = 0;

    long downloadIstant = DOWNLOAD_NOT_STARTED;



    public JSONDownloader(TableSchema tableSchema, String url) {
        this.schema = tableSchema;
        this.downloadedRows = null;
        this.handlerList = new ArrayList<>();
        this.URL = url;
    }

    public void addHandler(JSONHandler newHandler){
        this.handlerList.add(newHandler);
        if(isDownloadFinished())
            newHandler.onJSONDownloadFinished(this.getDownloadedRows());
    }

    public boolean startDownload() {
        if(isDownloadStarted()==false) {
            this.execute(URL);
            return true;
        }
        else return false;
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



    public TableRow[] getDownloadedRows()
    {
        if(downloadedRows != null)
            return downloadedRows;
        else return null;
    }







    @Override
    protected final void onPreExecute() {
        super.onPreExecute();
        this.downloadIstant = DOWNLOAD_STARTED;
    }


    protected final TableRow[] doInBackground(String... args) {

        String url = args[0];
        ArrayList<TableRow> tableRows = new ArrayList<TableRow>();
        JSONArray jsonArray = null;

        JSONParser jParser = new JSONParser();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        JSONObject json = jParser.makeHttpRequest(url, "GET", params);


        try {
            jsonArray = json.getJSONArray("museums");
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
                        String colName = field.name();
                        String jsonValue = jsonObject.getString(colName);
                        row.field(j).set(jsonValue);
                    }

                    tableRows.add(row);

                }
                catch (JSONException e) {  e.printStackTrace(); }
            }
        }

        return tableRows.toArray(new TableRow[tableRows.size()]);
    }


    protected final void onPostExecute(TableRow[] result) {
        this.downloadedRows = result;
        downloadIstant = System.nanoTime();

        Iterator<JSONHandler> iter = this.handlerList.iterator();
        while(iter.hasNext())
            iter.next().onJSONDownloadFinished(result);
            // richiama i gestori di tutti gli handler
    }

}
