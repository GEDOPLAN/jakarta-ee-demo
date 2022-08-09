package de.gedoplan.showcase.view;

import de.gedoplan.showcase.faces.CurrencyPresenter;
import de.gedoplan.showcase.service.CurrencyService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.application.StateManager;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.UISelectItems;
import jakarta.faces.component.html.*;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.facelets.Facelet;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Log
@View("/currencyCalculatorJava.xhtml")
@ApplicationScoped
public class CurrencyCalculatorView extends Facelet {
  @Inject
  CurrencyPresenter currencyPresenter;

  @Inject
  CurrencyService currencyService;

  @Override
  public void apply(FacesContext facesContext, UIComponent parent) throws IOException {
    if (!facesContext.getAttributes().containsKey(StateManager.IS_BUILDING_INITIAL_STATE)) {
      return;
    }

    /* HTML Startup
      <!DOCTYPE html>
      <html xmlns="http://www.w3.org/1999/xhtml"/>
        ...
      </html>
     */

    UIOutput output = new UIOutput();
    output.setValue("<!DOCTYPE html>");
    parent.getChildren().add(output);

    output = new UIOutput();
    output.setValue("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    parent.getChildren().add(output);

    /* Head
      <h:head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <title>Currency calculator</title>
      </h:head>
     */

    HtmlHead head = (HtmlHead) facesContext.getApplication().createComponent(HtmlHead.COMPONENT_TYPE);

    output = new UIOutput();
    output.setValue("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />");
    head.getChildren().add(output);

    output = new UIOutput();
    output.setValue("<title>Currency calculator</title>");
    head.getChildren().add(output);

    parent.getChildren().add(head);

    /* Start body
      <h:body>
        ...
      </h:body>
     */

    HtmlBody body = (HtmlBody) facesContext.getApplication().createComponent(HtmlBody.COMPONENT_TYPE);

    /* Header
      <h:panelGrid columns="2">
        <h3>Currency calculator</h3>

        <h:graphicImage library="images" name="scheineUndMuenzen1.jpg" />
      </h:panelGrid>
     */

    HtmlPanelGrid headerGrid = (HtmlPanelGrid) facesContext.getApplication().createComponent(HtmlPanelGrid.COMPONENT_TYPE);
    headerGrid.setColumns(2);

    output = new UIOutput();
    output.setValue("<h3>Currency calculator</h3>");
    headerGrid.getChildren().add(output);

    HtmlGraphicImage image = (HtmlGraphicImage) facesContext.getApplication().createComponent(HtmlGraphicImage.COMPONENT_TYPE);
    // TODO: How to add resources
    headerGrid.getChildren().add(image);

    body.getChildren().add(headerGrid);

    /* Start form
      <h:form>
        ...
      </h:form>
     */

    HtmlForm form = (HtmlForm) facesContext.getApplication().createComponent(HtmlForm.COMPONENT_TYPE);
    form.setId("main");
    body.getChildren().add(form);

    /* Start main grid
      <h:panelGrid columns="2">
        ...
      </h:panelGrid>
     */

    HtmlPanelGrid mainGrid = (HtmlPanelGrid) facesContext.getApplication().createComponent(HtmlPanelGrid.COMPONENT_TYPE);
    mainGrid.setColumns(2);

    /* Foreign currency selection
      <h:outputLabel for="foreignCurrency" value="Currency: " />
      <h:selectOneMenu id="foreignCurrency" value="#{currencyPresenter.currencyId}">
        <f:selectItems value="#{currencyPresenter.currencies}" var="currency" itemLabel="#{currency.id}" itemValue="#{currency.id}" />
      </h:selectOneMenu>
    * */

    HtmlOutputLabel foreignCurrencyLabel = (HtmlOutputLabel) facesContext.getApplication().createComponent(HtmlOutputLabel.COMPONENT_TYPE);
    foreignCurrencyLabel.setFor("foreignCurrency");
    foreignCurrencyLabel.setValue("Currency: ");

    HtmlSelectOneMenu foreignCurrencySelection = (HtmlSelectOneMenu) facesContext.getApplication().createComponent(HtmlSelectOneMenu.COMPONENT_TYPE);
    foreignCurrencySelection.setId("foreignCurrency");
    UISelectItems currencySelectionItems = new UISelectItems();
    var selectItems = currencyPresenter.getCurrencies().stream()
        .map(currency -> new SelectItem(currency.getId(), currency.getId()))
        .collect(Collectors.toList());
    currencySelectionItems.setValue(selectItems);
    foreignCurrencySelection.getChildren().add(currencySelectionItems);

    mainGrid.getChildren().add(foreignCurrencyLabel);
    mainGrid.getChildren().add(foreignCurrencySelection);

    /* Input currency amount
      <h:outputLabel for="currencyAmount" value="Amount: " />
      <h:inputText id="currencyAmount" value="#{currencyPresenter.currencyAmount}" />
    */

    HtmlOutputLabel currencyAmountLabel = (HtmlOutputLabel) facesContext.getApplication().createComponent(HtmlOutputLabel.COMPONENT_TYPE);
    currencyAmountLabel.setFor("currencyAmount");
    currencyAmountLabel.setValue("Amount: ");

    HtmlInputText currencyAmountInput = (HtmlInputText) facesContext.getApplication().createComponent(HtmlInputText.COMPONENT_TYPE);
    currencyAmountInput.setId("currencyAmount");

    mainGrid.getChildren().add(currencyAmountLabel);
    mainGrid.getChildren().add(currencyAmountInput);

    /* Command button
      <h:commandButton value="convert" action="#{currencyPresenter.convert()}" />
      <h:panelGroup />
     */

    HtmlOutputText euroAmount = (HtmlOutputText) facesContext.getApplication().createComponent(HtmlOutputText.COMPONENT_TYPE);
    euroAmount.setId("euroAmount");

    HtmlCommandButton convertButton = (HtmlCommandButton) facesContext.getApplication().createComponent(HtmlCommandButton.COMPONENT_TYPE);
    convertButton.setId("convertButton");
    convertButton.setValue("convert");
    convertButton.addActionListener(event -> {
      log.info("Button pressed: " + event.getSource());
      log.info(currencyAmountInput.getValue().getClass().getName());
      log.info(foreignCurrencySelection.getValue().getClass().getName());
      euroAmount.setValue(currencyService.convertToEuro(new BigDecimal(currencyAmountInput.getValue().toString()), foreignCurrencySelection.getValue().toString()));
    });
    HtmlPanelGroup fillUp = (HtmlPanelGroup) facesContext.getApplication().createComponent(HtmlPanelGroup.COMPONENT_TYPE);

    mainGrid.getChildren().add(convertButton);
    mainGrid.getChildren().add(fillUp);

    /* Euro amount
      <h:outputLabel for="euroAmount" value="Euro amount: " />
      <h:outputText id="euroAmount" value="#{currencyPresenter.euroAmount}" />
     */

    HtmlOutputLabel euroAmountLabel = (HtmlOutputLabel) facesContext.getApplication().createComponent(HtmlOutputLabel.COMPONENT_TYPE);
    euroAmountLabel.setFor("euroAmount");
    euroAmountLabel.setValue("Euro amount: ");

    mainGrid.getChildren().add(euroAmountLabel);
    mainGrid.getChildren().add(euroAmount);

    form.getChildren().add(mainGrid);

    /* No footer because we have added enough components for now */

    parent.getChildren().add(body);

    output = new UIOutput();
    output.setValue("</html>");
    parent.getChildren().add(output);
  }
}
