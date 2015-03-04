package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
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
