package com.oltpbenchmark.benchmarks.chbenchmark.queries;

import com.oltpbenchmark.api.SQLStmt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Q15 extends GenericQuery {

    public final SQLStmt createview_stmt = new SQLStmt(
              "CREATE view revenue0 (supplier_no, total_revenue) AS "
            +     "SELECT "
            +         "mod((s_w_id * s_i_id),10000) as supplier_no, "
            +         "sum(ol_amount) as total_revenue "
            +     "FROM "
            +         "order_line, stock "
            +     "WHERE "
            +         "ol_i_id = s_i_id "
            +         "AND ol_supply_w_id = s_w_id "
            +         "AND ol_delivery_d >= '2007-01-02 00:00:00.000000' "
            +     "GROUP BY "
            +         "supplier_no"
        );
    
    public final SQLStmt query_stmt = new SQLStmt (
              "WITH revenue AS ( "
            +     "SELECT "
            +         "mod((s_w_id * s_i_id),10000) as supplier_no, "
            +         "sum(ol_amount) as total_revenue "
            +     "FROM "
            +         "order_line, stock "
            +     "WHERE "
            +         "ol_i_id = s_i_id "
            +         "AND ol_supply_w_id = s_w_id "
            +         "AND ol_delivery_d >= '2007-01-02 00:00:00.000000' "
            +     "GROUP BY "
            +         "supplier_no "
            + ") "
            + "SELECT su_suppkey, "
            +        "su_name, "
            +        "su_address, "
            +        "su_phone, "
            +        "total_revenue "
            + "FROM supplier, revenue "
            + "WHERE su_suppkey = supplier_no "
            +     "AND total_revenue = (select max(total_revenue) from revenue) "
            + "ORDER BY su_suppkey"
        );
	
    public final SQLStmt dropview_stmt = new SQLStmt(
              "DROP VIEW revenue0"
        );

    protected SQLStmt get_query() {
	    return query_stmt;
    }

    public ResultSet run(Connection conn) throws SQLException {
        // With this query, we have to set up a view before we execute the
        // query, then drop it once we're done.
        Statement stmt = conn.createStatement();
        ResultSet ret = null;
        try {
            stmt.executeUpdate(createview_stmt.getSQL());
            ret = super.run(conn);
        } finally {
            stmt.executeUpdate(dropview_stmt.getSQL());
        }

        return ret;
    }
}
