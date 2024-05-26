[back](./fx-comp-architecture.md)

## FX Comp Demo - Empty

This application demonstrates how to get a minimal FX Comp driven application up an running with minimum effort. Many times it is sufficient to simply subclass from classes defined by FX Comp architecture.

### View

The class definition for the component's view below is empty except for the extends clause. By extending ```DefaultFXCView``` a complete default implementation for ```FXCView``` is available immediately.

```
package de.ruu.lib.fx.comp.demo.empty;

import de.ruu.lib.fx.comp.DefaultFXCView;

public class Empty extends DefaultFXCView { }
```

### Controller

Same as the view class definition the controller definition is also (nearly) empty. Extending an empty ```DefaultFXCViewController``` does not bring in many functionality but helps to tie the controller to the corresponding view via internal mechanisms in ```DefaultFXCView```. This results in having a valid match of view and controller without further ado.

```
package de.ruu.lib.fx.comp.demo.empty;

import de.ruu.lib.fx.comp.DefaultFXCViewController;
import javafx.fxml.FXML;

class EmptyController extends DefaultFXCViewController
{
	@Override @FXML protected void initialize() { }
}
```

### App

No surprise here either: To start up a Java FX application that runs a FX Comp component it's sufficient to subclass from FX App.

```
package de.ruu.lib.fx.comp.demo.empty;

import de.ruu.lib.fx.comp.FXCApp;

public class EmptyApp extends FXCApp { }
```

## FX Comp Demo - Composing Applications from a hierarchy of FX Comp components

