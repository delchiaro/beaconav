package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by Riccardo Del Chiaro & Franco Yang (25/02/2015)
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
