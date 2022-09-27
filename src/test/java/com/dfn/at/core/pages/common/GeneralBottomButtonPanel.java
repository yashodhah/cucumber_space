package com.dfn.at.core.pages.common;

import com.dfn.at.common.util.ElementUtility;
import org.openqa.selenium.By;

public class GeneralBottomButtonPanel implements BottomButtonI {
    By saveBtn = By.name("btm-frm-btn-save");
    By closeBtn = By.name("btm-frm-btn-close");

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
