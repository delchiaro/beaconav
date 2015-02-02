package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by nagash on 02/02/15.
 */
public class StringSchema extends ColumnSchema {


    public StringSchema(String name) {
        super(name);
    }

    @Override
    protected Class generateType() {
        return String.class;
    }

    @Override
    protected ColumnField generateField() {
        return new StringField();
    }
}
