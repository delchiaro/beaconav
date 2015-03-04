package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public class StringField extends ColumnField<String> {

    public StringField(ColumnSchema<String> schema) {
        super(schema);
    }

    @Override
    protected String generateNewInitValue() {
        return new String("");
    }

    @Override
    protected String parseString(String newStringToParse) {
        return newStringToParse;
    }

    @Override
    public String generateDeepCopy(String deepCopyThis) {
        return new String(deepCopyThis);
    }


}
