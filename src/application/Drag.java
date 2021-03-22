package application;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class Drag {

	
	private Node makeDraggable(final Node node) {
		final DragContext dragContext = new DragContext();
		final Group wrapGroup = new Group(node);

		wrapGroup.addEventFilter(
		    MouseEvent.ANY,
		    new EventHandler<MouseEvent>() {
		        public void handle(final MouseEvent mouseEvent) {
		            if (dragModeActiveProperty.get()) {
		                // disable mouse events for all children
		                mouseEvent.consume();
		            }
		         }
		    });

		wrapGroup.addEventFilter(
		    MouseEvent.MOUSE_PRESSED,
		    new EventHandler<MouseEvent>() {
		        public void handle(final MouseEvent mouseEvent) {
		            if (dragModeActiveProperty.get()) {
		                // remember initial mouse cursor coordinates
		                // and node position
		                dragContext.mouseAnchorX = mouseEvent.getX();
		                dragContext.mouseAnchorY = mouseEvent.getY();
		                dragContext.initialTranslateX =
		                    node.getTranslateX();
		                dragContext.initialTranslateY =
		                    node.getTranslateY();
		            }
		        }
		    });

		wrapGroup.addEventFilter(
		    MouseEvent.MOUSE_DRAGGED,
		    new EventHandler<MouseEvent>() {
		        public void handle(final MouseEvent mouseEvent) {
		            if (dragModeActiveProperty.get()) {
		                // shift node from its initial position by delta
		                // calculated from mouse cursor movement
		                node.setTranslateX(
		                    dragContext.initialTranslateX
		                        + mouseEvent.getX()
		                        - dragContext.mouseAnchorX);
		                node.setTranslateY(
		                    dragContext.initialTranslateY
		                        + mouseEvent.getY()
		                        - dragContext.mouseAnchorY);
		            }
		        }
		    });

		return wrapGroup;

		}
	
}
