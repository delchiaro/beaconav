package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by Riccardo Del Chiaro & Franco Yang (25/02/2015)
 */
public class LongSchema extends ColumnSchema<Long> {


    public LongSchema(String name) {
        super(name);
    }

    @Override
    public LongField newField() {
        return new LongField(this);
    }


}
