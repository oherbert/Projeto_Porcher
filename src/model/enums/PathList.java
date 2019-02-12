/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.enums;

/**
 *
 * @author Herbert
 */
public enum PathList {
    ARDUINOCOM("ArduinoCom"),
    CLOUDFOLDER("CloudFolder"),
    LOCALFOLDER("LocalFolder"),
    OFFSETSECAGEM_1("OffSetSecagem_1"),
    OFFSETSECAGEM_2("OffSetSecagem_2"),
    OFFSETSECAGEM_3("OffSetSecagem_3"),
    OFFSETSECAGEM_4("OffSetSecagem_4"),
    OFFSETVULCANICACAO_1("OffSetVulcanizacao_1"),
    OFFSETVULCANICACAO_2("OffSetVulcanizacao_2"),
    OFFSETVULCANICACAO_3("OffSetVulcanizacao_3"),
    OFFSETVULCANICACAO_4("OffSetVulcanizacao_4");

    private final String path;

    PathList(String thisPath) {
        path = thisPath;
    }

    public String getPath() {
        return path;
    }

}
