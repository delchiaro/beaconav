package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by nagash on 01/02/15.
 */
public class LongField extends ColumnField<Long> {

    public LongField(ColumnSchema<Long> schema) {
        super(schema);
    }

    @Override
    protected Long generateNewInitValue() {
        return new Long(0);
    }

    @Override
    protected Long parseString(String newStringToParse) {
        return Long.parseLong(newStringToParse);
    }

    @Override
    public Long generateDeepCopy(Long deepCopyThis) {
        return new Long(deepCopyThis);
    }

}
