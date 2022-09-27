<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <suite>
            <xsl:variable name="suiteName" select="/suite/@name"/>
            <xsl:attribute name="name">
                <xsl:value-of select="$suiteName"/>
            </xsl:attribute>
            <test>
                <xsl:variable name="testName" select="/suite/test/@name"/>
                <xsl:attribute name="name">
                    <xsl:value-of select="$testName"/>
                </xsl:attribute>
                <classes>
                    <xsl:for-each select="/suite/test/classes/class">
                        <class>
                            <xsl:variable name="classId" select="./@id"/>
                            <xsl:variable name="className" select="./@name"/>
                            <xsl:attribute name="id">
                                <xsl:value-of select="$classId"/>
                            </xsl:attribute>
                            <xsl:attribute name="name">
                                <xsl:value-of select="$className"/>
                            </xsl:attribute>
                            <xsl:if test="methods">
                                <methods>
                                    <xsl:for-each select="methods/exclude">
                                        <exclude>
                                            <xsl:variable name="excludeName" select="./@name"/>
                                            <xsl:attribute name="name">
                                                <xsl:value-of select="$excludeName"/>
                                            </xsl:attribute>
                                        </exclude>
                                    </xsl:for-each>
                                </methods>
                            </xsl:if>
                        </class>
                    </xsl:for-each>
                </classes>
            </test>
        </suite>
    </xsl:template>
</xsl:stylesheet>