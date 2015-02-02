package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;

/**
 * Created by nagash on 01/02/15.
 */
public class LongField extends ColumnField<Long> {

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
