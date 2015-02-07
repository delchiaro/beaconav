package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by nagash on 01/02/15.
 */
public class DoubleField extends ColumnField<Double> {

    public DoubleField(ColumnSchema<Double> schema) {
        super(schema);
    }

    @Override
    protected Double generateNewInitValue() {
        return new Double(0);
    }

    @Override
    protected Double parseString(String newStringToParse) {
        return Double.parseDouble(newStringToParse);
    }

    @Override
    public Double generateDeepCopy(Double deepCopyThis) {
        return new Double(deepCopyThis);
    }


}
