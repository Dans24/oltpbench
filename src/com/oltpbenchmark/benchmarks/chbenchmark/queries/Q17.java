package com.oltpbenchmark.benchmarks.chbenchmark.queries;

import com.oltpbenchmark.api.SQLStmt;

public class Q17 extends GenericQuery {
	
    public final SQLStmt query_stmt = new SQLStmt(
              "SELECT SUM (ol_amount) / 2.0 AS avg_yearly "
            + "FROM order_line, "
            +   "(SELECT i_id, AVG (ol_quantity) AS a "
            +    "FROM item, "
            +         "order_line "
            +    "WHERE i_data LIKE '%b' "
            +      "AND ol_i_id = i_id "
            +    "GROUP BY i_id) t "
            + "WHERE ol_i_id = t.i_id "
            +   "AND ol_quantity < t.a"
        );

    protected SQLStmt get_query() {
        return query_stmt;
    }
}
