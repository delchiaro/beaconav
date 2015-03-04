package micc.beaconav.db.dbJSONManager;

import org.apache.http.NameValuePair;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public class HttpParam implements NameValuePair {
    private final String name;
    private final String value;

    public HttpParam(String paramName, String paramValue) {
        name = paramName;
        value = paramValue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }
}