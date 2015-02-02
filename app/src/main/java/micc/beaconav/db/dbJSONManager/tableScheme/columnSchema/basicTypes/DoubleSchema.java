package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by nagash on 02/02/15.
 */
public class DoubleSchema extends ColumnSchema {


    public DoubleSchema(String name) {
        super(name);
    }

    @Override
    protected Class generateType() {
        return Double.class;
    }

    @Override
    protected ColumnField generateField() {
        return new DoubleField();
    }
}
