package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by nagash on 02/02/15.
 */
public class LongSchema extends ColumnSchema<Long> {


    public LongSchema(String name) {
        super(name);
    }


    @Override
    protected LongField generateField() {
        return new LongField();
    }
}
