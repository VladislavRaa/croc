package test;

import org.junit.Test;
import pages.GoogleMainPage;
import pages.GoogleResultPage;
import pages.TinkoffJobPage;
import pages.TinkoffMobileOperatorPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TinkoffMobileOperatorPageTest extends BaseRunner{
    @Test
    public void tabSwitching() {
        GoogleMainPage googleMainPage = app.google;
        googleMainPage.open();
        googleMainPage.openSearchResultsPageByRequest("тинькофф мобайл тарифы");
        GoogleResultPage googleResultPage = app.googleResults;
        googleResultPage.clickSearchResultsByLinkContains("https://www.tinkoff.ru/mobile-operator/tariffs/");
        TinkoffJobPage job = app.tinkoffJob;
        job.switchToMainTab();
        job.closeCurrentTab();
        job.switchToMainTab();
        job.checkUrl("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }
    @Test
    public void changeOfRegion() {
        TinkoffMobileOperatorPage tinkoffMobile = app.tinkoffMobile;
        tinkoffMobile.open()
                .acceptRegion()
                .changeRegion("Москва")
                .assertRegion("Москва")
                .refresh();
        tinkoffMobile.assertRegion("Москва");
        String priceMsk = tinkoffMobile.getTotalPrice();
        tinkoffMobile
                .changeRegion("Краснодар")
                .refresh();
        String priceKrasnodar = tinkoffMobile.getTotalPrice();
        assertNotEquals(priceKrasnodar, priceMsk);
        tinkoffMobile.setInternet("Безлимитный")
                .setMinutes("Безлимитны")
                .setModem(true)
                .setUnlimitedSMS(true);
        String maxPriceKrasnodar = tinkoffMobile.getTotalPrice();
        tinkoffMobile.changeRegion("Москва");
        tinkoffMobile.setInternet("Безлимитный")
                .setMinutes("Безлимитны")
                .setModem(true)
                .setUnlimitedSMS(true);
        String maxPriceMsk = tinkoffMobile.getTotalPrice();
        assertEquals(maxPriceMsk, maxPriceKrasnodar);
    }
    @Test
    public void notActiveButton() {
        TinkoffMobileOperatorPage tinkoffMobile = app.tinkoffMobile;
        tinkoffMobile.open();
        tinkoffMobile.setInternet("0 ГБ")
            .setMinutes("0 минут")
            .setUnlimitedMessages(false)
            .setUnlimitedSocialNetworks(false)
            .setUnlimitedMusic(false)
            .setUnlimitedVideo(false)
            .setUnlimitedSMS(false);
        assertEquals(tinkoffMobile.getTotalPrice(), "Общая цена: 0 \u20BD");
        assertTrue(tinkoffMobile.ButtonIsEnable("Далее"));
    }
}
