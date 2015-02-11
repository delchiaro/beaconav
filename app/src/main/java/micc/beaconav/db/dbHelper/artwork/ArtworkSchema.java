package micc.beaconav.db.dbHelper.artwork;

import micc.beaconav.db.dbJSONManager.tableScheme.TableSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.DoubleSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringSchema;

/**
 * Created by nagash on 01/02/15.
 */
public class ArtworkSchema extends TableSchema<ArtworkRow>
{
    static final String          tableName  = "artwork";

    static final StringSchema    ID          = new StringSchema("ID");
    static final StringSchema    title        = new StringSchema("title");
    static final StringSchema    descr       = new StringSchema("descr");
    static final FloatSchema     x           = new FloatSchema("x");
    static final FloatSchema     y           = new FloatSchema("y");



    private static final ColumnSchema[] columns = new ColumnSchema[]{ ID, title, descr, x, y };

    @Override
    protected String generateTableName() {
        return tableName;
    }

    @Override
    protected ColumnSchema[] generateTableColumns() {
        return columns;
    }

    @Override
    protected ArtworkRow generateRow() {
        return new ArtworkRow();
    }

}
