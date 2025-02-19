package com.oltpbenchmark.benchmarks.chbenchmark.queries;

import com.oltpbenchmark.api.SQLStmt;

public class Q9 extends GenericQuery {
	
    public final SQLStmt query_stmt = new SQLStmt(
              "SELECT n_name, "
            +        "extract(YEAR "
            +                "FROM o_entry_d) AS l_year, "
            +        "sum(ol_amount) AS sum_profit "
            + "FROM item, "
            +      "stock, "
            +      "supplier, "
            +      "order_line, "
            +      "orders, "
            +      "nation "
            + "WHERE ol_i_id = s_i_id "
            +   "AND ol_supply_w_id = s_w_id "
            +   "AND MOD ((s_w_id * s_i_id), 10000) = su_suppkey "
            +   "AND ol_w_id = o_w_id "
            +   "AND ol_d_id = o_d_id "
            +   "AND ol_o_id = o_id "
            +   "AND ol_i_id = i_id "
            +   "AND su_nationkey = n_nationkey "
            +   "AND i_data LIKE '%bb' "
            + "GROUP BY n_name, "
            +          "l_year "
            + "ORDER BY n_name, "
            +          "l_year DESC"
        );

    protected SQLStmt get_query() {
        return query_stmt;
    }
}
