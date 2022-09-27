package com.dfn.at.core.pages.master_data.symbol_management;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.core.constants.ApplicationConstants;
import com.dfn.at.core.pages.common.ColumnWiseApprovalPage;
import com.dfn.at.core.pages.common.TabPage;
import com.dfn.at.core.services.AdvancedApprovalService;
import org.openqa.selenium.By;

public class SymbolPage extends TabPage {
    private final String addEditTabIdPath = "symbols_entry";
    private int symbolId;
    private String symbolCode;
    private String symbolDescription;


    protected By btnSave = By.id("btnSymbolSave");
    protected By txtSymbolId = By.name("td_m20Id");
    protected By txtSymbolCode = By.id("m20SymbolCode");
    protected By txtMinUnitSize = By.id("m20MinimumUnitSize");
    protected By ddInstrumentType = By.id("dropdown_m20InstrumentTypeIdV09_" + ApplicationConstants.INSTRUMENT_TYPE_ID); // Common Stocks
    protected By ddInstrumentTypeItem = By.id("m20InstrumentTypeIdV09_" + ApplicationConstants.INSTRUMENT_TYPE_ID); // Common Stocks
    protected By ddPriceInstrumentType = By.id("dropdown_m20PriceInstrumentIdV34_"); // Common Stocks
    protected By ddPriceInstrumentTypeItem = By.id("m20PriceInstrumentIdV34_" + ApplicationConstants.PRICE_INSTRUMENT_TYPE_ID); // Common Stocks
    protected By ddCurrencySAR = By.id("dropdown_m20CurrencyIdM03_" + ApplicationConstants.CURRENCY_ID);
    protected By ddCurrencySARItem = By.id("m20CurrencyIdM03_" + ApplicationConstants.CURRENCY_ID);
    protected By ddNationalitySaudi = By.id("dropdown_m20CountryM05Id_" + ApplicationConstants.NATIONALITY_ID);
    protected By ddNationalitySaudiItem = By.id("m20CountryM05Id_" + ApplicationConstants.NATIONALITY_ID);
    protected By ddPriceType = By.id("dropdown_m20PriceType_" + ApplicationConstants.PRICE_TYPE_ID); // Factor: Default
    protected By ddPriceTypeItem = By.id("m20PriceType_" + ApplicationConstants.PRICE_TYPE_ID); // Factor: Default
    protected By txtLotSize = By.id("m20LotSize");
    protected By txtPriceRatio = By.id("m20PriceRatio");
    protected By ddSettleCategory = By.id("dropdown_m20SettleCategoryV11_"); // Normal
    protected By ddSettleCategoryItem = By.id("m20SettleCategoryV11_" + ApplicationConstants.SETTLE_CATEGORY_ID); // Normal
    protected By ddTradeType = By.id("dropdown_m20TradeTypeV01_"); // Exchange
    protected By ddTradeTypeItem = By.id("m20TradeTypeV01_" + ApplicationConstants.TRADE_TYPE_ID); // Exchange
    protected By txtShortDescription = By.id("m20ShortDescription");
    protected By txtShortDescriptionLang = By.id("m20ShortDescriptionLang");
    protected By txtLongDescription = By.id("m20LongDescription");
    protected By txtLongDescriptionLang = By.id("m20LongDescriptionLang");

    protected  By addTabId;
    protected By btnAddPageClose;
    protected By btnEditPageClose;
    protected By ddExchange;
    protected By ddExchangeItem;
    protected By ddMarket;
    protected By ddMarketItem;

    public String getSymbolCode() {
        return symbolCode;
    }

    public SymbolPage(String tabId) {
        super(tabId);
        this.setSelectors();
    }

    public void setSelectors() {
        this.ddExchange = By.id("dropdown_m20ExchangeIdM01_" + ApplicationConstants.EXCHANGE_ID);
        this.ddExchangeItem = By.id("dropdown_m20ExchangeIdM01_" + ApplicationConstants.EXCHANGE_ID);
        this.ddMarket = By.id("dropdown_m20MarketIdM29_");
        this.ddMarketItem = By.id("m20MarketIdM29_" + ApplicationConstants.MARKET_ID);
        this.addTabId = By.id(this.addEditTabIdPath);
        this.btnAddPageClose = By.id(this.addEditTabIdPath + "_close");
        this.symbolCode = "DFN";
        this.symbolDescription =  "Direct FN";
    }

    public void clickGridPageCloseButton() {
        super.closeTab();
    }

    public void clickAddPageCloseButton() {
        ElementUtility.click(btnAddPageClose);
    }

    public void clickEditPageCloseButton() {
        ElementUtility.click(btnEditPageClose);
    }

    public String getPageTitle() {
        return ElementUtility.getText(this.addTabId);
    }

    public void addNewValues() {
        ElementUtility.clickDropDown(ddInstrumentType, ddInstrumentTypeItem);
        ElementUtility.clickDropDown(ddExchange, ddExchangeItem);
        ElementUtility.setText(txtSymbolCode, this.symbolCode);
        ElementUtility.clickDropDown(ddMarket, ddMarketItem);
        ElementUtility.clickDropDown(ddPriceInstrumentType, ddPriceInstrumentTypeItem);
        ElementUtility.clickDropDown(ddCurrencySAR, ddCurrencySARItem);
        ElementUtility.setText(txtMinUnitSize, "1");
        ElementUtility.clickDropDown(ddNationalitySaudi, ddNationalitySaudiItem);
        ElementUtility.clickDropDown(ddPriceType, ddPriceTypeItem);
        ElementUtility.setText(txtLotSize, "1");
        ElementUtility.setText(txtPriceRatio, "1");
        ElementUtility.clickDropDown(ddSettleCategory, ddSettleCategoryItem);
        ElementUtility.clickDropDown(ddTradeType, ddTradeTypeItem);
    }

    public void clickSaveButton() {
        ElementUtility.click(btnSave);
        this.confirmYes();
    }

    public int extractsSymbolKey() {
        this.symbolId = Integer.parseInt(ElementUtility.getInnerHtml(this.txtSymbolId));
        this.btnEditPageClose = By.id(this.addEditTabIdPath + "_" + this.symbolId + "edit_close");
        return symbolId;
    }

    public void updateValues() {
        ElementUtility.setText(txtShortDescription, this.symbolCode);
        ElementUtility.setText(txtShortDescriptionLang, this.symbolCode);
        ElementUtility.setText(txtLongDescription, this.symbolDescription);
        ElementUtility.setText(txtLongDescriptionLang, this.symbolDescription);
    }

    public void performsAdvancedApprovals() {
        this.confirmYes();
        ColumnWiseApprovalPage columnWiseApprovalPage = new ColumnWiseApprovalPage(this);
        AdvancedApprovalService.performColumnWiseApproval(columnWiseApprovalPage);
    }
}
