package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public class IntegerSchema extends ColumnSchema<Integer> {


    public IntegerSchema(String name) {
        super(name);
    }

    @Override
    public IntegerField newField() {
        return null;
    }

}
