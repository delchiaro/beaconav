package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by nagash on 02/02/15.
 */
public class FloatSchema extends ColumnSchema<Float> {


    public FloatSchema(String name) {
        super(name);
    }


    @Override
    protected FloatField generateField() {
        return new FloatField();
    }
}
