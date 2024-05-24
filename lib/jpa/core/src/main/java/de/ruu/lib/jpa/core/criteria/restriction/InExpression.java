package de.ruu.lib.jpa.core.criteria.restriction;

import de.ruu.lib.jpa.core.criteria.Criteria;
import de.ruu.lib.jpa.core.criteria.Criteria.CriteriaQuery;
import de.ruu.lib.jpa.core.criteria.Criterion;

/** in expression */
public class InExpression implements Criterion
{
	private final String   property;
	private final Object[] values;

	protected InExpression(String property, Object[] values)
	{
		this.property = property;
		this.values   = values;
	}

	@Override public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery)
	{
		String sql = criteriaQuery.getPropertyName(property, criteria) + " in (";

		for (Object v : values)
		{
			criteriaQuery.setParam(v);
			sql += "?,";
		}

		return sql.substring(0, sql.length() - 1) + ")";
	}
}