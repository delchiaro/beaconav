package micc.beaconav.dbJSONManager;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import micc.beaconav.dbJSONManager.schema.ColumnField;
import micc.beaconav.dbJSONManager.schema.TableRow;
import micc.beaconav.dbJSONManager.schema.TableSchema;

/**
 * Created by nagash on 21/01/15.
 */

public class JSONLoader extends AsyncTask<String, String, ArrayList<TableRow>>
{

    private TableSchema schema;
    private ArrayList<TableRow> downloadedRows;
    private JSONHandler handler;

    public JSONLoader(TableSchema tableSchema, JSONHandler handler) {
        this.schema = tableSchema;
        this.downloadedRows = null;
        this.handler = handler;
    }

    public void startDownload(String url)
    {
        String[] str = new String[1];
        str[0] = url;
        this.execute(str);
    }


    public ArrayList<TableRow> getDownloadedRows()
    {
        if(downloadedRows != null)
            return downloadedRows;
        else return null;
    }


    @Override
    protected final void onPreExecute() {
        super.onPreExecute();
        this.downloadedRows = null;
        //cancella i vecchi download e indica che non ci sono rows scaricate.
    }


    protected final ArrayList<TableRow> doInBackground(String... args) {

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

        return tableRows;
    }


    protected final void onPostExecute(ArrayList<TableRow> result) {
        this.downloadedRows = result;
        handler.onJSONDownloadFinished(result);
    }

}
