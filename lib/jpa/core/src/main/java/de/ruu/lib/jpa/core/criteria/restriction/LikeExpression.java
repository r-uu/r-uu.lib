package de.ruu.lib.jpa.core.criteria.restriction;

import de.ruu.lib.jpa.core.criteria.Criteria;
import de.ruu.lib.jpa.core.criteria.Criterion;

/** like expression */
public class LikeExpression implements Criterion
{
	private final String    property;
	private final Object    value;
	private final Character escapeChar;
	private final boolean   ignoreCase;

	protected LikeExpression(String property, Object value, Character escapeChar, boolean ignoreCase)
	{
		this.property   = property;
		this.value      = value;
		this.escapeChar = escapeChar;
		this.ignoreCase = ignoreCase;
	}

	@Override public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery)
	{
		criteriaQuery.setParam(ignoreCase ? value.toString().toLowerCase() : value);
		String lhs =
		    ignoreCase ? "lower(" + criteriaQuery.getPropertyName(property, criteria) + ")"
		        : criteriaQuery.getPropertyName(property, criteria);
		return lhs + " like ?" + (escapeChar == null ? "" : " escape \'" + escapeChar + "\'");
	}
}