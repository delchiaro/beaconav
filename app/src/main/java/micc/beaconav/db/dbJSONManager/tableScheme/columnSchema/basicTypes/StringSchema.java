package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by nagash on 02/02/15.
 */
public class StringSchema extends ColumnSchema<String> {


    public StringSchema(String name) {
        super(name);
    }

    @Override
    public StringField newField() {
        return new StringField(this);
    }


}
