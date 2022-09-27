package com.dfn.at.core.pages.common;

import com.dfn.at.common.util.ElementUtility;
import org.openqa.selenium.By;

public class DynamicBottomButtonPanel implements BottomButtonI {
    By saveBtn = By.name("dyn-tab-btm-btn-save");
    By closeBtn = By.name("dyn-tab-btm-btn-close");

    @Override
    public void clickSave() {
        ElementUtility.click(saveBtn);
    }

    @Override
    public void clickReset() {

    }

    @Override
    public void clickClose() {
        ElementUtility.click(closeBtn);
    }
}
