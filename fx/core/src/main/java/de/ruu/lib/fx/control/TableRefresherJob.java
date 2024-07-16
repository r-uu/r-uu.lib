package de.ruu.lib.fx.control;

import static de.ruu.lib.util.BooleanFunctions.not;

import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableRefresherJob implements Runnable
{
	private TableView<?> tableView;
	private long refreshDelay;

	private boolean stop = false;

	public TableRefresherJob(TableView<?> tableView, long refreshDelay)
	{
		this.tableView    = tableView;
		this.refreshDelay = refreshDelay;
	}

	@Override public void run()
	{
		while (not(stop))
		{
			tableView.refresh();
			log.debug("refreshed table");
			try
			{
				Thread.sleep(refreshDelay);
			}
			catch (InterruptedException e1)
			{
				log.debug("interrupted");
			}
		}
		log.debug("stopping table updater");
	}

	public void setStop(boolean stop) { this.stop = stop; }
}