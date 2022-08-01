package de.gedoplan.showcase.faces;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class NavigationPresenter implements Serializable {
  private List<String> viewIdStack = new LinkedList<>();

  public void pushCurrentViewId() {
    String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
    this.viewIdStack.add(0, viewId);
  }

  public String popViewId() {
    String viewId = this.viewIdStack.isEmpty() ? null : this.viewIdStack.remove(0);
    return viewId;
  }

  public boolean isViewIdAvailable() {
    return !this.viewIdStack.isEmpty();
  }
}
