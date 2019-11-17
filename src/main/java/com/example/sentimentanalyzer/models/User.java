package com.example.sentimentanalyzer.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    private Long id;
    private String idStr;
    private String name;
    private String screenName;
    private String location;
    private String description;
    private String translatorType;
    private Boolean protectedUser;
    private Boolean verified;
    private Long followersCount;
    private Long friendsCount;
    private Long listedCount;
    private Long favouritesCount;
    private Long statusesCount;
    @JsonSerialize(using = DateSerializer.class)
    private Date createdAt;
    private Boolean geoEnabled;
    private String lang;
    private Boolean contributorsEnabled;
    private Boolean isTranslator;
    private String profileBackgroundColor;
    private String profileBackgroundImageUrl;
    private String profileBackgroundImageUrlHttps;
    private Boolean profileBackgroundTile;
    private String profileLinkColor;
    private String profileSidebarBorderColor;
    private String profileSidebarFillColor;
    private String profileTextColor;
    private Boolean profileUseBackgroundImage;
    private String profileImageUrl;
    private String profileImageUrlHttps;
    private String profileBannerUrl;
    private Boolean defaultProfile;
    private Boolean defaultProfileImage;

}
