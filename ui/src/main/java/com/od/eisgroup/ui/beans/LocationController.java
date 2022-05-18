package com.od.eisgroup.ui.beans;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents controller for location pop-up window.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Controller
@SessionScope
@Getter
@Setter
public class LocationController {

    private List<String> locations;
    private String[] selectedLocations;

    private boolean isAllSelected = false;
    private boolean confirmButtonDisabled;

    @PostConstruct
    public void init() {
        locations = new ArrayList<>();

        locations.add("ODS");
        locations.add("MNSK");
        locations.add("RIX");

        //By default Odessa and Minsk should be checked
        selectedLocations = new String[]{"ODS", "MNSK"};

    }

    public void selectAll() {
        if (isAllSelected) {
            selectedLocations = locations.toArray(selectedLocations);
        } else {
            selectedLocations = new String[]{};
        }
        updateSelectedValues();
    }

    public void confirm() {
        if (!isSomeSelected()) {
            showMessage("At least one Location should be checked");
            this.confirmButtonDisabled = true;
            updateSelectedValues();
        } else {
            closeLocationDialog();
        }
    }

    public void select() {
        if (isAllSelected) {
            isAllSelected = false;
        }
        if (isSomeSelected()) {
            this.setConfirmButtonDisabled(false);
        }
        updateSelectedValues();
    }

    private boolean isSomeSelected() {
        return selectedLocations.length != 0;
    }

    private void closeLocationDialog() {
        PrimeFaces.current().executeScript("locationDialog.hide();");
    }

    private void showMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    private void updateSelectedValues() {
        PrimeFaces.current().ajax().update("locDialogForm");
    }
}
