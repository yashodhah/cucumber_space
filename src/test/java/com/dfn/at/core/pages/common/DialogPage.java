package com.dfn.at.core.pages.common;

import com.dfn.at.core.pages.BasePage;

public class DialogPage extends BasePage {
    BottomButtonI bottomButtonI;

    public DialogPage() {
        super();
    }

    public DialogPage(BottomButtonI bottomButtonPanel) {
        super();
        bottomButtonI = bottomButtonPanel;
    }

    public void clickSaveButton() {
        bottomButtonI.clickSave();
        this.confirmYes();
    }

    public void clickCloseButton() {
        bottomButtonI.clickClose();
    }
}
