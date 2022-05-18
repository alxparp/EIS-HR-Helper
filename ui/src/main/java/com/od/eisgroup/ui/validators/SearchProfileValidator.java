package com.od.eisgroup.ui.validators;

import org.primefaces.validate.ClientValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Map;

/**
 * Validates the symbols according to the specification BRM038, BRM039, BRM040
 */
@FacesValidator("searchProfileValidator")
public class SearchProfileValidator implements Validator, ClientValidator {
    private static final String VALIDATOR_ID = "searchProfileValidator";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) {
        // The maximum number of symbols to enter
        final int MAX_SYMBOLS = 50;
        // Test error to display to the user.
        String messageMaxSymbol = "More than 50 symbols are not allowed";
        String messageNotAllowed = "Your search input is not allowed";

        if (o == null) {
            printExceptionMessage(messageNotAllowed);
        } else {
            String searchLine = o.toString();
            if (searchLine.length() > MAX_SYMBOLS) {
                printExceptionMessage(messageMaxSymbol);
            }
            if (searchLine.isEmpty() || searchLine.equals(" ")) {
                printExceptionMessage(messageNotAllowed);
            }
            if (!checkSymbols(searchLine)) {
                printExceptionMessage(messageNotAllowed);
            }
        }

    }

    /**
     * First checks containing only english symbol and the symbols ' ' and '-'.
     * Second checks count the symbols ' ' and '-'.
     *
     * @param message message for validation
     * @return {@code True} if {@code message} is matched, otherwise {@code false}.
     */
    private boolean checkSymbols(String message) {
        return message.matches("([A-Za-z\\s\\-]+)");
    }

    /**
     * Throws exception that the search line no validation.
     *
     * @param messageException The text of the error.
     * @throws ValidatorException type exception.
     */
    private void printExceptionMessage(String messageException) {
        FacesMessage message = new FacesMessage(messageException);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }

    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }

    @Override
    public String getValidatorId() {
        return VALIDATOR_ID;
    }
}
