package io.umid.taskapi.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PageRequestDto {

    int page = 0;
    int size = 10;
    String[] sortBy = new String[]{"id"};
    Sort.Direction direction = Sort.Direction.ASC;
    boolean defaultSort = true;

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy.split(" ");
        this.defaultSort = false;
    }
}
