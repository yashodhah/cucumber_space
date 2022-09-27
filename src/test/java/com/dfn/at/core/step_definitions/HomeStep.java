package com.dfn.at.core.step_definitions;

import com.dfn.at.core.pages.HomePage;

public class HomeStep {
    HomePage homePage = new HomePage();
}


/*
 * Following commits need to be addressed and revert the change when executing excel based automation.
 * Better to go in the reverse order when reverting commits as same file might have been altered several times in the forward path

 * 1)
 * Underscore (_) needs to be reverted back to dash (-)
 * Excel automation works with - (dash) where as UI java automation works with underscore(_)
 * https://bitbucket.org/dfndc/gbl_x_ntpat/commits/1b7664d8129108b873e6a797d8c65724b5968bca
 * https://bitbucket.org/dfndc/gbl_x_ntpat/commits/769e47e068a3b2f6ae716121f97d27a4aa838be4

 * 2)
 * Grid table body data section's hidden column (i.e primary id column - z02_column_type = 8) name attribute changed from {{col.z02_mapping_name}} to td_{{col.z02_mapping_name}} distinguish header row and data rows
 * Grid table header section's filter control's name attribute changed to have a difference between header control and body controls name attributes
 * Following commit should be reverted to run excel automation
 * https://bitbucket.org/dfndc/gbl_x_ntpat/commits/6074d855c746575d74ec07c77b0f3083605bec22
 */