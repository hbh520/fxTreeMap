/*
 * The MIT License
 *
 * Copyright 2017 Arnaud Hamon
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.ptitnoony.components.fxtreemap;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.runLater;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javax.swing.Timer;

/**
 *
 * @author ahamon
 */
public abstract class TreeMap implements PropertyChangeListener {

    private static final Logger LOG = Logger.getGlobal();

    private final AnchorPane mainNode;
    private final Timer timer;

    private boolean withDelay;

    private double width = TreeMapUtils.DEFAULT_WIDTH;
    private double height = TreeMapUtils.DEFAULT_HEIGHT;

    /**
     * Creates an abstract TreeMap.
     *
     * @param withLayoutDelay value to be used to check if re-layout needs to be
     * delayed.
     */
    public TreeMap(boolean withLayoutDelay) {
        mainNode = new AnchorPane();
        withDelay = withLayoutDelay;
        //
        mainNode.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            LOG.log(Level.FINEST, "{0} changed from {1} to {2}", new Object[]{observable, oldValue, newValue});
            width = newValue.doubleValue();
            requestLayoutUpdate();
        });
        mainNode.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            LOG.log(Level.FINEST, "{0} changed from {1} to {2}", new Object[]{observable, oldValue, newValue});
            height = newValue.doubleValue();
            requestLayoutUpdate();
        });
        timer = new Timer(TreeMapUtils.DEFAULT_TIMER_DELAY, (ActionEvent e) -> {
            LOG.log(Level.INFO, "Restart layout update timer on event {0}", e);
            runLater(this::applyLayout);
        });
        timer.setRepeats(false);
    }

    /**
     * Creates an abstract TreeMap immediate layout after resize.
     */
    public TreeMap() {
        this(false);
    }

    /**
     * Get the JavaFx node to be added to the scene.
     *
     * @return the component node to be added to the scene
     */
    public Node getNode() {
        return mainNode;
    }

    /**
     *
     * @return the component's width
     */
    public final double getWidth() {
        return width;
    }

    /**
     *
     * @return the component's height
     */
    public final double getHeight() {
        return height;
    }

    /**
     * Will trigger a layout update, which timing depends on the withDelay mode
     * activation
     */
    public final void requestLayoutUpdate() {
        if (withDelay) {
            triggerTimer();
        } else {
            applyLayout();
        }
    }

    /**
     * Get the container that will contain the MapItems
     *
     * @return the TreeMap item container
     */
    protected final AnchorPane getContainer() {
        return mainNode;
    }

    //
    // Abstract methods
    //
    /**
     * Get the data represented by the TreeMap.
     *
     * @return the data represented
     */
    public abstract MapData getData();

    public abstract void setBackgroundColor(Color newBackgroundColor);

    public abstract void setDataFill(Color newBackgroundColor);

    public abstract void setDataStroke(Color newStrokeColor);

    public abstract void setDataBorderRadius(double newBorderRadius);

    public abstract void setDataStrokeWidth(double newStrokeWidth);

    public abstract void setPadding(double newPaddingValue);

    public abstract Color getBackgroundColor();

    public abstract Color getDataFill();

    public abstract Color getDataStroke();

    public abstract double getDataBorderRadius();

    public abstract double getDataStrokeWidth();

    public abstract double getPadding();

    protected abstract void applyLayout();

    //
    // Private methods
    //
    private void triggerTimer() {
        if (timer.isRunning()) {
            timer.restart();
        } else {
            timer.start();
        }
    }
}
