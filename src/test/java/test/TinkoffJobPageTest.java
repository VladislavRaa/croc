package test;

import org.junit.Test;
import pages.TinkoffJobPage;

public class TinkoffJobPageTest extends BaseRunner {
    @Test
    public void testEmptyFields() {
        app.tinkoffJob.openPage()
                .openPage()
                .checkErrorEmptyNameField()
                .checkErrorEmptyEmailField()
                .checkErrorEmptyPhoneField()
                .checkErrorEmptyCityField()
                .checkEmptyVacancyField();
    }
}
