package de.ruu.lib.jpa.core.criteria.restriction;

import de.ruu.lib.jpa.core.criteria.Criteria;
import de.ruu.lib.jpa.core.criteria.Criteria.CriteriaQuery;
import de.ruu.lib.jpa.core.criteria.Criterion;

/** Insensitive In expression */
public class InExpressionInsensitive implements Criterion
{
	private final String   property;
	private final String[] values;

	protected InExpressionInsensitive(String property, String[] values)
	{
		this.property = property;
		this.values   = values;
	}

	@Override public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery)
	{
		String sql = "lower(" + criteriaQuery.getPropertyName(property, criteria) + ") in (";

		for (String v : values)
		{
			criteriaQuery.setParam(v.toLowerCase());
			sql += "?,";
		}

		return sql.substring(0, sql.length() - 1) + ")";
	}
}