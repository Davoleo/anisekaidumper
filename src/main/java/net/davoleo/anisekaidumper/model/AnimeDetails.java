package net.davoleo.anisekaidumper.model;

import java.util.List;

public record AnimeDetails(String description, String downloadLink, int episodes, String season, List<String> genres, String studio, float score) {
}
