package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Scale;

public class Controller {
    public GridPane gridPane;
    public VBox content;

    public void printButtonPressed(ActionEvent actionEvent) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        printerJob.showPageSetupDialog(null);
        printerJob.showPrintDialog(null);

        PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();

//        ((Pane) gridPane.getParent()).getChildren().remove(gridPane);
//        Node node = new Circle(100, 200, 200);
        Node node = gridPane;

        Bounds boundsInParent = node.getBoundsInParent();
        double width = boundsInParent.getWidth();
        double printableWidth = pageLayout.getPrintableWidth();
        double scaleX = width > printableWidth ? printableWidth / width : 1.0;
        double height = boundsInParent.getHeight();
        double printableHeight = pageLayout.getPrintableHeight();
        double scaleY = height > printableHeight ? printableHeight / height : 1.0;
        System.out.println("printableWidth=" + printableWidth + " printableHeight=" + printableHeight);
        System.out.println("scaleX=" + scaleX + " scaleY=" + scaleY);
        double scale = Math.min(scaleX, scaleY);
        if (scale < 1.0) {
            node.getTransforms().add(new Scale(scale, scale));
        }
        System.out.println("scale=" + scale);

        boolean printResult = printerJob.printPage(node);
        System.out.println("printResult=" + printResult);
        if (printResult) {
            boolean endResult = printerJob.endJob();
            System.out.println("endResult=" + endResult);
        }
    }

    public void initialize() {
//        assert printButton != null : "fx:id=\"printButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'sample.fxml'.";

    }
}
