package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;

/**
 * Created by nagash on 01/02/15.
 */
public class StringField extends ColumnField {

    @Override
    protected Object generateNewInitValue() {
        return new String("");
    }

    @Override
    protected Object parseString(String newStringToParse) {
        return newStringToParse;
    }


}
