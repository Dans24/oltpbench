package com.oltpbenchmark.benchmarks.chbenchmark.queries;

import com.oltpbenchmark.api.SQLStmt;

public class Q3 extends GenericQuery {
	
    public final SQLStmt query_stmt = new SQLStmt(
              "SELECT ol_o_id, "
            +        "ol_w_id, "
            +        "ol_d_id, "
            +        "sum(ol_amount) AS revenue, "
            +        "o_entry_d "
            + "FROM customer, "
            +      "new_order, "
            +      "orders, "
            +      "order_line "
            + "WHERE c_state LIKE 'A%' "
            +   "AND c_id = o_c_id "
            +   "AND c_w_id = o_w_id "
            +   "AND c_d_id = o_d_id "
            +   "AND no_w_id = o_w_id "
            +   "AND no_d_id = o_d_id "
            +   "AND no_o_id = o_id "
            +   "AND ol_w_id = o_w_id "
            +   "AND ol_d_id = o_d_id "
            +   "AND ol_o_id = o_id "
            +   "AND o_entry_d > '2007-01-02 00:00:00.000000' "
            + "GROUP BY ol_o_id, "
            +          "ol_w_id, "
            +          "ol_d_id, "
            +          "o_entry_d "
            + "ORDER BY revenue DESC , o_entry_d"
        );

    protected SQLStmt get_query() {
        return query_stmt;
    }
}
