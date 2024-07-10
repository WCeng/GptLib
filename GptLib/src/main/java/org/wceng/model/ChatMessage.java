package org.wceng.model;

public record ChatMessage(String role, String content, Image... images) {
    public record Image(String url, String detail) {
    }
}
