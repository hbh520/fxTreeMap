# FxTreeMap
An JavaFx implementation of a treemap

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/627c82e4b6ed40fa9b5b78d7ea98f4f4)](https://www.codacy.com/app/PtitNoony/FxTreeMap?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=PtitNoony/FxTreeMap&amp;utm_campaign=Badge_Grade)

The original algorithm for the core code is derived from [javafx-chart-treemap](https://github.com/tasubo/javafx-chart-treemap).

## Disclaimer

This small project is in its very early days and features may not be stable.

SO, feel free to request changes!!
I welcome ideas for missing features/architectures...

## Available tree maps components

### FxTreeMap (most complete at the moment)

This version uses a set a `javafx.scene.shape.Rectangle` to draw the treemap.
It also allows more flexibility (on the implementation side) to add new features.


### CanvasTreeMap

This component uses a `javafx.scene.canvas.Canvas` to draw the treemap.

## How to

The main examples are available in the `main` class. Here is an extract of the code

### Create the data set

```java
        SimpleMapData data1 = new SimpleMapData("data1", 6.0);
        ...
        SimpleMapData data7 = new SimpleMapData("data7", 1.0);
        List<MapData> data = new LinkedList<>();
        data.add(data1);
        ...
        data.add(data7);
``` 

### Create the treemap component

```java
        FxTreeMap fxTreeMap = new FxTreeMap(data);
```

### Style the treemap (optional)
```java
        fxTreeMap.setBackgroundColor(Color.LIGHTGRAY);
        fxTreeMap.setStoke(Color.WHITESMOKE);
        fxTreeMap.setBorderRadius(10.0);
        fxTreeMap.setPadding(5);
```

### Add the treemap to the scene

```java
        Node fxTreeMapNode = fxTreeMap.getNode();
        rectAnchorPane.getChildren().add(fxTreeMapNode);
        AnchorPane.setBottomAnchor(fxTreeMapNode, 4.0);
        AnchorPane.setLeftAnchor(fxTreeMapNode, 4.0);
        AnchorPane.setRightAnchor(fxTreeMapNode, 4.0);
        AnchorPane.setTopAnchor(fxTreeMapNode, 4.0);
```


## Next steps

- update UI when data model is changed
- use css styles
- draw recursively in canvas example