package de.ruu.lib.fx.comp.demo.hierarchy;

import java.util.Optional;

import de.ruu.lib.cdi.se.EventDispatcher;
import de.ruu.lib.fx.FXUtil;
import de.ruu.lib.fx.comp.DefaultFXCViewController;
import de.ruu.lib.fx.comp.FXCAppStartedEvent;
import de.ruu.lib.fx.comp.demo.hierarchy.sub1.HierarchyDemoSub1;
import de.ruu.lib.fx.comp.demo.hierarchy.sub2.HierarchyDemoSub2;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * Java FX Component View Controller
 * <p>
 * generated by {@code de.ruu.lib.gen.java.fx.comp.GeneratorFXCViewController} at 2024.05.17 12:57:18:189
 */
@Slf4j
public class HierarchyDemoMainController extends DefaultFXCViewController
{
	@FXML private AnchorPane root;
	@FXML private AnchorPane main;
	@FXML private Button btnShow1;
	@FXML private Button btnShow2;

	@Inject private EventDispatcher<FXCAppStartedEvent> eventDispatcherFXCAppStarted;

	@Inject private HierarchyDemoSub1 sub1;
	@Inject private HierarchyDemoSub2 sub2;

	@Override @FXML protected void initialize()
	{
		log.debug("eventDispatcherFXCAppStarted == null {}", eventDispatcherFXCAppStarted == null);

		eventDispatcherFXCAppStarted.add(e -> onAppStarted(e));

		FXUtil.setAnchorsInAnchorPaneTo(root, 0);
		FXUtil.setAnchorsInAnchorPaneTo(main, 0);
		
		main.getChildren().add(sub1.getLocalRoot());
		
		btnShow1.setOnAction(e -> onBtnShow1(e));
		btnShow2.setOnAction(e -> onBtnShow2(e));
	}

	private void onBtnShow1(ActionEvent e)
	{
		main.getChildren().removeAll(main.getChildren());
		main.getChildren().add(sub1.getLocalRoot());
	}

	private void onBtnShow2(ActionEvent e)
	{
		main.getChildren().removeAll(main.getChildren());
		main.getChildren().add(sub2.getLocalRoot());
	}

	private void onAppStarted(FXCAppStartedEvent e)
	{
		Optional<Stage> optional = FXUtil.getStage(root);

		if (optional.isPresent())
		{
			optional.get().setTitle("fx component hierarchy demo");
		}
	}
}