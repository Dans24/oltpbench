package com.oltpbenchmark.benchmarks.chbenchmark.queries;

import com.oltpbenchmark.api.SQLStmt;

public class Q8 extends GenericQuery {
	
    public final SQLStmt query_stmt = new SQLStmt(
              "SELECT extract(YEAR "
            +                "FROM o_entry_d) AS l_year, "
            +        "sum(CASE WHEN n2.n_name = 'Germany' THEN ol_amount ELSE 0 END) / sum(ol_amount) AS mkt_share "
            + "FROM item, "
            +      "supplier, "
            +      "stock, "
            +      "order_line, "
            +      "orders, "
            +      "customer, "
            +      "nation n1, "
            +      "nation n2, "
            +      "region "
            + "WHERE i_id = s_i_id "
            +   "AND ol_i_id = s_i_id "
            +   "AND ol_supply_w_id = s_w_id "
            +   "AND MOD ((s_w_id * s_i_id), 10000) = su_suppkey "
            +   "AND ol_w_id = o_w_id "
            +   "AND ol_d_id = o_d_id "
            +   "AND ol_o_id = o_id "
            +   "AND c_id = o_c_id "
            +   "AND c_w_id = o_w_id "
            +   "AND c_d_id = o_d_id "
            +   "AND n1.n_nationkey = ascii(substring(c_state from  1  for  1)) "
            +   "AND n1.n_regionkey = r_regionkey "
            +   "AND ol_i_id < 1000 "
            +   "AND r_name = 'Europe' "
            +   "AND su_nationkey = n2.n_nationkey "
            +   "AND i_data LIKE '%b' "
            +   "AND i_id = ol_i_id "
            + "GROUP BY l_year "
            + "ORDER BY l_year"
        );

    protected SQLStmt get_query() {
        return query_stmt;
    }
}
