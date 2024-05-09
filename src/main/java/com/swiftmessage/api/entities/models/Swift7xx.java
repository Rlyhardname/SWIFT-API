package com.swiftmessage.api.entities.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "swift_7xx")
public class Swift7xx implements SwiftMessage{
//    @Id()
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @EmbeddedId
    private ReferenceAndMac referenceAndMac;
    @NotBlank
    @Column(name = "basic_header")
    private String basicHeader;
    @NotBlank
    @Column(name = "application_header")
    private String applicationHeader;
    @Column(name = "user_header")
    private String userHeader;
    @Column(name = "text",
            length = 1024)
    private String text;
    @Column(name = "trailer_block")
    private String trailerBlock;

    public Swift7xx(ReferenceAndMac referenceAndMac, String basicHeader, String applicationHeader, String userHeader, String text, String trailerBlock) {
        this.referenceAndMac = referenceAndMac;
        this.basicHeader = basicHeader;
        this.applicationHeader = applicationHeader;
        this.userHeader = userHeader;
        this.text = text;
        this.trailerBlock = trailerBlock;
    }

    public Swift7xx() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Swift7xx swift7xx = (Swift7xx) o;
        return Objects.equals(referenceAndMac, swift7xx.referenceAndMac) && Objects.equals(basicHeader, swift7xx.basicHeader) && Objects.equals(applicationHeader, swift7xx.applicationHeader) && Objects.equals(userHeader, swift7xx.userHeader) && Objects.equals(text, swift7xx.text) && Objects.equals(trailerBlock, swift7xx.trailerBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceAndMac, basicHeader, applicationHeader, userHeader, text, trailerBlock);
    }

    @Override
    public String toString() {
        return "Swift7xx{" +
                ", BasicHeader='" + basicHeader + '\'' +
                ", ApplicationHeader='" + applicationHeader + '\'' +
                ", UserHeader='" + userHeader + '\'' +
                ", Text='" + text + '\'' +
                ", TrailerBlock='" + trailerBlock + '\'' +
                '}';
    }

    public String getBasicHeader() {
        return basicHeader;
    }

    public String getApplicationHeader() {
        return applicationHeader;
    }

    public String getUserHeader() {
        return userHeader;
    }

    public String getText() {
        return text;
    }

    public String getTrailerBlock() {
        return trailerBlock;
    }

    public ReferenceAndMac getReferenceAndMac() {
        return referenceAndMac;
    }
}
