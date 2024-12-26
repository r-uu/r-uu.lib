/*
 * Created on 28.06.2003
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code Template
 */
package de.ruu.lib.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** make constants "real" constants (without calculations during value assignment) so that they can be used in annotations */
public final class Time
{
	public final static long MSECS_SEC   = 1000L;
	public final static long MSECS_MIN   = MSECS_SEC  *  60L;
	public final static long MSECS_HOUR  = MSECS_MIN  *  60L;
	public final static long MSECS_DAY   = MSECS_HOUR *  24L;
	public final static long MSECS_WEEK  = MSECS_DAY  *   7L;
	public final static long MSECS_MONTH = MSECS_DAY  *  30L;
	public final static long MSECS_YEAR  = MSECS_DAY  * 365L;

	public static SimpleDateFormat getDateFormatSortableTimestamp()
	{
		return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS");
	}

	public static SimpleDateFormat getDateFormatSortableTimestampPrecisionSeconds()
	{
		return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	}

	public static SimpleDateFormat getDateFormatSortableTimestampPrecisionMinutes()
	{
		return new SimpleDateFormat("yyyy.MM.dd HH:mm");
	}

	public static SimpleDateFormat getDateFormatSortableTimestampPrecisionHours()
	{
		return new SimpleDateFormat("yyyy.MM.dd HH");
	}

	public static SimpleDateFormat getDateFormatSortableTimestampPrecisionDays()
	{
		return new SimpleDateFormat("yyyy.MM.dd");
	}

	public static String getSortableTimestamp()
	{
		return getDateFormatSortableTimestamp().format(new Date());
	}

	public static String getSortableTimestampPrecisionSeconds()
	{
		return getDateFormatSortableTimestampPrecisionSeconds().format(new Date());
	}

	public static String getSortableTimestampPrecisionMinutes()
	{
		return getDateFormatSortableTimestampPrecisionMinutes().format(new Date());
	}

	public static String getSortableTimestampPrecisionMinutes(Date date)
	{
		return getDateFormatSortableTimestampPrecisionMinutes().format(date);
	}

	/**
	 * @return list of {@link LocalDate} ubstabces starting with {@code periodStart} and <b>excluding</b> {@code periodEnd}
	 */
	public static List<LocalDate> datesInPeriod(LocalDate periodStart, LocalDate periodEnd)
	{
		final long days = periodStart.until(periodEnd, ChronoUnit.DAYS);
		return Stream.iterate(periodStart, d -> d.plusDays(1)).limit(days).collect(Collectors.toList());
	}
}