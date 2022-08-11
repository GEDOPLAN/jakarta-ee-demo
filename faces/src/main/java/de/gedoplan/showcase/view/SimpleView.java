package de.gedoplan.showcase.view;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.application.StateManager;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.html.HtmlBody;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.facelets.Facelet;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
@View("/simple.xhtml")
@ApplicationScoped
public class SimpleView extends Facelet {
  @Override
  public void apply(FacesContext facesContext, UIComponent parent) throws IOException {
    if (!facesContext.getAttributes().containsKey(StateManager.IS_BUILDING_INITIAL_STATE)) {
      return;
    }

    UIOutput output = new UIOutput();
    output.setValue("<!DOCTYPE html>\n<html>");
    parent.getChildren().add(output);

    HtmlBody body = (HtmlBody) facesContext.getApplication().createComponent(HtmlBody.COMPONENT_TYPE);

    // Components may be placed here

    parent.getChildren().add(body);

    output = new UIOutput();
    output.setValue("</html>");
    parent.getChildren().add(output);
  }
}
