package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;

/**
 * Created by nagash on 01/02/15.
 */
public class IntegerField extends ColumnField {

    @Override
    protected Object generateNewInitValue() {
        return new Integer(0);
    }

    @Override
    protected Object parseString(String newStringToParse) {
        return Integer.parseInt(newStringToParse);
    }
}
