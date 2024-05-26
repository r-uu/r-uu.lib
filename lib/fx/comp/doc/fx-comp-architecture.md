[back](../README)

## FX Comp Architecture - Conceptual Overview

The following diagram shows, in a somewhat simplified way, central ideas behind the FX Comp Architecture from a user's (i.e. a developer's) point of view.

An important design goal of FX Comp is to make development of complex user interfaces as simple and flexible as possible. At the same time, the complexity of even large applications is to be reduced by modularising and encapsulating the components.

To achieve this for FX Comp driven applications there are several ways for inter component communication. The diagram shows how tightly coupled components can use synchronous API calls or exchange events when they a loosely coupled. 

![FX Comp Architecture](fx-comp-architecture.png)
*conceptual overview of FX Comp collaboration*

### View

In FX Comp the **view** type is a Java class that can be used to compose complex user interfaces. It provides a handle to the FX node graph that represents the visual appearance of a component and a method to retrieve access to the component's service methods. The behaviour of the component is defined "behind the scenes" by a custom controller class (see below).

### Controller

The controller defines the behaviour of the component. It has access to all relevant visual controls. ```FXMLLoader``` achieves this by injecting values to controller fields annotated with ```@FXML``` at load time.

FX Comp internally uses CDI to bootstrap all the required parts (e.g. the above-mentioned service and the controller instances). Thereby CDI is globally available in a FX comp based application.

### Layout

FX Comp supports layout configuration of user interface components in .fxml files. Typically these are created and maintained using Gluon's excellent visual design tool [SceneBuilder](https://gluonhq.com/products/scene-builder/).

### Service

A component in FX Comp provides access to custom services. For example an editor component for a customer might provide a ```Customer getInitialCustomerValue()``` and a  ```Customer getModifiedCustomerValue()``` method. Service methods are usually defined in Java interfaces.

### App

FX Comp components are autonomous software building blocks which can run for themselves in a JavaFX application. This allows for early user feedback regarding the visual design and eases testing the implementation of the component's behaviour. 

## FX Comp Architecture - Detailed Technical Overview

FX Comp components are structured into a couple of collaborating Java types. In the following the collaboration is described in more technical detail.

@startuml FX Comp Architecture
''''''''''
' settings
''''''''''
'  left to right direction
'  top to bottom direction
  skinparam linetype ortho
  ' avoids nesting of packages
  set separator none
  package de.ruu.lib.fx.comp
  {
'''''''
' types
'''''''
    interface FXCView
    {
      ~ Parent getLocalRoot()
      ~ FXCViewService getService()
    }
    interface FXCViewController
    interface FXCViewService
    abstract class DefaultFXCView
    {
'    - Parent localRoot
'    - FXCViewService service
'    - Optional<? extends FXCViewController> controllerOptional
'    - Scene scene
'    - String fxmlResourceName
'    - String cssResourceName
      + Parent getLocalRoot()
      + FXCViewService getService()
      # Optional<? extends FXCViewController> getController()
'    - FXMLLoader createFXMLLoader()
'    # Scene getScene()
'    # void addStylesheet(Scene)
'    # String getFXLMResourceName()
'    # String getCSSResourceName()
'    # Class<? extends FXCViewService> getServiceClass()
'    # Optional<Class<? extends FXCViewController>> getControllerClass()
'    # String getServiceClassName()
'    # String getControllerClassName()
'    # String getClassNameApp()
    }
    abstract class DefaultFXCViewController
    {
      # void initialize()
    }
    abstract class FXCApp
    {
    }
  }
  package javafx.scene
  {
    abstract class Parent
    {
    }
  }
  package javafx.application
  {
    abstract class Application
    {
    }
  }
'''''''''''
' relations
'''''''''''
  FXCView                  "provides"             ..       Parent
  FXCView                  "provides"             ..       FXCViewService
  FXCView                                       <|..       DefaultFXCView
  FXCViewController                             <|..       DefaultFXCViewController
  DefaultFXCView           "bootstraps default"   ..       FXCViewController
  DefaultFXCViewController                      -[hidden]> FXCApp
  Application                                   <|..       FXCApp
  FXCApp                                        -[hidden]> Application
@enduml
```
*top level types for FX Comp*

The above diagrams shows the most important types that typically are used when creating FX Comp driven applications.

### FXCView

First of all there is ```FXCView```. ```FXCView``` provides a ```javafx.scene.Parent``` that represents the layout of the visual component and at the same time automatically associates a custom ```FXCViewController``` "behind the scenes" that determines the component's behaviour. The abstract default implementations ```DefaultFXCView``` and ```DefaultFXCViewController``` are perfect starting points for the definition of custom FX Comp components. Extending these abstract classes with empty default implementations suffices to get a basic version of a custom FX Comp up and running (see in examples).

### FXCViewController

Implementations of FXCViewController classes are the working horses in a FX Comp driven application. These classes are where the behaviour and the collaboration with other FX Comp components is defined.

In fact service calls to other components as well as producing and consuming events is done inside controller classes. However, controllers do not depend on other view's controllers. Instead, they only depend on the interfaces of the views they collaborate with.

### FXCApp

This abstract class helps to create standalone JavaFX Applications that run a single FX Comp component.

[FX Comp Demo](fx-comp-demo.md)