package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public class DoubleSchema extends ColumnSchema<Double> {


    public DoubleSchema(String name) {
        super(name);
    }

    @Override
    public DoubleField newField() {
        return new DoubleField(this);
    }

}
