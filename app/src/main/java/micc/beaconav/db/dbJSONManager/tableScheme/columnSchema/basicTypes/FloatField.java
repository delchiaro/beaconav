package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;

/**
 * Created by nagash on 01/02/15.
 */
public class FloatField extends ColumnField<Float> {

    @Override
    protected Float generateNewInitValue() {
        return new Float(0);
    }

    @Override
    protected Float parseString(String newStringToParse) {
        return Float.parseFloat(newStringToParse);
    }

    @Override
    public Float generateDeepCopy(Float deepCopyThis) {
        return new Float(deepCopyThis);
    }

}
